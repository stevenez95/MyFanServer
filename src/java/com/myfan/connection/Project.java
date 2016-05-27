/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.connection;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Project {
    public void registrarFan(Fan fan,Connection connection) throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fecha = null;
        try {
            fecha = new java.sql.Date(format.parse(fan.getFechaNac()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "Insert into fans (username,password,nombre,apellido,fechaNac,fechaCreacion,genero,pais) value (?,?,?,?,?,?,?,?);";
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
        ps.execute();
        connection.close();
    }
    
    public void registrarBanda(Banda banda,Connection connection) throws SQLException{
        String query = "Insert into Bandas (username,password,nombreBanda,anioCreacion,hashtag,biografia,fechaCreacion,pais,integrantes) value (?,?,?,?,?,?,?,?,?);";
        String password = PasswordEncrypt.hashPassword(banda.getPassword());
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, banda.getUsername());
        ps.setString(2, password);
        ps.setString(3, banda.getNombreBanda());
        ps.setString(4, banda.getAnioCreacion());
        ps.setString(5, banda.getHashtag());
        ps.setString(6, banda.getBiografia());
        ps.setDouble(7, banda.getFechaCreacion());
        ps.setString(8, banda.getPais());
        ps.setString(9, banda.getIntegrantes());
        ps.execute();
        connection.close();
    }
    
    public void login(String username, String password, Connection connection) throws SQLException {
        String query = "select username, password from Bandas where username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()){
            if (PasswordEncrypt.validatePassword(password, rs.getString("password"))){
                Banda banda = new Banda();
                banda.setUsername(rs.getString("username"));
            }
            else{
                System.out.println("contrasena no es valida");
            }
        }
        
        else{
            String query2 = "select username, password from Fans where username = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setString(1, username);           
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()){
                if (PasswordEncrypt.validatePassword(password, rs2.getString("password"))){
                    Fan fan = new Fan();
                    fan.setUsername(rs2.getString("username"));
                }
                else{
                    System.out.println("contrasena no es valida");
                }
            }
            else{
                System.out.println("usuario no existe");
            }
        }
        connection.close();
        
    }
    
}
