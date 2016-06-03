/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.model;

import com.myfan.connection.DataBaseConnect;
import com.myfan.data.*;
import com.myfan.dto.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ProjectManager {
    
    /*** AUTENTICACION ***/
    
    /*fan*/
    
    public void registrarFan(Fan fan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.registrarFan(fan, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void seguirBanda(int idFan , int idBanda) throws SQLException{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        try {
            fanInfo.seguirBanda(idFan, idBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dejarSeguirBanda(int idFan,  int idBanda) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.dejarSeguirBanda(idFan, idBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desactivarFan(int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.desactivarFan(idFan, database.getConnection());            
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public void actualizarFan(Fan fan,int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.actualizarFan(fan,idFan, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Banda> verMisArtistas(int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            return  fanInfo.verMisArtistas(idFan, database.getConnection());
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void buscarArtistas(String nombre, String pais, String genero){
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        try {
            fanInfo.buscarArtistas(nombre, pais,genero, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    public void rateBand(ResenaBanda resenaBanda){
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        try {
            fanInfo.rateBand(resenaBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Evento> getEventosFan(int idFan) throws Exception{
        
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo  = new EventInfo();
        return eventInfo.getEventosFan(idFan, database.getConnection());
    }
    
    public void rateEvent(ResenaConcierto resenaConcierto) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo  = new EventInfo();
        eventInfo.rateEvent(resenaConcierto, database.getConnection());
    }
    
    
    public ArrayList<Noticia> getNoticiasFan(int idFan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo  newsInfo  = new NewsInfo();
        return newsInfo.getNoticiasFan(idFan, database.getConnection());
    }
    
    /*banda*/
    
    public void registrarBanda(Banda banda) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            BandInfo bandInfo = new BandInfo();
            bandInfo.registrarBanda(banda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarBanda(Banda banda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        bandInfo.actualizarBanda(banda, database.getConnection());
    }
    
    public void desactivarBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        bandInfo.desactivarBanda(idBanda, database.getConnection());
    }
    
    public int getCantidadSeguidores(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getCantidadSeguidores(idBanda, database.getConnection());
    }
    
    public ArrayList<ResenaBanda> getBandComments(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getBandComments(idBanda, database.getConnection());
    }
    
    public int getBandRate(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getBandRate(idBanda, database.getConnection());
        
    }
    
    public void crearEvento(Evento evento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        eventInfo.crearEvento(evento, database.getConnection());
    }
    
    public ArrayList<Evento> getEventosBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventosBanda(idBanda, database.getConnection());  
    }
    
    public void cancelEvent(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        eventInfo.cancelEvent(idEvento, database.getConnection());
    }
    
    public ArrayList<ResenaConcierto> getEventComments(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventComments(idEvento, database.getConnection());
        }
    
    public int getEventRate(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventRate(idEvento, database.getConnection());
        }
    
    public void crearNoticia(Noticia noticia) throws Exception{
     DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.crearNoticia(noticia, database.getConnection());
    }
    
    public ArrayList<Noticia> getNoticiasBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        return newsInfo.getNoticiasBanda(idBanda, database.getConnection());
        
        }
    
    public void deleteNews(int idNoticia) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.deleteNews(idNoticia, database.getConnection());
    }
    
    /******/
    public String login (String username, String password) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        UserInfo project = new UserInfo();
        return project.login(username,password, database.getConnection());
    }
    
    public ArrayList<Genero> verGeneros() throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        return genreInfo.verGeneros(database.getConnection());
        }
    
    public void editarGenero(String nombre,int idGenero) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.editarGenero(nombre,idGenero,database.getConnection());
    }
    
    public void borrarGenero(int idGenero) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.borrarGenero(idGenero,database.getConnection());
    }
    
    public void crearGenero(Genero genero) throws Exception{
    DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.crearGenero(genero,database.getConnection());
    }
    
    /*discos*/
    
    public void createDisc(Discografia discografia) throws SQLException, Exception{
     DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.createDisc(discografia,database.getConnection());
    
    }
    
    public ArrayList<Discografia> getDiscs(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscs(idBanda,database.getConnection());
    }
    
    public ArrayList<Discografia> getDiscInfo(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscInfo(idDisco,database.getConnection());  
    }
    
    public void editDisc(Discografia discografia, int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.editDisc(discografia, idDisco,database.getConnection());
    }
    
    public void deleteDisc(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.deleteDisc(idDisco,database.getConnection());
    }
    
    public void createSong(Cancion cancion) throws Exception{ 
    DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.createSong(cancion,database.getConnection());
    }
    
    public ArrayList<Cancion> getSongs(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getSongs(idDisco,database.getConnection());
     
    }
    
    public void editSong(Cancion cancion,int idCancion)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.editSong(cancion,idCancion,database.getConnection());
        
    }
    
    public void deleteSong(int idCancion)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.deleteSong(idCancion,database.getConnection());
    }
    
    public void rateDisc(ResenaDisco resenaDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.rateDisc(resenaDisco,database.getConnection());
    }
    
    public ArrayList<ResenaDisco> getDiscComments(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscComments(idDisco,database.getConnection());
    }
    
    public int getDiscRate(int  idDisco)throws SQLException, Exception{
       DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscRate(idDisco,database.getConnection());
    }
    
    
    
}
