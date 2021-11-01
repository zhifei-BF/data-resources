package com.liquor.pattern.filter;

import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:19
 * Description：And标准，例如：单身且是男性
 *
 * @author Liquor
 * @version 1.0.0
 */
public class AndCriteria implements Criteria {
    //标准
    private Criteria criteria;
    //其他标准
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
