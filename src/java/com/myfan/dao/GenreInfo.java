/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Genero;
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
    
    
    /**
     * Se obtienen los generos del sistema MyFan
     * @param connection conexion a la BD
     * @return Lista de los generos del sistema
     * @throws SQLException En caso de haber un error en la BD
     */
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
    
    
    /**
     * Se crean los generos del sistema MyFan
     * @param genero  que se desea crear en el sistema
     * @param connection conexion a la BD
     * @throws SQLException En caso de haber un error en la BD
     */
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
