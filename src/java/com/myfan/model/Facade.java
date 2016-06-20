/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.myfan.model;

import com.google.gson.Gson;
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper encargada
     * de procesar registrar fan
     * @param fan que se va a crear
     * @return Respuesta si se creo el fan correctamente
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase fanInfo y
     * realizar la solicitud a la clase Helper de procesar la publicacion en Twitter
     * @param idFan que va a seguir
     * @param idBanda  a la que va seguir
     * @throws SQLException En caso de haber un error en la BD
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase fanInfo
     * @param idFan  fan que quiere dejar de seguir
     * @param idBanda  Banda que se va a dejar de seguir
     * @throws SQLException En caso de haber un error en la BD
     */
    public void dejarSeguirBanda(int idFan,  int idBanda) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.dejarSeguirBanda(idFan, idBanda, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase fanInfo
     * @param idFan  fan que quiere desactivar la cuenta
     * @throws SQLException En caso de haber un error en la BD
     */
    public void desactivarFan(int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            fanInfo.desactivarFan(idFan, database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper
     * @param fan  que desea actualizar sus datos
     * @param idFan  fan que quiere actualizar sus datos
     * @return mensaje si se logro correctamente
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase fanInfo
     * @param idFan  fan que desea observar sus artistas
     * @throws SQLException En caso de haber un error en la BD
     * @return lista de artistas
     */
    public ArrayList<Banda> verMisArtistas(int idFan) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            FanInfo fanInfo = new FanInfo();
            return  fanInfo.verMisArtistas(idFan, database.getConnection());
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase fanInfo
     * @param nombre  de la banda que se desea buscar
     * @param pais de la banda que se desea buscar
     * @param genero de la banda que se desea buscar
     * @throws SQLException En caso de haber un error en la BD
     * @return lista de artistas
     */
    public ArrayList<Banda> buscarArtistas(String nombre, String pais, String genero) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        return fanInfo.buscarArtistas(nombre, pais,genero, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper para
     * realizar la publicacion en Twitter
     * @param resenaBanda de la que se realizar y publicar
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateBand(Resena resenaBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        Helper helper = new Helper();
        FanInfo fanInfo = new FanInfo();
        fanInfo.rateBand(resenaBanda, database.getConnection());
        helper.publicarCalificacion(resenaBanda);
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper para
     * realizar la publicacion en Twitter
     * @param resenaDisco  de la que se realizar y publicar
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateDisc(Resena resenaDisco) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fanInfo.rateDisc(resenaDisco, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase eventInfo
     * @param idFan que se desea obtener los eventos
     * @throws SQLException En caso de haber un error en la BD
     * @return lista de los eventos del fan
     */
    public ArrayList<Evento> getEventosFan(int idFan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo  = new EventInfo();
        return eventInfo.getEventosFan(idFan, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper para
     * realizar la publicacion en Twitter
     * @param resenaEvento  de la que se realizar y publicar
     * @throws SQLException En caso de haber un error en la BD
     */
    public void rateEvent(Resena resenaEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo   = new FanInfo();
        fanInfo.rateEvent(resenaEvento, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idFan que se desea obtener las noticias
     * @throws SQLException En caso de haber un error en la BD
     * @return lista de las noticias del fan
     */
    public ArrayList<Noticia> getNoticiasFan(int idFan) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo  newsInfo  = new NewsInfo();
        return newsInfo.getNoticiasFan(idFan, database.getConnection());
    }
    
    /*banda*/
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper encargada
     * de procesar registrar fan
     * @param banda  que se va a crear
     * @return Respuesta si se creo la banda correctamente
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper
     * @param banda que desea actualizar sus datos
     * @param idBanda  banda que quiere actualizar sus datos
     * @return mensaje si se logro correctamente
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere desactivar
     * @throws Exception En caso de haber un error
     */
    public void desactivarBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        bandInfo.desactivarBanda(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere obtener cantidad de seguidores
     * @throws Exception En caso de haber un error
     * @return cantidad de seguidores
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere obtener los comentarios
     * @return lista con los comentarios de la banda
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Resena> getBandComments(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getBandComments(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere obtener la cantidad de eventos
     * @return cantidad de eventos de la banda
     * @throws Exception En caso de haber un error
     */
    public int getCantidadEventos(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getCantidadEventos(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere obtener los ultimos eventos
     * @return lista con los ultimos eventos de la banda
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Evento> getUltimosEventos(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getUltimosEventos(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  banda que quiere obtener la calificacion
     * @return califiacion de la banda
     * @throws Exception En caso de haber un error
     */
    public float getBandRate(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo   = new BandInfo();
        return bandInfo.getBandRate(idBanda, database.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param evento que se desea crear
     * @throws Exception En caso de haber un error
     */
    public void crearEvento(Evento evento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        eventInfo.crearEvento(evento, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param idBanda  banda que quiere obtener los eventos
     * @return lista con los eventos de la banda
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Evento> getEventosBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventosBanda(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param idEvento  que se quiere cancelar
     * @throws Exception En caso de haber un error
     */
    public void cancelEvent(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        eventInfo.cancelEvent(idEvento, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param idEvento  que se quiere obtener los comentarios
     * @return lista con los comentarios de eventos de la banda
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Resena> getEventComments(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventComments(idEvento, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param idEvento  que se quiere obtener la calificacion
     * @return calificacion del evento de la banda
     * @throws Exception En caso de haber un error
     */
    public float getEventRate(int idEvento) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        EventInfo eventInfo = new EventInfo();
        return eventInfo.getEventRate(idEvento, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param noticia  que se quiere crear
     * @throws Exception En caso de haber un error
     */
    public void crearNoticia(Noticia noticia) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.crearNoticia(noticia, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idBanda  que se quiere obtener las noticias
     * @return lista con las noticias de la banda
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Noticia> getNoticiasBanda(int idBanda) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        return newsInfo.getNoticiasBanda(idBanda, database.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idNoticia   que se quiere borrar
     * @throws Exception En caso de haber un error
     */
    public void deleteNews(int idNoticia) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.deleteNews(idNoticia, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper
     * @param username que se quiere logger
     * @param password  que correspondiente al username
     * @return mensaje que se efectuo correctamente
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase GenreInfo
     * @return lista con los generos del sistema
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Genero> verGeneros() throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        return genreInfo.verGeneros(database.getConnection());
    }
    
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase GenreInfo
     * @param genero  que se quiere crear
     * @throws Exception En caso de haber un error
     */
    public void crearGenero(Genero genero) throws Exception{
        DataBaseConnect database = new DataBaseConnect();
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.crearGenero(genero,database.getConnection());
    }
    
    /*discos*/
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param discografia  que se quiere crear
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void createDisc(Discografia discografia) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.createDisc(discografia,database.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idBanda  que se quiere obtener los dicos
     * @return lista con los discos de una banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public ArrayList<Discografia> getDiscs(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscs(idBanda,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idDisco  que se quiere obtener la informacion
     * @return discografia de una banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public Discografia getDiscInfo(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscInfo(idDisco,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param discografia  que se quiere editar
     * @param idDisco  del que se desea modificar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void editDisc(Discografia discografia, int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.editDisc(discografia, idDisco,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idDisco  que se quiere eliminar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void deleteDisc(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.deleteDisc(idDisco,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param cancion  que se quiere crear
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void createSong(Cancion cancion) throws SQLException{
        try {
            DataBaseConnect database = new DataBaseConnect();
            DiscInfo discInfo = new DiscInfo();
            discInfo.createSong(cancion,database.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idDisco  que se quiere obtener las canciones
     * @return lista con las noticias de la banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public ArrayList<Cancion> getSongs(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getSongs(idDisco,database.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param cancion que se quiere editar
     * @param idCancion  de la cancion que se desea editar
     * @throws SQLException En caso de haber un error en la base de datos
     * @throws Exception En caso de haber un error
     */
    public void editSong(Cancion cancion,int idCancion)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        discInfo.editSong(cancion,idCancion,database.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idCancion  que se quiere eliminar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idDisco  que se quiere obtener los comentarios
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     * @return lista con los comentarios de un disco
     */
    public ArrayList<Resena> getDiscComments(int idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscComments(idDisco,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idDisco  que se quiere obtener las noticias
     * @return lista con las noticias de la banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public float getDiscRate(int  idDisco)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        DiscInfo discInfo = new DiscInfo();
        return discInfo.getDiscRate(idDisco,database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idBanda  que se quiere obtener las noticias
     * @return lista con las noticias de la banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public float getAllDiscRate(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getAllDiscRate(idBanda, database.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase NewsInfo
     * @param idBanda  que se quiere obtener las noticias
     * @return lista con las noticias de la banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public float getConcertRate(int idBanda)throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getConcertRate(idBanda, database.getConnection());
    }
    
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase DiscInfo
     * @param idCancion  que se quiere obtener
     * @return lista con las noticias de la banda
     */
    public Cancion getSong(int idCancion){
        DiscInfo di = new DiscInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        try {
            return di.getSong(idCancion, baseConnect.getConnection());
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase FanInfo
     *  @param idFan que desea es seguidor una banda
     * @param idBanda  al cual el fan sigue
     * @return boolean si es seguidor o no
     * @throws SQLException En caso de haber un error en la base de datos
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase FanInfo
     * @param idFan  que se quiere obtener la informacion
     * @return objeto fan
     * @throws SQLException En caso de haber un error en la base de datos
     */
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
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  que se quiere obtener la informacion
     * @return objeto banda con la informacion
     * @throws Exception En caso de haber un error
     */
    public Banda getBandInfo(int idBanda) throws Exception{
        BandInfo  bandInfo = new BandInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        return bandInfo.getBandInfo(idBanda, baseConnect.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase EventInfo
     * @param idEvento  que se quiere obtener la informacion
     * @return objeto evento con la informacion
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public Evento getEventInfo(int idEvento) throws SQLException, Exception{
        EventInfo ei = new EventInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        
        return ei.getEventInfo(idEvento, baseConnect.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @param idBanda  que se quiere obtener los generos musicales
     * @return lista con los generos de la banda
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public ArrayList<Genero> getGenerosBanda(int idBanda) throws SQLException, Exception{
        DataBaseConnect baseConnect = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        return bandInfo.getBandGeneros(idBanda, baseConnect.getConnection());
        
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase PaisesInfo
     * @return lista con los paises del sistema
     * @throws Exception En caso de haber un error
     */
    public ArrayList<Pais> getPaises() throws Exception{
        PaisesInfo info = new PaisesInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        return info.getPaises(baseConnect.getConnection());
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo
     * @return string con el top 10 de las bandas mejor calificadas
     * @throws Exception En caso de haber un error
     */
    public String getTopBands() throws Exception{
        Gson g = new Gson();
        BandInfo bandInfo = new BandInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        return g.toJson(bandInfo.getTopBandas(baseConnect.getConnection()));
    }
    
    /**
     * Se encarga de redigirir la solicitud del cliente a la clase Helper
     * @param id  del objeto eliminado
     * @param idFan que realizo la eliminacion
     * @param tipo de reseña
     * @throws Exception En caso de haber un error
     */
    public void deleteRate(int id, int  idFan, String tipo) throws Exception{
        Helper h = new Helper();
        h.deleteRate(id, idFan, tipo);
    }
}
