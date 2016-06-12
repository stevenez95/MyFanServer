/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.dao;

import com.myfan.dto.Genero;
import com.myfan.dto.Resena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author steve_000
 */
public class GenreInfo {

    public GenreInfo() {
    }
    
    public ArrayList<Genero> verGeneros(Connection connection ) throws SQLException{
        String query = "select idGenero,nombre \n" +
                "from generos;";
        PreparedStatement ps = connection.prepareStatement(query);
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
    
    public void editarGenero(String nombre,int idGenero,Connection connection ) throws SQLException{
        String query = "update genero set nombre = ? \n" +
                "where idGenero = ?; ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, idGenero);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void borrarGenero(int idGenero, Connection connection) throws SQLException{
        String query = "delete from generos \n" +
                "where idGenero = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idGenero);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public void crearGenero(Genero genero , Connection connection) throws SQLException{
        String query = "insert into generos (idGenero,nombre) value (?,?);";
         PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, genero.getIdGenero());
        ps.setString(2, genero.getNombre());
        ps.execute();
        ps.close();
        connection.close();
    }
    
}
