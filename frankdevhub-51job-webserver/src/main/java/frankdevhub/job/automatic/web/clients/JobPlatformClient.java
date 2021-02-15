package frankdevhub.job.automatic.web.clients;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.core.parser.PlatformDataConverter;
import frankdevhub.job.automatic.core.parser.PlatformPageParser;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.entities.business.JobSearchResult;
import frankdevhub.job.automatic.entities.business.PlatformDataJson;
import frankdevhub.job.automatic.selenium.DriverBase;
import frankdevhub.job.automatic.selenium.config.ChromeConfiguration;
import frankdevhub.job.automatic.service.JobSearchResultService;
import frankdevhub.job.automatic.service.PlatformDataJsonService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName JobPlatformClient.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/23 3:32
 * @Version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class JobPlatformClient {

    //源码window.__SEARCH_RESULT__=句柄处开始
    private final String DATA_JSON_REGEX = "window.__SEARCH_RESULT__\\s?=\\s?(?<context>\\{.*\\})</script>";

    private JobSearchResultService getJobSearchResultService() {
        //解析后的平台职位数据
        return SpringUtils.getBean(JobSearchResultService.class);
    }

    private PlatformDataJsonService getPlatformDataJsonService() {
        //平台职位源数据
        return SpringUtils.getBean(PlatformDataJsonService.class);
    }

    /**
     * 获取缓存的会话信息,直接登录
     *
     * @param configuration Chrome浏览器配置对象
     * @return 客户端浏览器会话对象
     * @throws Exception
     */
    public Set<Cookie> getPlatformCookie(ChromeConfiguration configuration) throws Exception {
        DriverBase.instantiateDriverObject();

        log.info("using cache path: " + configuration.getSeleniumCacheDirectoryPath());
        //实例化浏览器驱动对象
        //跳转至平台首页
        WebDriver driver = DriverBase.getDriver(configuration.getSeleniumCacheDirectoryPath());
        driver.get(BusinessConstants.JOB_PLATFORM_HOMEPAGE_SH);

        //判断校验是否成功跳转至平台搜索页面
        WebDriverUtils.doWaitTitleContains(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY_1, new WebDriverWait(driver, 3));
        log.info("navigate to platform homepage complete");
        log.info("start to get web cookie");
        Long start = System.currentTimeMillis();
        Set<Cookie> cookies = driver.manage().getCookies(); //获取驱动的缓存
        Long end = System.currentTimeMillis();
        //加载读取驱动缓存消耗时间
        log.info("time cost: " + (end - start) + " ms");
        log.info("cookie properties:");
        for (Cookie c : cookies) {
            log.info("name = " + c.getName());
            log.info("path = " + c.getPath());
            log.info("value = " + c.getValue());
            log.info("\n");
        }
        return cookies;
    }

    @Data
    private class JobSearchResultRestoreThread extends Thread {

        private String pageUrl;  //列表页面的链接地址
        private List<JobSearchResult> results; //返回的搜索集合对象
        private JobSearchResultService service; //解析返回的搜索集合的服务

        /**
         * 构建页面返回的结果集对象
         *
         * @param results 页面返回的结果集对象
         * @param service 页面返回的结果集存储服务
         * @return 页面扫描的线程对象
         */
        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultService service) {
            this.results = results;
            this.service = service;
        }

        private void restoreJobSearchResults(List<JobSearchResult> results) {
            if (null == this.service) {
                throw new RuntimeException("repository should not be null");
            }
            for (JobSearchResult result : results) {
                restoreJobSearchResult(result);
            }
        }

        @Transactional
        private void restoreJobSearchResult(JobSearchResult result) {
            //TODO: 企业信息解析，企业运营资质,职位列表
            try {
                if (null == result.getUnionId()) //如果唯一标识为空则跳过
                    return;
                int UnionId = result.getUnionId(); //生成搜索结果集的唯一标识
                int count = service.selectCountByUnionId(UnionId);
                if (count == 0) {
                    result.doCreateEntity();
                    service.insertSelective(result);
                } else {
                    JobSearchResult res = service.selectByUnionId(UnionId); //查询校验是否已有入库的结果集
                    result.doUpdateEntity();
                    service.updateByPrimaryKeySelective(res);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            log.info("job search result restore thread start");
            if (StringUtils.isNotEmpty(this.pageUrl)) {
                log.info("page url = " + pageUrl);
            }
            if (null == results) {
                return;
            }
            restoreJobSearchResults(results);
        }
    }

    /**
     * 解析搜索职位返回的结果集对象
     *
     * @param url 页面链接地址(含有关键字)
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    public List<JobSearchResult> getJobSearchResult(String url) throws IOException, XpathSyntaxErrorException {
        //获取页面DOM对象的字符串格式
        String pageContext = PlatformWebClient.getPageHtmlText(url);

        Assert.notNull(pageContext, "page context cannot be found");
        String keyword = PlatformWebClient.getSearchKeyword(url);
        Assert.notNull(keyword, "keyword cannot be found");
        log.info("keyword  = {}", keyword);
        List<JobSearchResult> results = new ArrayList<>();
        Document document = Jsoup.parse(pageContext);
        //转为DOM文档对象进行解析
        JXDocument jxDocument = new JXDocument(document);
        //定位职位搜索返回的结果集列表
        List<JXNode> rows = jxDocument.selN(SeleniumConstants.SEARCH_RESULT_LIST_XPATH);
        log.info("rows.size  = {}", rows.size());
        //如果jsoup返回的报文可以获取列表信息
        if (null != rows && rows.size() > 5) {
            //逐行解析返回的职位信息
            for (JXNode row : rows) {
                try {
                    //解析职位信息对象
                    JobSearchResult result = PlatformPageParser.parseSearchResult(row, keyword);
                    results.add(result);
                } catch (BusinessException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //如果不能获取则依据规则匹配页面加载js中的json对象
            //过滤去除空格换行符
            String ctx = pageContext.replaceAll("\\n", "");
            Pattern p = Pattern.compile(DATA_JSON_REGEX);
            Matcher m = p.matcher(ctx);
            String j = null;
            if (m.find())
                j = m.group("context");

            Assert.notNull(j, "data json cannot be found");
            log.info(j);
            //解析返回的json对象,获取指定业务属性下的数据集
            List<PlatformDataJson> datas = PlatformDataConverter.convertContext(j);
            //保存源数据
            if (null != datas & datas.size() > 0) {
                for (PlatformDataJson data : datas) {
                    Assert.notNull(data.getJobId(), "cannot find jobId");
                    //TODO: 校验源数据 jobid查重判断是否已经存在
                    PlatformDataJson d = getPlatformDataJsonService().selectByUnionId(Integer.parseInt(data.getJobId()));
                    log.info("property id = {}", data.getJobId());
                    if (null == d) {
                        data.doCreateEntity();
                        getPlatformDataJsonService().insertSelective(data);
                    } else {
                        data.doUpdateEntity();
                        data.setId(d.getId());
                        getPlatformDataJsonService().updateByPrimaryKeySelective(data);
                    }
                }
            }
            //TODO
            //JobSearchResult result = PlatformDataConverter.convert(j);
        }
        log.info("get search result entity list complete");
        return results;
    }


    /**
     * 解析并存储返回的结果集对象
     *
     * @param results 页面返回的结果集对象
     * @param service 页面返回的结果集存储服务
     */
    private void restoreJobSearchResult(List<JobSearchResult> results, ExecutorService service) {
        Runnable task = new JobSearchResultRestoreThread(results, getJobSearchResultService());
        log.info("submit task to executor service pool");
        service.submit(task);
    }

    /**
     * 解析结果集对象(页面单位)
     *
     * @param url     页面链接地址
     * @param service 线程池对象
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    public void restorePageJobSearchResult(String url, ExecutorService service) throws IOException, XpathSyntaxErrorException {
        List<JobSearchResult> results = getJobSearchResult(url);
        log.info("results size = " + results.size());
        //子线程解析获取的搜索结果集对象并存储
        restoreJobSearchResult(results, service);
    }

}
