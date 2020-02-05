package frankdevhub.job.automatic.core.data.query;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:@ClassName GeneratedCriteria.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 3:30
 * @Version: 1.0
 */
public class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
        super();
        criteria = new ArrayList<>();
    }

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
        return criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    protected void addCriterion(String condition) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
        if (value == null) {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIsNull(String columnName) {
        addCriterion("" + columnName + " is null");
        return (Criteria) this;
    }

    public Criteria andIsNotNull(String columnName) {
        addCriterion("" + columnName + " is not null");
        return (Criteria) this;
    }

    public Criteria andEqualTo(String columnName, Integer value) {
        addCriterion("" + columnName + " =", value, columnName);
        return (Criteria) this;
    }

    public Criteria andEqualTo(String columnName, String value) {
        addCriterion("" + columnName + " =", value, columnName);
        return (Criteria) this;
    }

    public Criteria andEqualTo(String columnName, Long value) {
        addCriterion("" + columnName + " =", value, columnName);
        return (Criteria) this;
    }

    public Criteria andNotEqualTo(String columnName, Integer value) {
        addCriterion("" + columnName + " <>", value, columnName);
        return (Criteria) this;
    }

    public Criteria andNotEqualTo(String columnName, String value) {
        addCriterion("" + columnName + " <>", value, columnName);
        return (Criteria) this;
    }

    public Criteria andNotEqualTo(String columnName, Long value) {
        addCriterion("" + columnName + " <>", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThan(String columnName, Integer value) {
        addCriterion("" + columnName + " >", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThan(String columnName, Double value) {
        addCriterion("" + columnName + " >", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThan(String columnName, Long value) {
        addCriterion("" + columnName + " >", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThanOrEqualTo(String columnName, Integer value) {
        addCriterion("" + columnName + " >=", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThanOrEqualTo(String columnName, Long value) {
        addCriterion("" + columnName + " >=", value, columnName);
        return (Criteria) this;
    }

    public Criteria andGreaterThanOrEqualTo(String columnName, Double value) {
        addCriterion("" + columnName + " >=", value, columnName);
        return (Criteria) this;
    }


    public Criteria andLessThan(String columnName, Integer value) {
        addCriterion("" + columnName + " <", value, columnName);
        return (Criteria) this;
    }

    public Criteria andLessThan(String columnName, Long value) {
        addCriterion("" + columnName + " <", value, columnName);
        return (Criteria) this;
    }

    public Criteria andLessThan(String columnName, Double value) {
        addCriterion("" + columnName + " <", value, columnName);
        return (Criteria) this;
    }

    public Criteria andLessThanOrEqualTo(String columnName, Integer value) {
        addCriterion("" + columnName + " <=", value, columnName);
        return (Criteria) this;
    }

    public Criteria andLessThanOrEqualTo(String columnName, Long value) {
        addCriterion("" + columnName + " <=", value, columnName);
        return (Criteria) this;
    }

    public Criteria andLessThanOrEqualTo(String columnName, Double value) {
        addCriterion("" + columnName + " <=", value, columnName);
        return (Criteria) this;
    }


    public Criteria andIn(String columnName, List<Object> values) {
        addCriterion("" + columnName + " in", values, columnName);
        return (Criteria) this;
    }


    public Criteria andNotIn(String columnName, List<Object> values) {
        addCriterion("" + columnName + " not in", values, columnName);
        return (Criteria) this;
    }

    public Criteria andBetween(String columnName, Integer value1, Integer value2) {
        addCriterion("" + columnName + " between", value1, value2, columnName);
        return (Criteria) this;
    }

    public Criteria andBetween(String columnName, Double value1, Double value2) {
        addCriterion("" + columnName + " between", value1, value2, columnName);
        return (Criteria) this;
    }

    public Criteria andBetween(String columnName, Long value1, Long value2) {
        addCriterion("" + columnName + " between", value1, value2, columnName);
        return (Criteria) this;
    }

    public Criteria andNotBetween(String columnName, Integer value1, Integer value2) {
        addCriterion("" + columnName + " not between", value1, value2, columnName);
        return (Criteria) this;
    }

    public Criteria andNotBetween(String columnName, Double value1, Double value2) {
        addCriterion("" + columnName + " not between", value1, value2, columnName);
        return (Criteria) this;
    }

    public Criteria andNotBetween(String columnName, Long value1, Long value2) {
        addCriterion("" + columnName + " not between", value1, value2, columnName);
        return (Criteria) this;
    }

}