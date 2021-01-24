package frankdevhub.job.automatic.web.handlers;

import frankdevhub.job.automatic.entities.BusinessCharacter;
import org.openqa.selenium.WebElement;

import java.util.List;

@SuppressWarnings("all")
public interface PageNavigatorHandler {

    /**
     * 获取页面分页导航对象
     *
     * @return 页面分页导航对象
     */
    WebElement findPageNavigator();

    /**
     * 获取上一页的页数
     *
     * @return 上一页的页数
     */
    String getPreviousPageButtonText();

    /**
     * 获取下一页的页数
     *
     * @return 下一页的页数
     */
    String getNextPageButtonText();

    /**
     * 是否有上一页
     *
     * @return 是否有上一页
     */
    boolean hasPreviousPage();


    /**
     * 是否有下一页
     *
     * @return 是否有下一页
     */
    boolean hasNextPage();

    /**
     * 获取当前页数
     *
     * @return 获取当前页数
     */
    int getCurrentPageIndex();

    /**
     * 获取当前页面导航对象上所有的控件集合
     *
     * @return 当前页面导航对象上所有的控件集合
     */
    List<WebElement> getDisplayedButtons();

    /**
     * 是否有最后一页
     *
     * @return 是否有最后一页
     */
    boolean hasLastPageNum();

    /**
     * 获取最后一页
     *
     * @return 获取最后一页
     */
    int getLastPageNum();

    /**
     * 是否页面含有自定义的分页控件
     *
     * @return 是否页面含有自定义的分页控件
     */
    boolean hasDefineCustomPageTextBox();

    /**
     * 是否页面含有总页数
     *
     * @return 是否页面含有总页数
     */
    boolean hasTotalPageNum();

    /**
     * 是否页面含有提交控件
     *
     * @return 是否页面含有提交控件
     */
    boolean hasSubmitButton();

    /**
     * 是否页面索引数详情描述的控件
     *
     * @return 是否页面索引数详情描述的控件
     */
    boolean hasTextInfoDescription();

    /**
     * 获取页面索引数详情描述的控件
     *
     * @return 页面索引数详情描述的控件
     */
    BusinessCharacter getTextInfoDescriptionCharacter();
}
