package frankdevhub.job.automatic.web.handlers;

import frankdevhub.job.automatic.entities.BusinessCharacter;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface PageNavigatorHandler {

    WebElement findPageNavigator();

    String getPreviousPageButtonText();

    String getNextPageButtonText();

    Boolean hasPreviousPage();

    Boolean hasNextPage();

    Integer getCurrentPageIndex();

    List<WebElement> getDisplayedButtons();

    Boolean hasLastPageNum();

    Integer getLastPageNum();

    Boolean hasDefineCustomPageTextBox();

    Boolean hasTotalPageNum();

    Boolean hasSubmitButton();

    Boolean hasTextInfoDescription();

    BusinessCharacter getTextInfoDescriptionCharacter();
}
