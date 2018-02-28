/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.faces;

import com.multilanguage.model.Language;
import com.multilanguage.services.LangService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yasin
 */
@ManagedBean(name = "languageBean")
@ViewScoped
public class LanguageBean implements Serializable{

    private List<Language> langList;
    private String newLangCode;
    private String newLangName;
    private LangService langService = new LangService();
    
    @PostConstruct
    public void init() {
        langList = new ArrayList<>();
        langList = langService.getLangList();
    }
    
    public void btnInsertLang_Click() {
        
        newLangCode = newLangCode.toLowerCase();

        if (langService.insertTranslation(newLangCode, newLangName)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("frmDialog:DTcolumns");
            FacesMessage msg = new FacesMessage("Insert Success", newLangName);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Insert Failed", newLangName);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public List<Language> getLangList() {
        return langList;
    }

    public void setLangList(List<Language> langList) {
        this.langList = langList;
    }

    public String getNewLangCode() {
        return newLangCode;
    }

    public void setNewLangCode(String newLangCode) {
        this.newLangCode = newLangCode;
    }

    public String getNewLangName() {
        return newLangName;
    }

    public void setNewLangName(String newLangName) {
        this.newLangName = newLangName;
    }
    
    
}
