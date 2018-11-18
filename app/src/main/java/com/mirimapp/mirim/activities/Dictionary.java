package com.mirimapp.mirim.activities;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Dictionary {

    private String name;
    private String sub_writer;
    private String sub_date;
    private String sub_content;

    boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_writer() {
        return sub_writer;
    }

    public void setSub_writer(String sub_writer) {
        this.sub_writer = sub_writer;
    }

    public String getSub_date() {
        return sub_date;
    }

    public void setSub_date(String sub_date) {
        this.sub_date = sub_date;
    }

    public String getSub_content() {
        return sub_content;
    }

    public void setSub_content(String sub_content) {
        this.sub_content = sub_content;
    }

    public Dictionary(String name, String sub_writer, String sub_date, String sub_content) {
        this.name = name;
        this.sub_writer = sub_writer;
        this.sub_date = sub_date;
        this.sub_content = sub_content;
    }
}
