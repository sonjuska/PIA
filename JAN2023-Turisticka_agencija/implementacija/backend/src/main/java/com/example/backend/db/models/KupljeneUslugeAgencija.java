package com.example.backend.db.models;

public class KupljeneUslugeAgencija {
    private String naziv;
    private String tip;
    private String lokacija_od;
    private String lokacija_do;
    private int broj_saputnika;
    
    public KupljeneUslugeAgencija(String naziv, String tip, String lokacija_od, String lokacija_do,
            int broj_saputnika) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija_od = lokacija_od;
        this.lokacija_do = lokacija_do;
        this.broj_saputnika = broj_saputnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLokacija_od() {
        return lokacija_od;
    }

    public void setLokacija_od(String lokacija_od) {
        this.lokacija_od = lokacija_od;
    }

    public String getLokacija_do() {
        return lokacija_do;
    }

    public void setLokacija_do(String lokacija_do) {
        this.lokacija_do = lokacija_do;
    }

    public int getBroj_saputnika() {
        return broj_saputnika;
    }

    public void setBroj_saputnika(int broj_saputnika) {
        this.broj_saputnika = broj_saputnika;
    }

    
}
