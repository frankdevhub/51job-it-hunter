package frankdevhub.job.automatic.web.handlers;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.entities.BusinessCharacter;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <p>Title:@ClassName SearchResultPageNavigator.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/11 6:04
 * @Version: 1.0
 */
public class SearchResultPageNavigator implements PageNavigatorHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(SearchResultPageNavigator.class);

    @Override
    public WebElement findPageNavigator() {
        return null;
    }

    @Override
    public String getPreviousPageButtonText() {
        return null;
    }

    @Override
    public String getNextPageButtonText() {
        return null;
    }

    @Override
    public Boolean hasPreviousPage() {
        return null;
    }

    @Override
    public Boolean hasNextPage() {
        return null;
    }

    @Override
    public Integer getCurrentPageIndex() {
        return null;
    }

    @Override
    public List<WebElement> getDisplayedButtons() {
        return null;
    }

    @Override
    public Boolean hasLastPageNum() {
        return null;
    }

    @Override
    public Integer getLastPageNum() {
        return null;
    }

    @Override
    public Boolean hasDefineCustomPageTextBox() {
        return null;
    }

    @Override
    public Boolean hasTotalPageNum() {
        return null;
    }

    @Override
    public Boolean hasSubmitButton() {
        return null;
    }

    @Override
    public Boolean hasTextInfoDescription() {
        return null;
    }

    @Override
    public BusinessCharacter getTextInfoDescriptionCharacter() {
        return null;
    }
}
