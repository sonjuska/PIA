package com.example.backend.db.models;

public class KupljeneUsluge {

    private int id_posete;
    private int id_usluge;
    private String id_putnik;
    private int broj_saputnika;
    
    public KupljeneUsluge(int id_posete, int id_usluge, String id_putnik, int broj_saputnika) {
        this.id_posete = id_posete;
        this.id_usluge = id_usluge;
        this.id_putnik = id_putnik;
        this.broj_saputnika = broj_saputnika;
    }

    public int getId_posete() {
        return id_posete;
    }

    public void setId_posete(int id_posete) {
        this.id_posete = id_posete;
    }

    public int getId_usluge() {
        return id_usluge;
    }

    public void setId_usluge(int id_usluge) {
        this.id_usluge = id_usluge;
    }

    public String getId_putnik() {
        return id_putnik;
    }

    public void setId_putnik(String id_putnik) {
        this.id_putnik = id_putnik;
    }

    public int getBroj_saputnika() {
        return broj_saputnika;
    }

    public void setBroj_saputnika(int broj_saputnika) {
        this.broj_saputnika = broj_saputnika;
    }

    
}
