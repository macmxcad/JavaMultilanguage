/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.faces;

import com.multilanguage.classes.FaceAccessor;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author yasin
 */
@ManagedBean(name = "index")
@SessionScoped
public class IndexBean implements Serializable{
    
    private String userName;
    private String password;
    private String userEmail;
    private String testField;
    
    

    public String testField()
    {
        MultilangBean multilang = (MultilangBean)FaceAccessor.getManagedBean("Multilang");
        return multilang.getUserLangMap().get("gnrl_description");
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
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
    
    
}
