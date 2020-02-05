package frankdevhub.job.automatic.selenium.webelements;

/**
 * <p>Title:@ClassName NodeDefinition.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 4:35
 * @Version: 1.0
 */
public class NodeDefinition {

    private String expression;

    private StringBuffer builder;

    public String getExpression() {
        return expression;
    }

    public StringBuffer getBuilder() {
        return builder;
    }

    public NodeDefinition setBuilder(StringBuffer builder) {
        this.builder = builder;
        return this;
    }
}
