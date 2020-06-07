package com.ssm.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * @Author: TJC
 * @Date: 2020/6/7 10:01
 * @description: TODO
 */
public class DateStringEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Timestamp timestamp = DateUtils.string2Date(text, "yyyy-MM-dd HH:mm");
            super.setValue(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
