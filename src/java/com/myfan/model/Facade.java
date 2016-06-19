/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.model;

import com.myfan.dao.UserInfo;
import com.myfan.dao.DiscInfo;
import com.myfan.dao.GenreInfo;
import com.myfan.dao.EventInfo;
import com.myfan.dao.NewsInfo;
import com.myfan.dao.BandInfo;
import com.myfan.dao.FanInfo;
import com.myfan.connection.DataBaseConnect;
import com.myfan.connection.IConstantes;
import com.myfan.dao.PaisesInfo;
import com.myfan.dto.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
public class Facade {
    
    /*** AUTENTICACION ***/
    
    /*fan*/
    
    public Response registrarFan(Fan fan){
        Message message = new Message();
        try {
            Helper helper = new Helper();
            helper.registarFan(fan);
            return Response.ok().build();
        }catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            message.setSuccess(false);
            message.setMensaje(IConstantes.USUARIO_EXISTE);
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.serverError().entity(message).build();
            message.setMensaje(IConstantes.ERROR);
            return Response.serverError().entity(message).build();
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            message.setSuccess(false);
            message.setMensaje(IConstantes.ERROR);
            return Response.serverError().entity(message).build();
        }
    }
    
    public void seguirBanda(int idFan , int idBanda) throws SQLException{
        DataBaseConnect database = new DataBaseConnect();
        Helper helper = new Helper();
        FanInfo fanInfo = new FanInfo();
        try {
            fanInfo.seguirBanda(idFan, idBanda, database.getConnection());
            helper.publicarSeguimiento(idFan, idBanda);
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dejarSeguirBanda(int idFan,  int idBanda) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.dejarSeguirBanda(idFan, idBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desactivarFan(int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.desactivarFan(idFan, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Response actualizarFan(Fan fan,int idFan){
        try {
            Helper helper = new Helper();
            helper.actualizarFan(fan, idFan);
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(IConstantes.m).build();
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
    
    public ArrayList<Banda> buscarArtistas(String nombre, String pais, String genero) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        
        return fanInfo.buscarArtistas(nombre, pais,genero, database.getConnection());
        
    }
    
    public void rateBand(Resena resenaBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        Helper helper = new Helper();
        FanInfo fanInfo = new FanInfo();
        fanInfo.rateBand(resenaBanda, database.getConnection());
        helper.publicarCalifcacion(resenaBanda);
    }
    
    public void rateDisc(Resena resenaDisco) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fanInfo.rateDisc(resenaDisco, database.getConnection());
    }
    
    public ArrayList<Evento> getEventosFan(int idFan) throws Exception{
        
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo  = new EventInfo();
        return eventInfo.getEventosFan(idFan, database.getConnection());
    }
    
    public void rateEvent(Resena resenaEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo   = new FanInfo();
        fanInfo.rateEvent(resenaEvento, database.getConnection());
    }
    
    public ArrayList<Noticia> getNoticiasFan(int idFan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo  newsInfo  = new NewsInfo();
        return newsInfo.getNoticiasFan(idFan, database.getConnection());
    }
    
    /*banda*/
    
    public Response registrarBanda(Banda banda){
        Message message = new Message();
        try {
            Helper helper = new Helper();
            helper.registarBanda(banda);
            return Response.ok().build();
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            message.setSuccess(false);
            message.setMensaje(IConstantes.USUARIO_EXISTE);
            if(ex.getErrorCode() == 1048 || ex.getErrorCode() == 1062) return Response.serverError().entity(message).build();
            message.setMensaje(IConstantes.ERROR);
            return Response.serverError().entity(message).build();
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            message.setSuccess(false);
            message.setMensaje(IConstantes.ERROR);
            return Response.serverError().entity(message).build();
        }
    }
    
    public Response actualizarBanda(Banda banda, int idBanda){
        try {
            Helper helper = new Helper();
            helper.actualizarBanda(banda, idBanda);
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(IConstantes.m).build();
        }
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
    /*
    public int getCalificacionPromedioDiscos(int idBanda) throws Exception{
    DataBaseConnect database = new DataBaseConnect();
    BandInfo bandInfo   = new BandInfo();
    return bandInfo.getCalificacionPromedioDiscos(idBanda, database.getConnection());
    }
    */
    public ArrayList<Resena> getBandComments(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getBandComments(idBanda, database.getConnection());
    }
    
    public int getCantidadEventos(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getCantidadEventos(idBanda, database.getConnection());
    }
    
    public ArrayList<Evento> getUltimosEventos(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getUltimosEventos(idBanda, database.getConnection());
    }
    
    public float getBandRate(int idBanda) throws Exception{
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
    
    public ArrayList<Resena> getEventComments(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventComments(idEvento, database.getConnection());
    }
    
    public float getEventRate(int idEvento) throws Exception{
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
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public Response login (String username, String password){
        Message m = new Message();
        try {
            Helper helper = new Helper();
            return Response.ok(helper.logUser(username, password)).build();
        } catch (SQLException ex) {
            m.setSuccess(false);
            if(ex.getErrorCode()==1){
                m.setMensaje(IConstantes.USUARIO_INCORRECTO);
                return Response.ok().entity(m).build();
            }
            else{
                m.setMensaje(IConstantes.ERROR);
                return Response.ok().entity(m).build();
            }
        } catch (Exception ex) {
            m.setSuccess(false);
            m.setMensaje(IConstantes.ERROR);
            return Response.ok().entity(m).build();
        }
    }
    
    public ArrayList<Genero> verGeneros() throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        return genreInfo.verGeneros(database.getConnection());
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
    
    public Discografia getDiscInfo(int idDisco)throws SQLException, Exception{
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
    
    public void createSong(Cancion cancion) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            DiscInfo discInfo = new DiscInfo();
            discInfo.createSong(cancion,database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
//    public void rateDisc(Resena resenaDisco)throws SQLException, Exception{
//        DataBaseConnect database = new DataBaseConnect();
//        DiscInfo discInfo = new DiscInfo();
//        discInfo.rateDisc(resenaDisco,database.getConnection());
//    }
    
    public ArrayList<Resena> getDiscComments(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscComments(idDisco,database.getConnection());
    }
    
    public float getDiscRate(int  idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscRate(idDisco,database.getConnection());
    }
    
    public float getAllDiscRate(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getAllDiscRate(idBanda, database.getConnection());
    }
    
    public float getConcertRate(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getConcertRate(idBanda, database.getConnection());
    }
    
    
    
    public Cancion getSong(int idCancion){
        DiscInfo di = new DiscInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        try {
            return di.getSong(idCancion, baseConnect.getConnection());
        } catch (Exception ex) {
            return null;
        }
    }
    
    public boolean esSeguidor(int idFan, int idBanda) throws SQLException{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        boolean bandera = false;
        try {
            bandera = fanInfo.esSeguidor(idFan, idBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(bandera)
            return true;
        else
            return false;
    }
    
    public Fan getFanInfo(int idFan)throws SQLException{
        try {
            FanInfo fanInfo = new FanInfo();
            DataBaseConnect baseConnect = new DataBaseConnect();
            return fanInfo.getFanInfo(idFan, baseConnect.getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Banda getBandInfo(int idBanda) throws Exception{
        BandInfo  bandInfo = new BandInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        return bandInfo.getBandInfo(idBanda, baseConnect.getConnection());
    }
    
    public Evento getEventInfo(int idEvento) throws SQLException, Exception{
        EventInfo ei = new EventInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        
        return ei.getEventInfo(idEvento, baseConnect.getConnection());
    }
    
    public ArrayList<Genero> getGenerosBanda(int idBanda) throws SQLException, Exception{
        DataBaseConnect baseConnect = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        
        return bandInfo.getBandGeneros(idBanda, baseConnect.getConnection());
        
    }
    
    public ArrayList<Pais> getPaises() throws Exception{
        PaisesInfo info = new PaisesInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        return info.getPaises(baseConnect.getConnection());
    }
}
