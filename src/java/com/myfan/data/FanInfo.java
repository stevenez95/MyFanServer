/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.data;

import com.myfan.dto.Fan;
import com.myfan.dto.ResenaBanda;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class FanInfo {
    
    public FanInfo() {
    }
    
    public void seguirBanda(int idFan , int idBanda, Connection connection)throws SQLException{}
    
    public void dejarSeguirBanda(int idFan,  int id, Connection connection)throws SQLException{}
    
    public void desactivarFan(int idFan, Connection connection)throws SQLException{
        String query = "update fans set activo = not activo where idFan = ?;";
    }
    
    public void actualizarFan(Fan fan, Connection connection)throws SQLException{}
    
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
        connection.close();
    }
    
    public void verMisArtistas(int idFan, Connection connection)throws SQLException{
        String query = "select b.idBanda, b.nombreBanda \n" +
                "from bandas b \n" +
                "join seguidos s \n" +
                "on s.idBanda = b.idBanda \n" +
                "where s.idFan = ?;";
    }
    
    public void buscarArtistas(String nombre, String pais, String genero, Connection connection)throws SQLException{}
    
    public void rateBand(ResenaBanda resenaBanda, Connection connection)throws SQLException{
        String query = "insert into resenasbanda (idBanda,idFan,calificacion,comentario) value (?,?,?,?);";
    }
    
}
