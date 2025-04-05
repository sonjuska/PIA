package com.example.backend.db.models;

public class Usluga {
    private int id;
    private String id_agencije;
    private String lokacija_od;
    private String lokacija_do;
    private String tip;
    private String period;
    private double cena;
    private int broj_mesta;
    
    public Usluga(int id, String id_agencije, String lokacija_od, String lokacija_do, String tip, String period,
            double cena, int broj_mesta) {
        this.id = id;
        this.id_agencije = id_agencije;
        this.lokacija_od = lokacija_od;
        this.lokacija_do = lokacija_do;
        this.tip = tip;
        this.period = period;
        this.cena = cena;
        this.broj_mesta = broj_mesta;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getId_agencije() {
        return id_agencije;
    }
    public void setId_agencije(String id_agencije) {
        this.id_agencije = id_agencije;
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
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public double getCena() {
        return cena;
    }
    public void setCena(double cena) {
        this.cena = cena;
    }
    public int getBroj_mesta() {
        return broj_mesta;
    }
    public void setBroj_mesta(int broj_mesta) {
        this.broj_mesta = broj_mesta;
    }

    
}
