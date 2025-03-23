package com.example.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.db.DB;
import com.example.backend.models.Aukcija;
import com.example.backend.models.Umetnina;
import com.example.backend.models.User;

public class Repo {

    public User login(User u) {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from korisnici where korisnicko_ime=? and lozinka=? and tip=?")){

            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setString(3, u.getType());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new User(rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("ime"), rs.getString("prezime"), rs.getString("mejl"), rs.getString("tip"));
            }
        }catch(SQLException e){

        }
        return null;
    }

    public List<Aukcija> aktivneAukcije() {
        
        List<Aukcija> aukcije = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from aukcije where kraj>now() order by kraj")){
            
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                aukcije.add(new Aukcija(rs.getInt("idA"), rs.getString("naziv"), rs.getString("pocetak"), rs.getString("kraj")));
            }

            return aukcije;
        }catch(SQLException e){

        }
        return null;
    }

    public List<Aukcija> sveAukcije() {
        List<Aukcija> aukcije = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from aukcije")){
            
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                aukcije.add(new Aukcija(rs.getInt("idA"), rs.getString("naziv"), rs.getString("pocetak"), rs.getString("kraj")));
            }

            return aukcije;
        }catch(SQLException e){

        }
        return null;
    }

    public List<Aukcija> neaktivneAukcije() {
        
        List<Aukcija> aukcije = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from aukcije where kraj<now() order by kraj")){
            
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                aukcije.add(new Aukcija(rs.getInt("idA"), rs.getString("naziv"), rs.getString("pocetak"), rs.getString("kraj")));
            }

            return aukcije;
        }catch(SQLException e){

        }
        return null;
    }

    public List<Umetnina> umetninePoIdA(String param) {
        List<Umetnina> umetnine = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from umetnine where idA=?")){
            
                stm.setInt(1, Integer.parseInt(param));
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                umetnine.add(new Umetnina(rs.getInt("idU"), rs.getInt("idA"), rs.getString("naziv"), rs.getInt("ponuda"), rs.getString("vlasnik")));
            }

            return umetnine;
        }catch(SQLException e){

        }
        return null;
    
    }

    public int updatePonuda(int idU, int idA, String naziv, int ponuda, String vlasnik) {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from umetnine where idU = ?")){

                stm.setInt(1, idU);
                ResultSet rs = stm.executeQuery();
                
                if(rs.next()){
                    int staraPonuda = rs.getInt("ponuda");

                    if(ponuda>staraPonuda){
                        PreparedStatement stm2 = conn.prepareStatement("update umetnine set ponuda=?,vlasnik=? where idU = ?");
                        stm2.setInt(1, ponuda);
                        stm2.setString(2, vlasnik);
                        stm2.setInt(3, idU);

                        return stm2.executeUpdate();
                    }else{

                        return -1;
                    }
                }

        }catch(SQLException e){

        }
        
        return 0;
    }

    public List<Umetnina> kupljeneUmetnine(String username) {
        List<Umetnina> umetnine = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from umetnine U join aukcije A on (U.idA=A.idA) where vlasnik=? and kraj<now();")){
            
                stm.setString(1, username);
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                umetnine.add(new Umetnina(rs.getInt("idU"), rs.getInt("idA"), rs.getString("naziv"), rs.getInt("ponuda"), rs.getString("vlasnik")));
            }

            return umetnine;
        }catch(SQLException e){

        }
        return null;
    }
    public List<Umetnina> sveKupljeneUmetnine() {
        List<Umetnina> umetnine = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from umetnine U join aukcije A on (U.idA=A.idA) where vlasnik is not null and kraj<now()")){
            
                ResultSet rs = stm.executeQuery();

            while(rs.next()){

                umetnine.add(new Umetnina(rs.getInt("idU"), rs.getInt("idA"), rs.getString("naziv"), rs.getInt("ponuda"), rs.getString("vlasnik")));
            }

            return umetnine;
        }catch(SQLException e){

        }
        return null;
    }

    /*
    public int maksUmetninaId(){
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select max(idU) from umetnine")){

            ResultSet rs =stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }

        }catch(SQLException e){

        }
        return -1;
    }
    */
    
}
