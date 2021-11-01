package com.liquor.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:18
 * Description：单一的标准
 *
 * @author Liquor
 * @version 1.0.0
 */
public class CriteriaSingle implements Criteria {

    /**
     * 筛选出婚姻状况是单一的人
     *
     * @param persons
     * @return
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
