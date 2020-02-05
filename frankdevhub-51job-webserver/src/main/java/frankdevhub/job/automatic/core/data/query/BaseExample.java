package frankdevhub.job.automatic.core.data.query;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:@ClassName BaseExample.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/6 3:34
 * @Version: 1.0
 */
public class BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public BaseExample setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public BaseExample setDistinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public BaseExample or(Criteria criteria) {
        oredCriteria.add(criteria);
        return this;
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

}
