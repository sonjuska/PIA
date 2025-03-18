package com.example.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.db.DB;
import com.example.backend.models.aktivnost;
import com.example.backend.models.korisnik;
import com.example.backend.models.prijava;

public class Repo {

    public korisnik login(korisnik entity) {
        
       try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from korisnici where korisnicko_ime=? and lozinka=? and tip=?")){

            stm.setString(1, entity.getKorime());
            stm.setString(2, entity.getLozinka());
            stm.setString(3, entity.getTip());

            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new korisnik(rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("ime"), rs.getString("prezime"), rs.getString("tip"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public List<aktivnost> aktuelneAktivnosti() {

        List<aktivnost> aktivnosti = new ArrayList<>();
        
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from aktivnosti where status=1")){

        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            aktivnost a = new aktivnost(rs.getInt("id"),
                rs.getString("naziv"),
             rs.getString("datum_vreme"),
             rs.getString("napravio"),
             rs.getInt("status"),
             rs.getInt("sala1"),
              rs.getInt("sala2"),
               rs.getInt("sala3")
            );
            aktivnosti.add(a);
        }
        return aktivnosti;
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
    }

    public aktivnost dohvatiAktivnostPoId(String id) {
        aktivnost a = null;
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from aktivnosti where id=?")){

        stm.setInt(1, Integer.parseInt(id));

        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            a = new aktivnost(rs.getInt("id"),
                rs.getString("naziv"),
                rs.getString("datum_vreme"),
                rs.getString("napravio"),
                rs.getInt("status"),
                rs.getInt("sala1"),
                rs.getInt("sala2"),
                rs.getInt("sala3")
            );
        }
        return a;
        
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;

    }

    public prijava dohvatiPrijavu(String korime, String id) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from prijave where student=? and aktivnost=?")){

        stm.setString(1, korime);
        stm.setInt(2, Integer.parseInt(id));

        ResultSet rs = stm.executeQuery();
        if(rs.next()){
             return new prijava(rs.getString("student"),
                rs.getInt("aktivnost"),
                rs.getString("sala")
            );
        }
        
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
}

    public List<prijava>svePrijaveStudenta(String korime) {
        List<prijava> prijave = new ArrayList<>();
        
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from prijave where student=?")){

            stm.setString(1, korime);
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            prijava p = new prijava(rs.getString("student"),
            rs.getInt("aktivnost"),
            rs.getString("sala")
            );
            prijave.add(p);
        }
        return prijave;
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
    }

    public int novaPrijava(String sala, int id, String korime) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update aktivnosti set "+sala+"="+sala+"-1 where id=?")){

            stm.setInt(1, id);
            int res = stm.executeUpdate();
        
            PreparedStatement insert = conn.prepareStatement("insert into prijave(student,aktivnost,sala) values(?,?,?)");
            insert.setString(1, korime);
            insert.setInt(2, id);
            insert.setString(3, sala);
            int res2=insert.executeUpdate();

            if(res==1 && res2==1) return 1;
            else return 0;

    }catch(SQLException e){
        e.printStackTrace();
    }
    return 0;
    }

    public List<aktivnost> nastavnikAktivnosti(String korime) {
        List<aktivnost> aktivnosti = new ArrayList<>();
        
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from aktivnosti where napravio=?")){
        stm.setString(1, korime);
        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            aktivnost a = new aktivnost(rs.getInt("id"),
                rs.getString("naziv"),
             rs.getString("datum_vreme"),
             rs.getString("napravio"),
             rs.getInt("status"),
             rs.getInt("sala1"),
              rs.getInt("sala2"),
               rs.getInt("sala3")
            );
            aktivnosti.add(a);
        }
        return aktivnosti;
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
    }

    public int promeniStatus(int status, int id) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update aktivnosti set status=? where id=?")){

            stm.setInt(1, status);
            stm.setInt(2, id);
            return stm.executeUpdate();

    }catch(SQLException e){
        e.printStackTrace();
    }
    return 0;
    }

    public int dodajAktivnost(String naziv, String datumVreme, String napravio, Boolean sala1, Boolean sala2,
            Boolean sala3) {
            try(Connection conn = DB.source().getConnection();
            PreparedStatement insert = conn.prepareStatement("insert into aktivnosti(naziv, datum_vreme,napravio,status,sala1,sala2,sala3) values(?,?,?,?,?,?,?)")){
    
                if(sala1){
                    try( PreparedStatement s1 = conn.prepareStatement("select broj_mesta from sale where naziv='Sala1'")) {
                        ResultSet rs = s1.executeQuery();
                        if(rs.next()){
                            insert.setInt(5,  rs.getInt(1));
                        }
                    } catch (Exception e) {
                        System.out.println("Greska u upitu.");
                    }
                }else{
                    insert.setInt(5,  -1);
                }
                if(sala2){
                    try( PreparedStatement s1 = conn.prepareStatement("select broj_mesta from sale where naziv='Sala2'")) {
                        ResultSet rs = s1.executeQuery();
                        if(rs.next()){
                            insert.setInt(6,  rs.getInt(1));
                        }
                    } catch (Exception e) {
                        System.out.println("Greska u upitu.");
                    }
                }else{
                    insert.setInt(6,  -1);
                }
                if(sala3){
                    try( PreparedStatement s1 = conn.prepareStatement("select broj_mesta from sale where naziv='Sala3'")) {
                        ResultSet rs = s1.executeQuery();
                        if(rs.next()){
                            insert.setInt(7,  rs.getInt(1));
                        }
                    } catch (Exception e) {
                        System.out.println("Greska u upitu.");
                    }
                }else{
                    insert.setInt(7,  -1);
                }

                insert.setString(1, naziv);
                insert.setString(2, datumVreme);
                insert.setString(3, napravio);
                insert.setInt(4, 1);
                int res=insert.executeUpdate();
    
                return res;
    
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    
}
    
    
