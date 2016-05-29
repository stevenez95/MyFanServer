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
import java.sql.SQLException;

/**
 *
 * @author Steven
 */
public class DiscInfo {

    public DiscInfo() {
    }
    
    public void createDisc(Discografia discografia, Connection connection){}
    
    public void getDiscs(int idBanda, Connection connection)throws SQLException{
        String query = "select idDisco, nombre \n" +
                "from discografias \n" +
                "where idBanda = ?;";
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
