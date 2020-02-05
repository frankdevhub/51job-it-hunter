package frankdevhub.job.automatic.selenium.webelements;

import java.util.List;

/**
 * <p>Title:@ClassName XPath.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/2 0:17
 * @Version: 1.0
 */
public class XPath {

    private String expression;
    private List<NodeDefinition> definitions;

    public String getExpression() {
        return expression;
    }

    public XPath setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public List<NodeDefinition> getDefinitions() {
        return definitions;
    }

    public XPath setDefinitions(List<NodeDefinition> definitions) {
        this.definitions = definitions;
        return this;
    }

    protected void addNodeDefinition() {

    }
}
