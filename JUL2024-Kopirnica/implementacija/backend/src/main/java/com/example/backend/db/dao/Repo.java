package com.example.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.db.DB;
import com.example.backend.models.Knjiga;
import com.example.backend.models.Korisnik;
import com.example.backend.models.Opcija;
import com.example.backend.models.Zahtev;
import com.example.backend.models.ZahtevJoinKlijent;

public class Repo {
    public Korisnik login(Korisnik u) {
        try(Connection conn = DB.source().getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from korisnici where kor_ime=? and lozinka=?")){

            stm.setString(1, u.getKor_ime());
            stm.setString(2, u.getLozinka());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new Korisnik(rs.getString("kor_ime"), rs.getString("lozinka"), rs.getString("ime"), rs.getString("prezime"), rs.getString("mejl"), rs.getString("tip"));
            }
        }catch(SQLException e){

        }
        return null;
    }

    public List<Knjiga> knjigeKlijent(String param) {
        List<Knjiga> knjige= new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from knjige where klijent=?")){

            stm.setString(1, param);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Knjiga k = new Knjiga(rs.getInt("idK"), rs.getString("klijent"), rs.getString("naziv"),  rs.getInt("strane"));
                knjige.add(k);
            }

            return knjige;
        }catch(SQLException e){

        }
    return null;
    }

    public Knjiga knjigaDetalji(int idK) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from knjige where idK=?")){

            stm.setInt(1, idK);

            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new Knjiga(rs.getInt("idK"), rs.getString("klijent"), rs.getString("naziv"),  rs.getInt("strane"));
                
            }

        }catch(SQLException e){

        }
    return null;
    }

    public int azurirajKnjigu(String naziv, int strane, int idK) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update knjige set naziv=?,strane=? where idK=?")){

            stm.setString(1, naziv);
            stm.setInt(2, strane);
            stm.setInt(3, idK);

            return stm.executeUpdate();


        }catch(SQLException e){

        }
        return -1;
    
    }

    public List<Zahtev> zahteviKlijent(String kor_ime) {
        List<Zahtev> zahtevi= new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select Z.*, K.naziv \r\n" + //
                        "from zahtevi Z \r\n" + //
                        "join knjige K on Z.knjiga = K.idK \r\n" + //
                        "where Z.klijent = ?\r\n" + //
                        "")){

            stm.setString(1, kor_ime);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Zahtev k = new Zahtev(rs.getInt("idZ"),
                rs.getString("klijent"),
                rs.getInt("knjiga"),
                rs.getString("naziv"),
                rs.getString("opcije").split(","),
                rs.getDouble("racun"),
                rs.getDate("datum"),
                rs.getString("status") );

                zahtevi.add(k);
            }

            return zahtevi;
        }catch(SQLException e){

        }
    return null;
    }

    public Zahtev zahtev(int idZ) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select Z.*,K.naziv from zahtevi Z join knjige K on (Z.knjiga=K.idK) where Z.idZ=?")){

            stm.setInt(1, idZ);


            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new Zahtev(rs.getInt("idZ"),
                rs.getString("klijent"),
                rs.getInt("knjiga"),
                rs.getString("naziv"),
                rs.getString("opcije").split(","),
                rs.getDouble("racun"),
                rs.getDate("datum"),
                rs.getString("status") );

            }
        

        }catch(SQLException e){

        }
    return null;
    }

    public List<Opcija> opcije() {
        List<Opcija> opcije = new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select * from opcijekopirnice")){

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Opcija o = new Opcija(rs.getString("naziv"),
                rs.getString("kategorijausluge"),
                rs.getDouble("cena"));

                opcije.add(o);
            }

            return opcije;
        }catch(SQLException e){

        }
        return null;
    }

    public int azurirajZahtev(String opcije, int idZ, double racun, int idK) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement r = conn.prepareStatement("select strane from knjige where idK=?");
        PreparedStatement stm = conn.prepareStatement("update zahtevi set opcije=?, racun=? where idZ=?")){

            r.setInt(1, idK);
            ResultSet rs1=r.executeQuery();
            if(rs1.next()){
                int brojStrana=Integer.parseInt(rs1.getString(1));
                double konacanRacun=racun*brojStrana;
                stm.setString(1, opcije);
                stm.setDouble(2, konacanRacun);
                stm.setInt(3, idZ);

                return stm.executeUpdate();
            }else{
                return -2;
            }

        }catch(SQLException e){

        }
        return -1;
    }

    public List<ZahtevJoinKlijent> noviZahtevi() {
        List<ZahtevJoinKlijent> zahtevi= new ArrayList<>();
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("select Z.idZ, Kor.ime, Kor.prezime,K.idK,K.naziv,Z.opcije, Z.racun, Z.datum, Z.status from zahtevi Z join knjige K on (Z.knjiga=K.idK) join korisnici Kor on (Z.klijent=Kor.kor_ime) where status='nov'")){

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ZahtevJoinKlijent k = new ZahtevJoinKlijent(rs.getInt("idZ"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getInt("idK"),
                rs.getString("naziv"),
                rs.getString("opcije").split(","),
                rs.getDouble("racun"),
                rs.getDate("datum"),
                rs.getString("status") );

                zahtevi.add(k);
            }

            return zahtevi;
        }catch(SQLException e){

        }
    return null;
    }

    public int obrisiOpciju(String naziv, String kategorijausluge, double cena) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("delete from opcijekopirnice where naziv=? and kategorijausluge=? and cena=?")){

            stm.setString(1, naziv);
            stm.setString(2, kategorijausluge);
            stm.setDouble(3, cena);

            return stm.executeUpdate();


        }catch(SQLException e){

        }
        return -1;
    }

    public int promeniStatusZahteva(int idZ) {
        try(Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement("update zahtevi set status='prihvacen' where idZ=?")){

            stm.setInt(1, idZ);

            return stm.executeUpdate();


        }catch(SQLException e){

        }
        return -1;
    }

}
