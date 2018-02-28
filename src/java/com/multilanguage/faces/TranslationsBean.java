/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.faces;

import com.multilanguage.model.Language;
import com.multilanguage.model.Translation;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author yasin
 */
@ManagedBean(name = "translationsBean")
@ViewScoped
public class TranslationsBean implements Serializable {

    private List<Translation> translationsList;
    private List<Language> langList;
    private String selectedLang;
    private Translation selectedLanguage;
    private LangService langService = new LangService();

    public TranslationsBean() {
    }

    @PostConstruct
    public void init() {
        langList = new ArrayList<>();
        langList = langService.getLangList();
    }

    public void btnGetLang_Click() {
        translationsList = new ArrayList<>();
        translationsList = langService.getTranslationListByCode(selectedLang);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("frmDialog:DTcolumns");
    }

    public void onRowEdit(RowEditEvent event) {

        Translation edit = (Translation) event.getObject();
        if (langService.translationUpdated(edit.getLangvalue(), edit.getId())) {
            FacesMessage msg = new FacesMessage("Edit Success", ((Translation) event.getObject()).getLangvalue());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Edit Failed", ((Translation) event.getObject()).getLangvalue());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Translation> getTranslationsList() {
        return translationsList;
    }

    public void setTranslationsList(List<Translation> translationsList) {
        this.translationsList = translationsList;
    }

    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }

    public Translation getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(Translation selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public List<Language> getLangList() {
        return langList;
    }

    public void setLangList(List<Language> langList) {
        this.langList = langList;
    }
}
