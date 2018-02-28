/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.faces;

import com.multilanguage.classes.FaceAccessor;
import com.multilanguage.services.LangService;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author yasin
 */
@ManagedBean(name = "login")
@RequestScoped
public class Login {

    private String userName;
    private String password;
    private static String selectedLang;
    public static String langCode = "en";
    private LangService langService = new LangService();

    public Login() {

    }

    public void loginProcess() {

        if (true) { //Authenticate User

            MultilangBean multilang = (MultilangBean) FaceAccessor.getManagedBean("Multilang");
            FacesContext context = FacesContext.getCurrentInstance();

            //dil harici extra bilgiler var ise ekleniyor.(username, password v.s sadece örnek proje için geçerli) 
            //Production da sadece selectedLang bilgisi alınması yeterli. 
            //selectedLang yerine isim olarak userLang gibi bir isimlendirme yapılabilir. (tr,en,fr... gibi dil iki haneli dil kodu)
            multilang.setUserId(1);
            multilang.setUserName(userName);
            multilang.setPassword(password);
            multilang.setUserEmail(userName);
            multilang.setLangCode(selectedLang);//Get Lang Code From User Info (Bu bilgi kullanıcı bilgilerinden (veritabanından) alınacak.)
            //kullanıcın diline göre, sadece bu map'in doldurulması yeterli.
            multilang.setUserLangMap(langService.getTranslationMap(selectedLang));

            context.getApplication().getNavigationHandler().handleNavigation(context, null, "index?faces-redirect=true");
        }
    }

    public void selectLang() {
        MultilangBean multilang = (MultilangBean) FaceAccessor.getManagedBean("Multilang");
        multilang.setLangCode(selectedLang);
    }

    //Bu method login ekranında dil değiştirmek için kullanıldı. langCode değişkeni, default olarak atandı. 
    //Production da bu method kullanılmayacak. 
    public String langChanger(String langCode, String langString) {

        MultilangBean multilang = (MultilangBean) FaceAccessor.getManagedBean("Multilang");
        if (selectedLang == null) {
            multilang.setLangCode("en");
            selectedLang = "en";
            String value = langService.getLangValue(multilang.getLangCode(), langString);
            return value;
        } else {
            multilang.setLangCode(selectedLang);
            String value = langService.getLangValue(multilang.getLangCode(), langString);
            return value;
        }
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

    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }

    public String getLangCode() {
        return this.langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

}
