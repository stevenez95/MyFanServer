/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.model;

import com.myfan.connection.DataBaseConnect;
import com.myfan.connection.ImageSaver;
import com.myfan.connection.TwitterConnect;
import com.myfan.dao.BandInfo;
import com.myfan.dao.FanInfo;
import com.myfan.dao.UserInfo;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Message;
import com.myfan.dto.Resena;
import java.sql.SQLException;

/**
 *
 * @author Steven
 */
public class Helper {

    public Helper() {
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase TwitterConnect
     * @param idBanda  sobre la que se siguió
     * @param idFan que seguió a la banda
     */
    public void publicarSeguimiento(int idFan, int idBanda){
        TwitterConnect twitterConnect = new TwitterConnect();
        twitterConnect.publicar(idFan, idBanda, "seguimiento");
    }
    
      /**
     * Se encarga de redigirir la solicitud del cliente a la clase TwitterConnect
     * @param resena que sera publicada
     */
    public void publicarCalificacion(Resena resena){
        TwitterConnect twitterConnect = new TwitterConnect();
        twitterConnect.publicar(resena.getIdFan(), resena.getId(), "calificacion");
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase BandInfo, ImageSaver
     * @param banda  que se desea registrar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void registarBanda(Banda banda) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(banda.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        banda.setFotoPerfil(foto);
        bandInfo.registrarBanda(banda, database.getConnection());
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase ImageSaver, BandInfo
     * @param banda que se quiere actualizar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void actualizarBanda(Banda banda,int idBanda) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(banda.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        banda.setFotoPerfil(foto);
        bandInfo.actualizarBanda(banda,idBanda, database.getConnection());
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase FanInfo, ImageSaver
     * @param fan  que se quiere registrar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void registarFan(Fan fan) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(fan.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fan.setFotoPerfil(foto);
        fanInfo.registrarFan(fan, database.getConnection());
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase ImageSaver,FanInfo 
     * @param fan  que se quiere actualizar la informacion
     * @param idFan del fan a modificar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void actualizarFan(Fan fan, int idFan) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(fan.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fan.setFotoPerfil(foto);
        fanInfo.actualizarFan(fan,idFan, database.getConnection());
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase UserInfo
     * @param username que desea ingresar al sistema
     *  @param password del usuario que desea ingresar al sistema
     * @return mensaje de confirmacion
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public Message logUser(String username, String password) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        UserInfo project = new UserInfo();
        return project.login(username,password, database.getConnection());
    }
    
     /**
     * Se encarga de redigirir la solicitud del cliente a la clase FanInfo
     * @param id del objeto que se desea eliminar
     * @param idFan  que elimino la calificacion
     * @param tipo  de calificacion a eliminar
     * @throws Exception En caso de haber un error
     * @throws SQLException En caso de haber un error en la base de datos
     */
    public void deleteRate(int id, int idFan, String tipo) throws SQLException, Exception{
        FanInfo fanInfo = new FanInfo();
        DataBaseConnect baseConnect = new DataBaseConnect();
        switch(tipo){
            case "banda":
                fanInfo.deleteRateBand(idFan, id, baseConnect.getConnection());
                break;
            case "disco":
                fanInfo.deleteRateDisc(idFan, id, baseConnect.getConnection());
                break;
            case "concierto":
                fanInfo.deleteRateConcert(idFan, id, baseConnect.getConnection());
                break;
            default:
                break;
        }
    }
    
}
