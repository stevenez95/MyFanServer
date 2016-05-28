/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.data;

import com.myfan.dto.Cancion;
import com.myfan.dto.Discografia;
import java.sql.Connection;

/**
 *
 * @author Steven
 */
public class DiscsInfo {

    public DiscsInfo() {
    }
    
    public void getDiscs(int idBanda, Connection connection){
        String query = "select idDisco, nombre \n" +
                "from discografias \n" +
                "where idBanda = ?;";
    }
    
    public void getDiscInfo(int idDisco, Connection connection){
        String query = "select nombre,descripcion,generoMusical,anioPublicacion,selloDiscografico \n" +
                "from discografias \n" +
                "where idDisco = ?;";
    }
    
    public void editDisc(Discografia discografia, int idDisco, Connection connection){
        String query ="update discografias \n" +
                "set nombre=?,descripcion=?,generoMusical=?,anioPublicacion=?,selloDiscografico=? \n" +
                "where idDisco = ?;";
    }
    
    public void deleteDisc(int idDisco, Connection connection){
        String query = "delete from discografias \n" +
                "where idDisco = ?;";
    }
    
    public void getSongs(int idDisco, Connection connection){
        String query = "select nombre, enVivo,bonus,limitada,link \n" +
                "from canciones \n" +
                "where idDisco = ?;";
    }
    
    public void editSong(Cancion cancion,int idCancion, Connection connection){
        String query = "update canciones \n" +
                "set nombre=?,enVivo=?,bonus=?,limitada=?,link=? \n" +
                "where idCancion = ?;";
    }
    
    public void deleteSong(int idCancion, Connection connection){
        String query = "delete from canciones \n" +
                "where idCancion = ?;";
    }
    
}
