package frankdevhub.job.automatic.core.data.query;

import java.util.List;

/**
 * <p>Title:@ClassName Criterion.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 3:22
 * @Version: 1.0
 */

public class Criterion {

    private String condition;
    private Object value;
    private Object secondValue;
    private Boolean noValue;
    private Boolean singleValue;
    private Boolean betweenValue;
    private Boolean listValue;
    private String typeHandler;

    protected Criterion(String condition) {
        super();
        this.condition = condition;
        this.typeHandler = null;
        this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
        super();
        this.condition = condition;
        this.value = value;
        this.typeHandler = typeHandler;
        if (value instanceof List<?>) {
            this.listValue = true;
        } else {
            this.singleValue = true;
        }
    }

    protected Criterion(String condition, Object value) {
        this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
        super();
        this.condition = condition;
        this.value = value;
        this.secondValue = secondValue;
        this.typeHandler = typeHandler;
        this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
        this(condition, value, secondValue, null);
    }

    public String getCondition() {
        return condition;
    }

    public Criterion setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Criterion setValue(Object value) {
        this.value = value;
        return this;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public Criterion setSecondValue(Object secondValue) {
        this.secondValue = secondValue;
        return this;
    }

    public Boolean getNoValue() {
        return noValue;
    }

    public Criterion setNoValue(Boolean noValue) {
        this.noValue = noValue;
        return this;
    }

    public Boolean getSingleValue() {
        return singleValue;
    }

    public Criterion setSingleValue(Boolean singleValue) {
        this.singleValue = singleValue;
        return this;
    }

    public Boolean getBetweenValue() {
        return betweenValue;
    }

    public Criterion setBetweenValue(Boolean betweenValue) {
        this.betweenValue = betweenValue;
        return this;
    }

    public Boolean getListValue() {
        return listValue;
    }

    public Criterion setListValue(Boolean listValue) {
        this.listValue = listValue;
        return this;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public Criterion setTypeHandler(String typeHandler) {
        this.typeHandler = typeHandler;
        return this;
    }
}
