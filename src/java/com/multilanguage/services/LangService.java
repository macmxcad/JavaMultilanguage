/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multilanguage.services;

import com.multilanguage.DB.DBConnect;
import com.multilanguage.model.Language;
import com.multilanguage.model.Translation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yasin
 */
public class LangService {

    public String getLangValue(String langCode, String langString) {
        Connection conn = null;
        PreparedStatement preStat = null;
        String value = "Undefined";

        try {
            conn = new DBConnect().getConn();

            String query = "select langvalue from multilang_translations where langcode=? and langstring=?";

            preStat = conn.prepareStatement(query);
            preStat.setString(1, langCode);
            preStat.setString(2, langString);

            ResultSet rs = preStat.executeQuery();

            if (rs.next()) {
                value = rs.getString("langvalue");
            } else {
                System.out.println(preStat.toString());
            }

        } catch (Exception e) {

        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);

                    System.out.println("Hata : " + ex.toString());
                }
            }
        }
        if (value != null && value != "") {
            return value;
        } else {
            System.out.println(preStat.toString());
            return value;
        }
    }

    //To use front end
    public Map<String, String> getTranslationMap(String langCode) {
        Connection conn = null;
        PreparedStatement preStat = null;
        Map<String, String> languageMap = new HashMap<>();

        try {
            conn = new DBConnect().getConn();

            String query = "select * from multilang_translations where langcode=? ";

            preStat = conn.prepareStatement(query);
            preStat.setString(1, langCode);

            ResultSet rs = preStat.executeQuery();

            while (rs.next()) {
                languageMap.put(rs.getString("langstring"), rs.getString("langvalue"));
            }

        } catch (Exception e) {

        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);

                    System.out.println("Hata : " + ex.toString());
                }
            }
        }
        return languageMap;
    }

    //To use language manager
    public List<Translation> getTranslationListByCode(String langCode) {
        Connection conn = null;
        PreparedStatement preStat = null;
        List<Translation> language = new ArrayList<>();
        try {
            conn = new DBConnect().getConn();
            String query = "select * from multilang_translations where langcode=?";
            preStat = conn.prepareStatement(query);
            preStat.setString(1, langCode);
            ResultSet rs = preStat.executeQuery();

            while (rs.next()) {
                Translation lang = new Translation();
                lang.setId(rs.getInt("id"));
                lang.setLangcode(rs.getString("langcode"));
                lang.setLangstring(rs.getString("langstring"));
                lang.setLangvalue(rs.getString("langvalue"));
                language.add(lang);
            }

        } catch (Exception e) {

        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }
        }
        return language;

    }

    public List<Language> getLangList() {
        Connection conn = null;
        PreparedStatement preStat = null;
        List<Language> language = new ArrayList<>();
        try {
            conn = new DBConnect().getConn();
            String query = "select * from multilang_languages";
            preStat = conn.prepareStatement(query);
            ResultSet rs = preStat.executeQuery();

            while (rs.next()) {
                Language lang = new Language();
                lang.setId(rs.getInt("id"));
                lang.setLangCode(rs.getString("langcode"));
                lang.setLangName(rs.getString("langname"));
                language.add(lang);
            }

        } catch (Exception e) {

        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }
        }
        return language;

    }

    public boolean insertTranslation(String langCode, String langName) {
        Connection conn = null;
        PreparedStatement preStat = null;

        boolean inserted = false;
        if (!checkTranslationExist(langCode)) {
            try {

                conn = new DBConnect().getConn();
                String query = "insert into multilang_languages (langcode, langname) values (?,?)";
                preStat = conn.prepareStatement(query);
                preStat.setString(1, langCode);
                preStat.setString(2, langName);
                preStat.executeUpdate();
                inserted = true;

            } catch (Exception e) {
                System.out.println(e);
                return inserted;

            } finally {

                if (preStat != null) {
                    try {
                        preStat.close();
                    } catch (SQLException ex) {
                        //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Hata : " + ex.toString());
                    }
                }

                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Hata : " + ex.toString());
                    }
                }

            }
        }
        return inserted;
    }

    private boolean checkTranslationExist(String langCode) {
        Connection conn = null;
        PreparedStatement preStat = null;
        boolean langExist = false;

        try {

            conn = new DBConnect().getConn();

            String query = "select * from multilang_languages where langCode=?";

            preStat = conn.prepareStatement(query);
            preStat.setString(1, langCode);
            preStat.executeQuery();

            ResultSet rs = preStat.executeQuery();
            if (rs.next()) {
                langExist = true;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {

            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }
        }
        return langExist;
    }

    public boolean translationUpdated(String langValue, int id) {
        Connection conn = null;
        PreparedStatement preStat = null;

        try {

            conn = new DBConnect().getConn();

            String query = "update multilang_translations set langvalue=? where id=?";

            preStat = conn.prepareStatement(query);
            preStat.setString(1, langValue);
            preStat.setInt(2, id);
            preStat.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {

            if (preStat != null) {
                try {
                    preStat.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(AlarmImpl.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Hata : " + ex.toString());
                }
            }

        }

    }
}
