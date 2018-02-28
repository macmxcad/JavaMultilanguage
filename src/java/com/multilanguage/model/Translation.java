/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.model;

/**
 *
 * @author yasin
 */
public class Translation {
    private int id;
    private String langcode;
    private String langstring;
    private String langvalue;

    public Translation() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public String getLangstring() {
        return langstring;
    }

    public void setLangstring(String langstring) {
        this.langstring = langstring;
    }

    public String getLangvalue() {
        return langvalue;
    }

    public void setLangvalue(String langvalue) {
        this.langvalue = langvalue;
    }

    
    
}
