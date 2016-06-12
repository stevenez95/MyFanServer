/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Resena;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, fan.getUsername());
        ps.setString(2, password);
        ps.setString(3, fan.getNombre());
        ps.setString(4, fan.getApellido());
        ps.setDate(5, fecha);
        ps.setDouble(6, fan.getFechaCreacion());
        ps.setString(7, fan.getGenero());
        ps.setString(8, fan.getPais());
        ps.setString(9, fan.getFotoPerfil());
        ps.execute();
        ps.close();
        connection.close();
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
    
    public void buscarArtistas(String nombre, String pais, String genero, Connection connection)throws SQLException{}
    
    public void rateBand(Resena resenaBanda, Connection connection)throws SQLException{
        String query = "insert into resenasbanda (idBanda,idFan,calificacion,comentario) value (?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaBanda.getId());
        ps.setInt(2, resenaBanda.getIdFan());
        ps.setInt(3, resenaBanda.getCalificacion());
        ps.setString(4, resenaBanda.getComentario()); 
        ps.execute();
        connection.close();
        ps.close();
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
    
}
