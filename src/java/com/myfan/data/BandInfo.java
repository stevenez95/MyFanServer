/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.data;

import com.myfan.dto.Banda;
import com.myfan.security.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Steven
 */
public class BandInfo {
    
    public BandInfo() {
    }
    
    public void registrarBanda(Banda banda,Connection connection) throws SQLException{
        String query = "Insert into Bandas (username,password,nombreBanda,anioCreacion,hashtag,biografia,fechaCreacion,pais,integrantes,fotoPerfil) value (?,?,?,?,?,?,?,?,?,?);";
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
        ps.setString(10, banda.getFotoPerfil());
        ps.execute();
        connection.close();
    }
    
    public void actualizarBanda(Banda banda, Connection connection){}
    
    public void desactivarBanda(int idBanda,Connection connection){
        String query = "update bandas set activo = not activo where idBanda = ?;";
    }
    
    public void getCantidadSeguidores(int idBanda, Connection connection){
        String query ="select count(*) as total\n" +
                "from seguidos s \n" +
                "join fans f\n" +
                "on s.idFan = f.idFan\n" +
                "where s.idBanda = ? and f.activo = 1;";
    }
    
}
