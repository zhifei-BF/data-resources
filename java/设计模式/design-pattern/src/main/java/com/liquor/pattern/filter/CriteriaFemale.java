package com.liquor.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:17
 * Description：标准女性
 *
 * @author Liquor
 * @version 1.0.0
 */
public class CriteriaFemale implements Criteria {

    /**
     * 筛选出女性
     *
     * @param persons
     * @return
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
