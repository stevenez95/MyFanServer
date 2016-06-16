/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Message;
import com.myfan.security.JwtManager;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class UserInfo {
    
    public UserInfo() {
    }
    
    public Message login(String username, String password, Connection connection) throws SQLException {
        JwtManager jwtManager = new JwtManager();
        String query = "select idBanda,username, password from Bandas where username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        Message m = new Message();
        
        if (rs.next()){
            if (PasswordEncrypt.validatePassword(password, rs.getString("password"))){
                m.setSuccess(true);
                m.setId(rs.getInt("idBanda"));
                m.setTipo("banda");
                String token = jwtManager.jwtGenerate();
                m.setMensaje(token);
                connection.close();
                return m;
            }
            else{
                System.out.println("contrasena no es valida");
                connection.close();
                throw new SQLException(null, null, 1);
            }
        }
        
        else{
            String query2 = "select idFan, username, password from Fans where username = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setString(1, username);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()){
                if (PasswordEncrypt.validatePassword(password, rs2.getString("password"))){
                    m.setSuccess(true);
                    m.setId(rs2.getInt("idFan"));
                    m.setTipo("fan");
                    String token = jwtManager.jwtGenerate();
                    m.setMensaje(token);
                    connection.close();
                    return m;
                }
                else{
                    System.out.println("contrasena no es valida");
                    connection.close();
                    throw new SQLException(null, null, 1);
                }
            }
            else{
                connection.close();
                System.out.println("usuario no existe");
                throw new SQLException(null, null, 1);
            }
        }
    }
    

}
