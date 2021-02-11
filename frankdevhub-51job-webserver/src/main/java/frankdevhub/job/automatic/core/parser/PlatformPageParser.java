package frankdevhub.job.automatic.core.parser;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXNode;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import frankdevhub.job.automatic.core.constants.SeleniumConstants;
import frankdevhub.job.automatic.core.exception.BusinessException;
import frankdevhub.job.automatic.entities.business.JobCompany;
import frankdevhub.job.automatic.entities.business.JobSearchResult;
import frankdevhub.job.automatic.web.clients.PlatformWebClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import tk.mybatis.mapper.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PlatformPageParser
 * @Description: 平台页面解析工具类
 * @date: 2021/2/3 11:29
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class PlatformPageParser {

    /**
     * 解析结果集对象
     *
     * @param row     页面行对象
     * @param keyword 搜索关键字
     * @return 页面搜索结果集对象
     * @throws XpathSyntaxErrorException,BusinessException,IllegalAccessException
     */
    public static JobSearchResult parseSearchResult(JXNode row, String keyword) throws XpathSyntaxErrorException, BusinessException, IllegalAccessException {
        JXNode jobDescriptionNode = row.sel(SeleniumConstants.RESULT_JD_NAME_XPATH).get(0); //职位信息描述
        JXNode companyNameNode = row.sel(SeleniumConstants.RESULT_COMPANY_NAME_XPATH).get(0); //公司名称
        JXNode salaryRangeNode = row.sel(SeleniumConstants.RESULT_SALARY_RANGE_XPATH).get(0); //薪资范围
        JXNode publishDateNode = row.sel(SeleniumConstants.RESULT_JD_PUBLISH_DATE_XPATH).get(0); //职位发布日期
        JXNode jobLocationNode = row.sel(SeleniumConstants.RESULT_JD_LOCATION_XPATH).get(0); //职位地点信息

        Assert.notNull(jobDescriptionNode, "job description node cannot be found on this row"); //校验职位信息描述
        Assert.notNull(companyNameNode, "company name node cannot be found on this row"); //校验职位所在公司名称不能为空
        Assert.notNull(publishDateNode, "publish date node cannot be found on this row"); //校验职位发布日期不能为空
        Assert.notNull(jobLocationNode, "job location node cannot be found on this row"); //校验职位地点不能为空

        //职位简介信息对象
        JobSearchResult result = new JobSearchResult();

        result.setJobTitle(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE)) //职位名称
                .setLinkUrl(jobDescriptionNode.getElement().attr(SeleniumConstants.ATTRIBUTE_HREF)); //职位描述的详情链接

        String salaryRangeText = null == salaryRangeNode.getTextVal() ? "" : salaryRangeNode.getTextVal();
        result.setSalaryRangeChars(salaryRangeText);
        log.info("salaryRangeText = {}", salaryRangeText);

        if (StringUtils.isNotEmpty(salaryRangeText.trim())) {
            SalaryRangeTextUtils utils = new SalaryRangeTextUtils(salaryRangeNode.getTextVal()); //解析薪资范围描述
            utils.parse();
            result.setSalaryNumericUnit(utils.getNumericUnit()) //薪资数值单位
                    .setSalaryRangeMin(utils.getMinimizeValue()) //薪资范围的最小值
                    .setSalaryRangeMax(utils.getMaximumValue()) //薪资范围的最大值
                    .setSalaryTimeUnit(utils.getTimeUnit()) //薪资的计量时间
                    .setIsDefineByDay(utils.isUnitByDay()) //薪资是否以天为单位进行计量
                    .setIsDefineByMonth(utils.isUnitByMonth()) //薪资是否以月为单位进行计量
                    .setIsDefineByYear(utils.isUnitByYear()) //薪资是否以年为单位进行计量
                    .setIsDefineByK(utils.isUnitByThousand()) //薪资是否以千位数进行计量
                    .setIsDefineByW(utils.isUnitByTenThousand()); //薪资是否以万位数进行计量
        }
        //判断是否是校招职位
        try {
            row.sel(SeleniumConstants.RESULT_JD_CAMPUS_ONLY_XPATH).get(0);
            result.setIsCampusOnly(true);
        } catch (Exception e) {
            result.setIsCampusOnly(false);
        }
        //判断是内部推荐岗位
        try {
            row.sel(SeleniumConstants.RESULT_JD_INTERNSHIP_ONLY_XPATH).get(0);
            result.setIsInternshipPos(true);
        } catch (Exception e) {
            result.setIsInternshipPos(false);
        }
        result.setIsSalaryNegotiable(false); //薪资是否可商议
        result.setCompanyName(companyNameNode.getElement().attr(SeleniumConstants.ATTRIBUTE_TITLE).trim());
        result.setLocation(jobLocationNode.getElement().childNodes().get(0).outerHtml().trim());
        //职位发布日期以及其他属性
        String publishDate = publishDateNode.getElement().childNodes().get(0).outerHtml().trim();
        //替换中文字符 2020-01-15 发布-> 2020-01-15
        publishDate = publishDate.replaceAll("[\u4E00-\u9FA5]+", ""); //过滤替换中文字符例如"发布"
        int month = Integer.parseInt(publishDate.split("-")[0]);
        int day = Integer.parseInt(publishDate.split("-")[1]);//职位发布日期(天)
        result.setPublishDateChar(publishDate) //职位发布日期(字符串)
                .setPublishDateDayNumeric(day) //职位发布日期(天)
                .setPublishDateMonthNumeric(month); //职位发布日期(月)
        result.generateMarkId(); //生成hashCode和唯一标识

        log.info("result markId  = {}", result.getUnionId());
        return result;
    }


    /**
     * 解析平台上的企业介绍页面以及企业相关职位介绍
     *
     * @param companyInfo 是否抓取企业信息相关的内容
     * @param jobList     是否抓取该企业所有正在招聘的职位信息
     * @return 企业信息以及相关开放招聘的职位信息的集合
     * dataMap 字段:
     * company: 企业实体对象
     * context: 企业信息介绍
     * jobList: 企业开放的招聘的职位列表(所有页面)
     * @throws IOException
     */
    public static Map<String, Object> parseCompanyPlatformPage(String url, boolean companyInfo, boolean jobList) throws IOException {

        Map<String, Object> data = new HashMap<>();
        Assert.notNull(url, "cannot find url");
        //获取企业信息coid唯一标识
        String unionId = PlatformWebClient.getPageUnionId(url);
        Assert.notNull(unionId, "cannot find unionId, url = " + url + "");
        log.info("unionId = {}", unionId);

        HtmlPage page = PlatformWebClient.getWebPage(url, null); //获取企业信息页面
        JobCompany company = new JobCompany(); //创建企业信息实例
        data.put("company", company);
        company.setUnionId(Integer.parseInt(unionId)); //链接中提取的唯一识别号
        //测试获取公司信息介绍
        //div class = 'tCompany_center clearfix'
        HtmlDivision div = page.getFirstByXPath(SeleniumConstants.COMPANY_INFO_TEXT_XPATH);
        Assert.notNull(div, "cannot find element by path '//div[@class='tCompany_center clearfix']'");
        //获取html源码
        String ctx = div.asXml();
        company.setContext(ctx);
        data.put("context", ctx);
        //TODO
        //获取html源码,去除多余换行字符存储为源数据

        //释放资源
        page.getWebClient().close();

        return data;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param text 字符串参数,可以是页面链接或页面的html源码字符串
     *             如果是企业介绍页的链接,则先获取当前页面对象的html再进行解析
     *             如果是页面的html字符串,则进行页面字符的解析,匹配获取页面上的图片链接
     * @return 页面上的图片链接集合
     */
    public static List<String> parseCompanyPlatformPageImages(String text) {
        return null;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param context 企业介绍页的html源码字字符串
     * @return 页面上的图片链接集合
     */
    private static List<String> getCompanyImages(String context) {
        List<String> images = new ArrayList<>();
        return images;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param text 字符串参数,可以是页面链接或页面的html源码字符串
     *             如果是企业介绍页的链接,则先获取当前页面对象的html再进行解析
     *             如果是页面的html字符串,则进行页面字符的解析,匹配获取页面上的图片链接
     * @return 页面上的图片链接集合
     */
    public static List<String> parseCompanyPlatformPageLogos(String text) {
        return null;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param context 企业介绍页的html源码字字符串
     * @return 页面上的图片链接集合
     */
    private static List<String> getCompanyLogos(String context) {
        List<String> images = new ArrayList<>();
        return images;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param text 字符串参数,可以是页面链接或页面的html源码字符串
     *             如果是企业介绍页的链接,则先获取当前页面对象的html再进行解析
     *             如果是页面的html字符串,则进行页面字符的解析,匹配获取页面上的图片链接
     * @return 页面上的图片链接集合
     */
    public static List<String> parseCompanyPlatformPageCompanyDetails(String text) {
        return null;
    }


    /**
     * 解析企业介绍信息页面的图片链接
     *
     * @param context 企业介绍页的html源码字字符串
     * @return 页面上的图片链接集合
     */
    private static List<String> getCompanyDetails(String context) {
        List<String> images = new ArrayList<>();
        return images;
    }
}
