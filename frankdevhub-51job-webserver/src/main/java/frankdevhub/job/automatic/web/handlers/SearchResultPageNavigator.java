package frankdevhub.job.automatic.web.handlers;

import frankdevhub.job.automatic.entities.BusinessCharacter;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@SuppressWarnings("all")
public class SearchResultPageNavigator implements PageNavigatorHandler {

    /**
     * 获取页面分页导航对象
     *
     * @return 页面分页导航对象
     */
    @Override
    public WebElement findPageNavigator() {
        return null;
    }

    /**
     * 获取上一页的页数
     *
     * @return 上一页的页数
     */
    @Override
    public String getPreviousPageButtonText() {
        return null;
    }

    /**
     * 获取下一页的页数
     *
     * @return 下一页的页数
     */
    @Override
    public String getNextPageButtonText() {
        return null;
    }

    /**
     * 是否有上一页
     *
     * @return 是否有上一页
     */
    @Override
    public boolean hasPreviousPage() {
        return false;
    }

    /**
     * 是否有下一页
     *
     * @return 是否有下一页
     */
    @Override
    public boolean hasNextPage() {
        return false;
    }

    /**
     * 获取当前页数
     *
     * @return 获取当前页数
     */
    @Override
    public int getCurrentPageIndex() {
        return 0;
    }

    /**
     * 获取当前页面导航对象上所有的控件集合
     *
     * @return 当前页面导航对象上所有的控件集合
     */
    @Override
    public List<WebElement> getDisplayedButtons() {
        return null;
    }

    /**
     * 是否有最后一页
     *
     * @return 是否有最后一页
     */
    @Override
    public boolean hasLastPageNum() {
        return false;
    }

    /**
     * 获取最后一页
     *
     * @return 获取最后一页
     */
    @Override
    public int getLastPageNum() {
        return 0;
    }

    /**
     * 是否页面含有自定义的分页控件
     *
     * @return 是否页面含有自定义的分页控件
     */
    @Override
    public boolean hasDefineCustomPageTextBox() {
        return false;
    }

    /**
     * 是否页面含有总页数
     *
     * @return 是否页面含有总页数
     */
    @Override
    public boolean hasTotalPageNum() {
        return false;
    }

    /**
     * 是否页面含有提交控件
     *
     * @return 是否页面含有提交控件
     */
    @Override
    public boolean hasSubmitButton() {
        return false;
    }

    /**
     * 是否页面索引数详情描述的控件
     *
     * @return 是否页面索引数详情描述的控件
     */
    @Override
    public boolean hasTextInfoDescription() {
        return false;
    }

    /**
     * 获取页面索引数详情描述的控件
     *
     * @return 页面索引数详情描述的控件
     */
    @Override
    public BusinessCharacter getTextInfoDescriptionCharacter() {
        return null;
    }
}
