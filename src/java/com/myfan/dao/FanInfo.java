/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Genero;
import com.myfan.dto.Resena;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class FanInfo {
    
    public FanInfo() {
    }
    
    public void seguirBanda(int idFan , int idBanda, Connection connection)throws SQLException{
        String query = "insert into seguidos (idFan, idBanda) value (?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public void dejarSeguirBanda(int idFan,  int idBanda, Connection connection)throws SQLException{
        String query = "delete from seguidos where idFan = ? and idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public void desactivarFan(int idFan, Connection connection)throws SQLException{
        String query = "update fans set activo = not activo where idFan = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void actualizarFan(Fan fan,int idFan, Connection connection)throws SQLException{
        String query1 = "update fans set password = ?,nombre= ?,apellido= ?,fechaNac= ?,genero= ?,pais= ?,fotoPerfil= ? where idFan = ?;";
        String query2 = "update fans set nombre= ?,apellido= ?,fechaNac= ?,genero= ?,pais= ?,fotoPerfil= ? where idFan = ?;";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(fan.getFechaNac()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement ps;
        String pass="";
        if(fan.getPassword()==null){
            ps = connection.prepareStatement(query2);
            ps.setString(1, fan.getNombre());
            ps.setString(2, fan.getApellido());
            ps.setDate(3, fecha);
            ps.setString(4, fan.getGenero());
            ps.setString(5, fan.getPais());
            ps.setString(6, fan.getFotoPerfil());
            ps.setInt(7, idFan);
        }
        else{
            ps = connection.prepareStatement(query1);
            pass = PasswordEncrypt.hashPassword(fan.getPassword());
            ps.setString(1, pass);
            ps.setString(2, fan.getNombre());
            ps.setString(3, fan.getApellido());
            ps.setDate(4, fecha);
            ps.setString(5, fan.getGenero());
            ps.setString(6, fan.getPais());
            ps.setString(7, fan.getFotoPerfil());
            ps.setInt(8, idFan);
        }
        
        ingresarGenerosFan(fan.getGeneros(), idFan, connection);
        
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void registrarFan(Fan fan,Connection connection) throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(fan.getFechaNac()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "Insert into fans (username,password,nombre,apellido,fechaNac,fechaCreacion,genero,pais,fotoPerfil) value (?,?,?,?,?,?,?,?,?);";
        String password = PasswordEncrypt.hashPassword(fan.getPassword());
        PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, fan.getUsername());
        ps.setString(2, password);
        ps.setString(3, fan.getNombre());
        ps.setString(4, fan.getApellido());
        ps.setDate(5, fecha);
        ps.setDouble(6, fan.getFechaCreacion());
        ps.setString(7, fan.getGenero());
        ps.setString(8, fan.getPais());
        ps.setString(9, "http://localhost:8080/MyFanServer/uploads/"+fan.getFotoPerfil());
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        ingresarGenerosFan(fan.getGeneros(), rs.getInt(1), connection);
        
        ps.close();
        connection.close();
    }
    
    private void ingresarGenerosFan(int[] generos, int idFan, Connection c)throws SQLException{
        if(generos.length == 0) return;
        String delete = "delete from fangeneros where idFan = ?;";
        PreparedStatement ps1 = c.prepareStatement(delete);
        ps1.setInt(1, idFan);
        ps1.execute();
        
        String insert = "insert into fangeneros (idGenero, idFan) value (?,?);";
        for (int i = 0; i < generos.length; i++) {
            PreparedStatement ps2 = c.prepareStatement(insert);
            ps2.setInt(1, generos[i]);
            ps2.setInt(2, idFan);
            ps2.execute();
            ps2.close();
        }
        
        ps1.close();
        c.close();
        
    }
    
    public ArrayList<Banda> verMisArtistas(int idFan, Connection connection)throws SQLException{
        String query = "select b.idBanda, b.nombreBanda \n" +
                "from bandas b \n" +
                "join seguidos s \n" +
                "on s.idBanda = b.idBanda \n" +
                "where s.idFan = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        ArrayList<Banda> artistasList = new ArrayList<>();
        while(rs.next()){
            Banda banda = new Banda();
            banda.setIdBanda(rs.getInt("idBanda"));
            banda.setNombreBanda(rs.getString("nombreBanda"));
            artistasList.add(banda);
        }
        connection.close();
        ps.close();
        return artistasList;
        
    }
    
    public boolean esSeguidor (int idFan, int idBanda, Connection connection)throws SQLException{
        String query = "Select * from seguidos where idfan=? And idbanda=?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ps.setInt(2, idBanda);
        ResultSet rs = ps.executeQuery();
        boolean bandera = false;
        while(rs.next()){
            bandera = true;
        }
        connection.close();
        ps.close();
        
        if(bandera)
            return true;
        else
            return false;
    }      
        
    public void buscarArtistas(String nombre, String pais, String genero, Connection connection)throws SQLException{}
    
    public void rateBand(Resena resenaBanda, Connection connection)throws SQLException{
        String delete = "delete from resenasbanda where idBanda = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaBanda.getId());
        ps2.setInt(2, resenaBanda.getIdFan());
        ps2.execute();
        String query = "insert into resenasbanda (idBanda,idFan,calificacion,comentario,fecha) values (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaBanda.getId());
        ps.setInt(2, resenaBanda.getIdFan());
        ps.setInt(3, resenaBanda.getCalificacion());
        ps.setString(4, resenaBanda.getComentario());
        ps.setDouble(5, resenaBanda.getFecha());
        ps.execute();
        ps.close();
        ps2.close();
        connection.close();   
    }
    
    public void rateDisc(Resena resenaDisco, Connection connection)throws SQLException{
        String delete = "delete from resenasdisco where idDisco = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaDisco.getId());
        ps2.setInt(2, resenaDisco.getIdFan());
        ps2.execute();
        String query = "insert into resenasdisco (idDisco,idFan,calificacion,comentario,fecha) values (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaDisco.getId());
        ps.setInt(2, resenaDisco.getIdFan());
        ps.setInt(3, resenaDisco.getCalificacion());
        ps.setString(4, resenaDisco.getComentario());
        ps.setDouble(5, resenaDisco.getFecha());
        ps.execute();
        connection.close();
        ps2.close();
        ps.close();
    }
    
    public void rateEvent(Resena resenaEvento, Connection connection)throws SQLException{
        String delete = "delete from resenasconcierto where idEvento = ? and idFan = ?";
        PreparedStatement ps2 = connection.prepareStatement(delete);
        ps2.setInt(1, resenaEvento.getId());
        ps2.setInt(2, resenaEvento.getIdFan());
        ps2.execute();
        String query = "insert into resenasconcierto (idEvento,idFan,calificacion,comentario,fecha) value (?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaEvento.getId());
        ps.setInt(2, resenaEvento.getIdFan());
        ps.setInt(3, resenaEvento.getCalificacion());
        ps.setString(4, resenaEvento.getComentario());
        ps.setDouble(5, resenaEvento.getFecha());
        ps.execute();
        ps.close();
        ps2.close();
        connection.close();
    }
    
    public Fan getFanInfo(int idFan, Connection connection)throws SQLException{
        String query = "select f.idFan,f.username, f.genero, floor(datediff(now(),f.fechaNac)/365) as age, count(s.idFan) as total, f.activo, f.pais,f.nombre,f.apellido,f.fechaNac,f.fotoPerfil \n" +
                "from fans f \n" +
                "join seguidos s \n" +
                "on s.idFan = f.idFan \n" +
                "where f.idFan = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        ResultSet rs = ps.executeQuery();
        Fan fan = new Fan();
        while(rs.next()){
            fan.setIdFan(rs.getInt("idFan"));
            fan.setUsername(rs.getString("username"));
            fan.setGenero(rs.getString("genero"));
            fan.setEdad(rs.getInt("age"));
            fan.setSiguiendo(rs.getInt("total"));
            fan.setActivo(rs.getBoolean("activo"));
            fan.setPais(rs.getString("pais"));
            fan.setNombre(rs.getString("nombre"));
            fan.setApellido(rs.getString("apellido"));
            fan.setFechaNac(rs.getDate("fechaNac").toString());
            fan.setFotoPerfil(rs.getString("fotoPerfil"));
        }
        connection.close();
        ps.close();
        return fan;
        
    }
    
    public ArrayList<Genero> getFanGeneros (int idFan, Connection connection)throws SQLException{
        String query = "select g.nombre,g.idGenero\n" +
                "from fangeneros bg\n" +
                "join fan b\n" +
                "on bg.idFan = b.idFan\n" +
                "join generos g\n" +
                "on g.idGenero = bg.idGenero\n" +
                "where bg.idFan = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idFan);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Genero> generosList = new ArrayList<>();
        while(rs.next()){
            Genero genero = new Genero();
            genero.setIdGenero(rs.getInt("idGenero"));
            genero.setNombre(rs.getString("nombre"));
            generosList.add(genero);
        }
        
        connection.close();
        ps.close();
        return generosList;
    }

    
}
