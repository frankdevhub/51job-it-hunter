package frankdevhub.job.automatic.web.pages;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.WebDriverUtils;
import frankdevhub.job.automatic.selenium.AssignDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tk.mybatis.mapper.util.Assert;

/**
 * <p>Title:@ClassName JobPlatformSearchPage.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/4 21:46
 * @Version: 1.0
 */
public class JobPlatformSearchPage extends BaseWebPage {

    private final String jobKeyword;

    private final Logger LOGGER = LoggerFactory.getLogger(JobPlatformSearchPage.class);

    public JobPlatformSearchPage(Boolean isAutoConfig, String jobKeyword) {
        super(isAutoConfig);
        Assert.notNull(jobKeyword, BusinessConstants.JOB_SEARCH_KEYWORD_NULL);
        this.jobKeyword = jobKeyword;
    }

    private void initSearchPage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::initSearchPage()}}");
        AssignDriver.initQueryObjects(this, (RemoteWebDriver) getDriver());
    }

    private void navigateToPlatformHomePage() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::navigateToPlatformHomePage()}}");
        getDriver().get(BusinessConstants.JOB_PLATFORM_HOMEPAGE);
        WebDriverWait wait = new WebDriverWait(getDriver(), 5, 100);
        WebDriverUtils.doWaitTitle(BusinessConstants.JOB_PLATFORM_HOMEPAGE_TITLE_KEY, wait);

        LOGGER.begin().info("navigate to www.51job.com success");
    }

    private void inputSearchQuery() {

    }

    public void startSearchResultPatrol() {
        LOGGER.begin().info("invoke {{JobPlatformSearchPage::startSearchResultPatrol()}}");
    }

}
