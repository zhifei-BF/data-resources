package com.liquor.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：10:59
 * Description：员工
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Employee {
    //名字
    private String name;
    //部门
    private String dept;
    //薪资
    private int salary;
    //下属
    private List<Employee> subordinates;

    //构造函数
    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return ("Employee :[ Name : " + name
            + ", dept : " + dept + ", salary :"
            + salary + " ]");
    }
}
