package com.example.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.db.DB;
import com.example.backend.db.models.Agencija;
import com.example.backend.db.models.KupljeneUslugeAgencija;
import com.example.backend.db.models.Putnik;
import com.example.backend.db.models.Usluga;

public class Repo {

    public Putnik loginPutnik(Putnik p) {
    
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from putnik where korisnickoime=? and lozinka=?")){

            stm.setString(1, p.getKorisnickoime());
            stm.setString(2, p.getLozinka());
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                return new Putnik(rs.getString("korisnickoime"), rs.getString("lozinka"), rs.getString("ime_prezime"), rs.getString("imejl"), rs.getString("lokacija_trenutna"), rs.getDouble("novac"));
            }
            
        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return null;
    }

    public Agencija loginAgencija(Agencija a) {
      
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from agencija where korisnickoime=? and lozinka=?")){

        stm.setString(1, a.getKorisnickoime());
        stm.setString(2, a.getLozinka());
        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            return new Agencija(rs.getString("korisnickoime"), rs.getString("lozinka"), rs.getString("naziv"), rs.getLong("PIB"));
        }

    }catch(SQLException e){
        e.printStackTrace(); 
    }
    return null;
    }

    public List<Usluga> uslugePoIdA(String korisnickoime) {
       
       List<Usluga> usluge = new ArrayList<>();

       try(Connection conn = DB.source().getConnection();
       PreparedStatement stm = conn.prepareStatement("select * from usluga where idAgencije=?")){

       stm.setString(1, korisnickoime);
       ResultSet rs = stm.executeQuery();

       while(rs.next()){
           Usluga u = new Usluga(rs.getInt("id"),
                rs.getString("idAgencije"),
                rs.getString("lokacija_od"),
                rs.getString("lokacija_do"),
                rs.getString("tip"),
                rs.getString("period"),
                rs.getDouble("cena"),
                rs.getInt("broj_mesta"));

            usluge.add(u);
       }
       return usluge;

   }catch(SQLException e){
       e.printStackTrace(); 
   }

       return null;
    }

    public int dodajUslugu(String idAgencije, String tip, String lokacija_od, String lokacija_do, String period,
            double cena, int broj_mesta) {

            try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("insert into usluga(idAgencije,lokacija_od,lokacija_do,tip,period,cena,broj_mesta) values(?,?,?,?,?,?,?)")){

                stm.setString(1, idAgencije);
                stm.setString(2, lokacija_od);
                stm.setString(3, lokacija_do);
                stm.setString(4, tip);
                stm.setString(5, period);
                stm.setDouble(6, cena);
                stm.setInt(7, broj_mesta);
                int res = stm.executeUpdate();

            return res;

            }catch(SQLException e){
                e.printStackTrace(); 
            }
            return -1;

    }

    public List<String> sveLokacije(String id_putnik) {
        List<String> lokacije= new ArrayList<>();

       try(Connection conn = DB.source().getConnection();
       PreparedStatement stm = conn.prepareStatement("select distinct lokacija_od from usluga,kupljeneusluge where id_usluge = id and id_putnik = ?\n" + //
                      "union\n" + //
                      "select distinct lokacija_do from usluga,kupljeneusluge where id_usluge = id and id_putnik = ?")){

       stm.setString(1, id_putnik);
       stm.setString(2, id_putnik);
       ResultSet rs = stm.executeQuery();

       while(rs.next()){
           String u = rs.getString("lokacija_od");

            if(u != null && !u.isEmpty()){
                lokacije.add(u);
            }

       }
       return lokacije;

        }catch(SQLException e){
            e.printStackTrace(); 
        }

       return null;
    }

    public String trenutnaLokacija(String id_putnik) {

       try(Connection conn = DB.source().getConnection();
       PreparedStatement stm = conn.prepareStatement("select lokacija_trenutna from putnik where korisnickoime=?")){

       stm.setString(1, id_putnik);

       ResultSet rs = stm.executeQuery();

       if(rs.next()){
           return rs.getString("lokacija_trenutna");
       }

        }catch(SQLException e){
            e.printStackTrace(); 
        }

       return null;
    }

    public int promeniTrenutnuLokaciju(String id_putnik, String lokacija) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update putnik set lokacija_trenutna=? where korisnickoime=?")){

            stm.setString(1, lokacija);
            stm.setString(2, id_putnik);
            
            int res = stm.executeUpdate();

        return res;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return -1;
    }

    public List<KupljeneUslugeAgencija> dohvatiKupljeneUsluge(String korisnickoime) {
        List<KupljeneUslugeAgencija> lista = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select A.naziv, U.tip, U.lokacija_od, U.lokacija_do, K.broj_saputnika\n" + //
                        "from putnik P, usluga U, agencija A, kupljeneusluge K\n" + //
                        "where P.korisnickoime = ? and K.id_putnik=P.korisnickoime and K.id_usluge = U.id and U.idAgencije = A.korisnickoime;")){

            stm.setString(1, korisnickoime);
            
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                KupljeneUslugeAgencija o = new KupljeneUslugeAgencija(
                    rs.getString("naziv"),
                    rs.getString("tip"),
                    rs.getString("lokacija_od"),
                    rs.getString("lokacija_do"),
                    rs.getInt("broj_saputnika")
                    );
    
                
                lista.add(o);
            }
            return lista;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return null;
    }

    public List<Usluga> dohvatiUsluge(){
        List<Usluga> lista = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from usluga")){
          
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Usluga o = new Usluga(
                    rs.getInt("id"),
                    rs.getString("idAgencije"),
                    rs.getString("lokacija_od"),
                    rs.getString("lokacija_do"),
                    rs.getString("tip"),
                    rs.getString("period"),
                    rs.getDouble("cena"),
                    rs.getInt("broj_mesta")
                    );
    
                
                lista.add(o);
            }
            return lista;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return null;
    }

    public int dodajKupljenuUslugu(int id_usluge, String id_putnik, int broj_saputnika) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm1 = conn.prepareStatement("select novac from putnik where korisnickoime=?");
        PreparedStatement stm2 = conn.prepareStatement("select cena from usluga where id=?");
        PreparedStatement insert = conn.prepareStatement("insert into kupljeneusluge(id_usluge,id_putnik,broj_saputnika) values(?,?,?)")){
        
            
            stm1.setString(1, id_putnik);
            stm2.setInt(1, id_usluge);
            insert.setInt(1,id_usluge);
            insert.setString(2,id_putnik);
            insert.setInt(3, broj_saputnika);

            ResultSet rs1 = stm1.executeQuery(), rs2 = stm2.executeQuery();

            // dohvati cenu usluge sa id_usluge i izracunaj (brSap+1)*cena
            if(rs1.next() && rs2.next()){
                double novac = (double)rs1.getInt("novac");
                double cena = rs2.getDouble("cena");

                double popust = (broj_saputnika+1>3)? 0.8 : 1;
                double konacna_cena = (broj_saputnika+1)*cena*popust;
                
                if(novac>konacna_cena){
                    updateNovac(id_putnik, konacna_cena);
                    updateBrojMesta(id_usluge, broj_saputnika+1);
                    return insert.executeUpdate();

                }else{
                    return -2;             //nema dovoljno novca
                }
                
            }

        return -4;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return -5;
    }

    public int updateNovac(String korisnickoime, double novac){
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update putnik set novac=novac-? where korisnickoime=?")){

            stm.setDouble(1, novac);
            stm.setString(2, korisnickoime);
            
            int res = stm.executeUpdate();

        return res;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return -3;
    }

    public int updateBrojMesta(int id, int brMesta){
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update usluga set broj_mesta=broj_mesta-? where id=?")){

            stm.setInt(1, brMesta);
            stm.setInt(2, id);
            
            int res = stm.executeUpdate();

        return res;

        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return -3;
    }

    public Putnik getPutnik(String korisnickoime, String lozinka) {
    
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from putnik where korisnickoime=? and lozinka=?")){

            stm.setString(1, korisnickoime);
            stm.setString(2, lozinka);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                return new Putnik(rs.getString("korisnickoime"), rs.getString("lozinka"), rs.getString("ime_prezime"), rs.getString("imejl"), rs.getString("lokacija_trenutna"), rs.getDouble("novac"));
            }
            
        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return null;
    }

    public Agencija getAgencija(String korisnickoime, String lozinka) {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from agencija where korisnickoime=? and lozinka=?")){

            stm.setString(1, korisnickoime);
            stm.setString(2, lozinka);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                return new Agencija(rs.getString("korisnickoime"), rs.getString("lozinka"), rs.getString("naziv"), rs.getLong("PIB"));
            }
            
        }catch(SQLException e){
            e.printStackTrace(); 
        }
        return null;
    }
    
}
