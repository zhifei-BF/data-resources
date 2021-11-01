package com.liquor.pattern.filter;

import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:14
 * Description：标准
 *
 * @author Liquor
 * @version 1.0.0
 */
public interface Criteria {

    /**
     * 符合标准
     *
     * @param persons
     * @return
     */
    public List<Person> meetCriteria(List<Person> persons);

}
