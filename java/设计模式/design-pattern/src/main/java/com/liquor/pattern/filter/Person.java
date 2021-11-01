package com.liquor.pattern.filter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:13
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Person {

    private String name;

    //性别
    private String gender;

    //婚姻状况
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}
