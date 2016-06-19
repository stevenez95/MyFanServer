/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.dao;

import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import com.myfan.dto.Resena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven
 */
public class DiscInfo {
    
    public DiscInfo() {}
    
    public void createDisc(Discografia discografia, Connection connection) throws SQLException{
        String query = "insert into discografias (nombre,descripcion,generoMusical,anioPublicacion,selloDiscografico,idBanda)\n" +
                "value (?,?,?,?,?,?);";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, discografia.getNombre());
        ps.setString(2, discografia.getDescripcion());
        ps.setString(3, discografia.getGeneroMusical());
        ps.setInt(4, discografia.getAnioPublicacion());
        ps.setString(5, discografia.getSelloDiscografico());
        ps.setInt(6, discografia.getIdBanda());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Discografia> getDiscs(int idBanda, Connection connection)throws SQLException{
        String query = "select idDiscografia, nombre \n" +
                "from discografias \n" +
                "where idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        ResultSet rs = ps.executeQuery();
        ArrayList<Discografia> discosList = new ArrayList<>();
        while(rs.next()){
            Discografia discografia = new Discografia();
            discografia.setIdDiscografia(rs.getInt("idDiscografia"));
            discografia.setNombre(rs.getString("nombre"));
            discosList.add(discografia);
        }
        connection.close();
        ps.close();
        return discosList;
    }
    
    public Discografia getDiscInfo(int idDisco, Connection connection)throws SQLException{
        String query = "select idDiscografia,nombre,descripcion,generoMusical,anioPublicacion,selloDiscografico \n" +
                "from discografias \n" +
                "where idDiscografia = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        
        Discografia discografia = new Discografia();
        while(rs.next()){
            discografia.setIdDiscografia(rs.getInt("idDiscografia"));
            discografia.setNombre(rs.getString("nombre"));
            discografia.setDescripcion(rs.getString("descripcion"));
            discografia.setGeneroMusical(rs.getString("generoMusical"));
            discografia.setAnioPublicacion(rs.getInt("anioPublicacion"));
            discografia.setSelloDiscografico(rs.getString("selloDiscografico"));
        }
        connection.close();
        ps.close();
        return discografia;
    }
    
    public void editDisc(Discografia discografia, int idDisco, Connection connection)throws SQLException{
        String query ="update discografias \n" +
                "set nombre=?,descripcion=?,generoMusical=?,anioPublicacion=?,selloDiscografico=? \n" +
                "where idDiscografia = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, discografia.getNombre());
        ps.setString(2, discografia.getDescripcion());
        ps.setString(3, discografia.getGeneroMusical());
        ps.setInt(4, discografia.getAnioPublicacion());
        ps.setString(5, discografia.getSelloDiscografico());
        ps.setInt(6, idDisco);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void deleteDisc(int idDisco, Connection connection)throws SQLException{
        String query = "delete from discografias \n" +
                "where idDiscografia = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public void createSong(Cancion cancion, Connection connection)throws SQLException{   
        String query = "insert into canciones ( nombre, enVivo, bonus, link, limitada, idDisco, duracion)\n" +
                "value (?,?,?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, cancion.getNombre());
        ps.setBoolean(2, cancion.isEnVivo());
        ps.setBoolean(3, cancion.isBonus());
        ps.setString(4, cancion.getLinkVid());
        ps.setBoolean(5, cancion.isLimitada());
        ps.setInt(6, cancion.getIdDisco());
        ps.setString(7, cancion.getDuracion());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Cancion> getSongs(int idDisco, Connection connection)throws SQLException{
        String query = "select idCancion,nombre, enVivo,bonus,limitada,link,duracion \n" +
                "from canciones \n" +
                "where idDisco = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Cancion> cancionesList = new ArrayList<>();
        while(rs.next()){
            Cancion cancion = new Cancion();
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setNombre(rs.getString("nombre"));
            cancion.setEnVivo(rs.getBoolean("enVivo"));
            cancion.setBonus(rs.getBoolean("bonus"));
            cancion.setLimitada(rs.getBoolean("limitada"));
            cancion.setLinkVid(rs.getString("link"));
            cancion.setDuracion(rs.getString("duracion"));
            cancionesList.add(cancion);
        }
        connection.close();
        ps.close();
        return cancionesList;
    }
    
    public Cancion getSong(int idCancion, Connection connection)throws SQLException{
        String query = "select idCancion,nombre, enVivo,bonus,limitada,link,duracion \n" +
                "from canciones \n" +
                "where idCancion = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idCancion);
        ResultSet rs = ps.executeQuery();
        Cancion cancion = new Cancion();
        while(rs.next()){
            cancion.setIdCancion(rs.getInt("idCancion"));
            cancion.setNombre(rs.getString("nombre"));
            cancion.setEnVivo(rs.getBoolean("enVivo"));
            cancion.setBonus(rs.getBoolean("bonus"));
            cancion.setLimitada(rs.getBoolean("limitada"));
            cancion.setLinkVid(rs.getString("link"));
            cancion.setDuracion(rs.getString("duracion"));
        }
        return cancion;
    }
    
    public void editSong(Cancion cancion,int idCancion, Connection connection)throws SQLException{
        String query = "update canciones \n" +
                "set nombre=?,enVivo=?,bonus=?,limitada=?,link=?, duracion=? \n" +
                "where idCancion = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, cancion.getNombre());
        ps.setBoolean(2, cancion.isEnVivo());
        ps.setBoolean(3, cancion.isBonus());
        ps.setBoolean(4, cancion.isLimitada());
        ps.setString(5, cancion.getLinkVid());
        ps.setString(6, cancion.getDuracion());
        ps.setInt(7, idCancion);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void deleteSong(int idCancion, Connection connection)throws SQLException{
        String query = "delete from canciones \n" +
                "where idCancion = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idCancion);
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<Resena> getDiscComments(int idDisco, Connection connection)throws SQLException{
        String query = "select r.comentario, f.username, r.fecha\n" +
                "from resenasdisco r \n" +
                "join fans f\n" +
                "on f.idFan = r.idFan\n" +
                "where r.idDisco = ? \n"
                + "order by r.fecha desc \n"
                + "limit 6;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        ArrayList<Resena> comentariosList = new ArrayList<>();
        while(rs.next()){
            Resena resenaDisco = new Resena();
            resenaDisco.setComentario(rs.getString("comentario"));
            resenaDisco.setCreadoPor(rs.getString("username"));
            resenaDisco.setFecha(rs.getDouble("fecha"));
            comentariosList.add(resenaDisco);
        }
        connection.close();
        ps.close();
        return comentariosList;
    }
    
    public float getDiscRate(int  idDisco, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idDisco \n" +
                "from resenasdisco \n" +
                "where idDisco = ? \n" +
                "group by idDisco;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        float promedio = 0;
        while(rs.next()){
            promedio= rs.getFloat("promedio");
        }
        connection.close();
        ps.close();
        return promedio;
    }
    
    
    
}
