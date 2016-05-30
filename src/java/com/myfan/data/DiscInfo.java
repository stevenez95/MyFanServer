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
    
    public ArrayList<Discografia> getDiscInfo(int idDisco, Connection connection)throws SQLException{
        String query = "select nombre,descripcion,generoMusical,anioPublicacion,selloDiscografico \n" +
                "from discografias \n" +
                "where idDisco = ?;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Discografia> infoDiscosList = new ArrayList<>();
        while(rs.next()){
            Discografia discografia = new Discografia();
            discografia.setNombre(rs.getString("nombre"));
            discografia.setDescripcion(rs.getString("descripcion"));
            discografia.setGeneroMusical(rs.getString("generoMusical"));
            discografia.setAnioPublicacion(rs.getInt("anioPublicacion"));
            discografia.setSelloDiscografico(rs.getString("selloDiscografico"));
            infoDiscosList.add(discografia);
        }
        connection.close();
        ps.close();
        return infoDiscosList;
    }
    
    public void editDisc(Discografia discografia, int idDisco, Connection connection)throws SQLException{
        String query ="update discografias \n" +
                "set nombre=?,descripcion=?,generoMusical=?,anioPublicacion=?,selloDiscografico=? \n" +
                "where idDisco = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
    
    public void deleteDisc(int idDisco, Connection connection)throws SQLException{
        String query = "delete from discografias \n" +
                "where idDisco = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ps.execute();
    }
    
    public void createSong(Cancion cancion, Connection connection){
        
    }
    
    public ArrayList<Cancion> getSongs(int idDisco, Connection connection)throws SQLException{
        String query = "select nombre, enVivo,bonus,limitada,link \n" +
                "from canciones \n" +
                "where idDisco = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Cancion> cancionesList = new ArrayList<>();
        while(rs.next()){
            Cancion cancion = new Cancion();
            cancion.setNombre(rs.getString("nombre"));
            cancion.setEnVivo(rs.getBoolean("enVivo"));
            cancion.setBonus(rs.getBoolean("bonus"));
            cancion.setLimitada(rs.getBoolean("limitada"));
            cancion.setLink(rs.getString("link"));
            cancionesList.add(cancion);
        }
        connection.close();
        ps.close();
        return cancionesList;
    }
    
    public void editSong(Cancion cancion,int idCancion, Connection connection)throws SQLException{
        String query = "update canciones \n" +
                "set nombre=?,enVivo=?,bonus=?,limitada=?,link=? \n" +
                "where idCancion = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idCancion);
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
    }
    
    public void rateDisc(ResenaDisco resenaDisco, Connection connection)throws SQLException{
        String query = "insert into resenasdisco (idDisco,idFan,calificacion,comentario) value (?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, resenaDisco.getIdDisco());
        ps.setInt(2, resenaDisco.getIdFan());
        ps.setInt(3, resenaDisco.getCalificacion());
        ps.setString(4, resenaDisco.getComentario());
        ps.execute();
        ps.close();
        connection.close();
    }
    
    public ArrayList<ResenaDisco> getDiscComments(int idDisco, Connection connection)throws SQLException{
        String query = "select comentario \n" +
                "from resenasdisco \n" +
                "where idDisco = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        ArrayList<ResenaDisco> comentariosList = new ArrayList<>();
        while(rs.next()){
            ResenaDisco resenaDisco = new ResenaDisco();
            resenaDisco.setComentario(rs.getString("comentario"));
            comentariosList.add(resenaDisco);
        }
        connection.close();
        ps.close();
        return comentariosList;
    }
    
    public ArrayList<ResenaDisco> getDiscRate(int  idDisco, Connection connection)throws SQLException{
        String query = "select avg(calificacion) as promedio, idDisco \n" +
                "from resenasdisco \n" +
                "where idDisco = ? \n" +
                "group by idDisco;";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idDisco);
        ResultSet rs = ps.executeQuery();
        ArrayList<ResenaDisco> promedioList = new ArrayList<>();
        while(rs.next()){
            ResenaDisco resenaDisco = new ResenaDisco();
            resenaDisco.setCalificacion(rs.getInt("promedio"));
            resenaDisco.setIdDisco(rs.getInt("idDisco"));
            promedioList.add(resenaDisco);
        }
        connection.close();
        ps.close();
        return promedioList;
    }
    
}
