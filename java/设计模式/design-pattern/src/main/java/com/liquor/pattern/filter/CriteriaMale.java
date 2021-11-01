package com.liquor.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:15
 * Description：标准男性
 *
 * @author Liquor
 * @version 1.0.0
 */
public class CriteriaMale implements Criteria {
    /**
     * 筛选出标准男性
     *
     * @param persons
     * @return
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }

}
