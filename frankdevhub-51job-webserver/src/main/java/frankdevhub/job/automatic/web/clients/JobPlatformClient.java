package frankdevhub.job.automatic.web.clients;

import cn.wanghaomiao.xpath.model.JXDocument;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.SpringUtils;
import frankdevhub.job.automatic.entities.JobSearchResult;
import frankdevhub.job.automatic.repository.JobSearchResultRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class JobPlatformClient {

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformClient.class);

    private JobSearchResultRepository getSearchResultRepository() {
        return SpringUtils.getBean(JobSearchResultRepository.class);
    }

    private String getPageHtmlText(String url) throws IOException {
        LOGGER.begin().info("invoke {{JobPlatformClient::getPageHtmlText()}}");

        String pageContext = null;
        CloseableHttpClient httpClient = null;

        Long start = System.currentTimeMillis();
        try {
            // httpClient = HttpClientBuilder.create().build();
            httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "text/html; charset=GBK");

            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            pageContext = EntityUtils.toString(responseEntity, "GBK");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }

        Long end = System.currentTimeMillis();
        System.out.println(String.format("time cost: %s sec", (end - start) / 1000));

        System.out.println(pageContext);


        return pageContext;
    }

    private String getPreviousPage(String url) {
        String previousPage = null;
        return previousPage;
    }

    private String getNextPage(String url) {
        String nextPage = null;
        return nextPage;
    }

    private class JobSearchResultRestoreThread extends Thread {

        private String pageUrl;
        private List<JobSearchResult> results;
        private JobSearchResultRepository repository;

        public JobSearchResultRestoreThread setResults(List<JobSearchResult> results) {
            this.results = results;
            return this;
        }

        public JobSearchResultRestoreThread setRepository(JobSearchResultRepository repository) {
            this.repository = repository;
            return this;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultRepository repository) {
            this.results = results;
            this.repository = repository;
        }

        protected JobSearchResultRestoreThread(List<JobSearchResult> results, JobSearchResultRepository repository, String pageUrl) {
            this.results = results;
            this.repository = repository;
            this.pageUrl = pageUrl;
        }

        private void restoreJobSearchResults(List<JobSearchResult> results) {

        }

        @Override
        public void run() {
            System.out.println("job search result restore thread start");
            if (StringUtils.isNotEmpty(this.pageUrl))
                System.out.println("page url = " + pageUrl);
            if (null == results)
                return;
            restoreJobSearchResults(results);
        }

    }

    private void restoreJobSearchResult(List<JobSearchResult> results) {

    }

    public List<JobSearchResult> getJobSearchResult(String url) throws IOException {
        LOGGER.begin().info("invoke {{JobPlatformClient::getJobSearchResult()}}");

        String pageContext = getPageHtmlText(url);

        System.out.println("get search result page context complete");
        List<JobSearchResult> results = new ArrayList<>();
        Document document = Jsoup.parse(pageContext);
        JXDocument jxDocument = new JXDocument(document);

        return results;
    }


}
