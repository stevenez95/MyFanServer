/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.data;

import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import com.myfan.dto.ResenaDisco;
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
    
    public DiscInfo() {
    }
    
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
        String query = "select idDisco, nombre \n" +
                "from discografias \n" +
                "where idBanda = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idBanda);
        
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Discografia> discosList = new ArrayList<>();
        while(rs.next()){
            Discografia discografia = new Discografia();
            discografia.setIdBanda(rs.getInt("idDisco"));
            discografia.setNombre(rs.getString("nombre"));
            discosList.add(discografia);
        }
        connection.close();
        ps.close();
        return discosList;
    }
    
    public void getDiscInfo(int idDisco, Connection connection)throws SQLException{
        String query = "select nombre,descripcion,generoMusical,anioPublicacion,selloDiscografico \n" +
                "from discografias \n" +
                "where idDisco = ?;";
    }
    
    public void editDisc(Discografia discografia, int idDisco, Connection connection)throws SQLException{
        String query ="update discografias \n" +
                "set nombre=?,descripcion=?,generoMusical=?,anioPublicacion=?,selloDiscografico=? \n" +
                "where idDisco = ?;";
    }
    
    public void deleteDisc(int idDisco, Connection connection)throws SQLException{
        String query = "delete from discografias \n" +
                "where idDisco = ?;";
    }
    
    public void createSong(Cancion cancion, Connection connection){}
    
    public void getSongs(int idDisco, Connection connection)throws SQLException{
        String query = "select nombre, enVivo,bonus,limitada,link \n" +
                "from canciones \n" +
                "where idDisco = ?;";
    }
    
    public void editSong(Cancion cancion,int idCancion, Connection connection)throws SQLException{
        String query = "update canciones \n" +
                "set nombre=?,enVivo=?,bonus=?,limitada=?,link=? \n" +
                "where idCancion = ?;";
    }
    
    public void deleteSong(int idCancion, Connection connection)throws SQLException{
        String query = "delete from canciones \n" +
                "where idCancion = ?;";
    }
    
    public void rateDisc(ResenaDisco resenaDisco, Connection connection)throws SQLException{
        String query = "insert into resenasdisco (idDisco,idFan,calificacion,comentario) value (?,?,?,?);";
    }
    
    public void getDiscComments(int idDisco, Connection connection)throws SQLException{
        String query = "select comentario \n" +
                "from resenasdisco \n" +
                "where idDisco = ?";
    }
    
    public void getDiscRate(int  idDisco, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idDisco \n" +
                "from resenasdisco \n" +
                "where idDisco = ? \n" +
                "group by idDisco;";
    }
    
}
