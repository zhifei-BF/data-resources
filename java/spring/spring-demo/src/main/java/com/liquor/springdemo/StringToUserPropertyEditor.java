package com.liquor.springdemo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 16:19
 */
public class StringToUserPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = new User();
        user.setName(text);
        this.setValue(user);
    }
}
