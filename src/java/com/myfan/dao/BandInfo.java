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
import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class BandInfo {
    
    public BandInfo() {}
    
    public void registrarBanda(Banda banda,Connection connection) throws SQLException{
        String query = "insert into Bandas (username,password,nombreBanda,anioCreacion,hashtag,biografia,fechaCreacion,pais,integrantes,fotoPerfil) value (?,?,?,?,?,?,?,?,?,?);";
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
        ps.close();
        connection.close();
    }
    
    public void actualizarBanda(Banda banda,int idBanda, Connection connection)throws SQLException{}
    
    public void desactivarBanda(int idBanda,Connection connection)throws SQLException{
        String query = "update bandas set activo = not activo where idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public int getCantidadSeguidores(int idBanda, Connection connection)throws SQLException{
        String query ="select count(*) as total\n" +
                "from seguidos s \n" +
                "join fans f\n" +
                "on s.idFan = f.idFan\n" +
                "where s.idBanda = ? and f.activo = 1;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        int seguidores = rs.getInt("total");
        
        ps.close();
        connection.close();
        return seguidores;
        
    }
    
    public ArrayList<Resena> getBandComments(int idBanda, Connection connection)throws SQLException{
        String query = "select comentario  \n" +
                "from resenasbanda \n" +
                "where idBanda = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Resena> bandaComentList = new ArrayList<>();
        while(rs.next()){
            Resena resenabanda = new Resena();
            resenabanda.setComentario(rs.getString("comentario"));
            bandaComentList.add(resenabanda);
        }
        connection.close();
        ps.close();
        return bandaComentList;
    }
    
    public int getBandRate(int idBanda, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idBanda \n" +
                "from resenasbanda \n" +
                "where idBanda = ? \n" +
                "group by idBanda;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int promedio = rs.getInt("promedio");
        connection.close();
        ps.close();
        return promedio;
    }

    public Banda getBandInfo(int idBanda, Connection connection) throws SQLException {
        String query = "select b.idBanda, b.username, b.nombreBanda, b.hashtag, b.pais, b.activo\n" +
                "from bandas b\n" +
                "where b.idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        Banda banda = new Banda();
        while(rs.next()){
            banda.setIdBanda(rs.getInt("idBanda"));
            banda.setUsername(rs.getString("username"));
            banda.setNombreBanda(rs.getString("nombreBanda"));
            banda.setHashtag(rs.getString("hashtag"));
            banda.setActivo(rs.getBoolean("activo"));
            banda.setPais(rs.getString("pais"));
        }
        connection.close();
        ps.close();
        return banda;
        
    }
        
}
