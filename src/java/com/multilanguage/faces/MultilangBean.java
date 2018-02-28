/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.faces;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author yasin
 */
@ManagedBean(name = "Multilang")
@SessionScoped

/**
 * Giriş yapan kullanıcının kullandığı dile göre (dil koduna göre) gerekli bilgiler bu classta bulunan userLangMap e kaydedilir. Session sonuna kadar saklanır. Sayfalarda kullanımı: Multilang.userLangMap.get('hello') şeklindedir. hello stringinin seçilen dil karşılığını getir anlamıdadır.
 */
public class MultilangBean implements Serializable {

    private String langCode;
    private int userId;
    private String userName;
    private String password;
    private String userEmail;
    private String userLanguage;
    private Map<String, String> userLangMap;

    public MultilangBean() {
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public Map<String, String> getUserLangMap() {
        return userLangMap;
    }

    public void setUserLangMap(Map<String, String> userLangMap) {
        this.userLangMap = userLangMap;
    }

}
