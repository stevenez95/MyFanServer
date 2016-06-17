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
    
    public void publicarSeguimiento(int idFan, int idBanda){
        TwitterConnect twitterConnect = new TwitterConnect();
        twitterConnect.publicarSeguimiento(idFan, idBanda);
    }
    
    public void publicarCalifcacion(Resena resena){
        TwitterConnect twitterConnect = new TwitterConnect();
        twitterConnect.publicarCalificacion(resena);
    }
    
    public void registarBanda(Banda banda) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(banda.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        BandInfo bandInfo = new BandInfo();
        banda.setFotoPerfil(foto);
        bandInfo.registrarBanda(banda, database.getConnection());
    }
    
    public void registarFan(Fan fan) throws SQLException, Exception{
        ImageSaver imageSaver = new ImageSaver();
        String foto = imageSaver.saveImage(fan.getFotoPerfil());
        if(foto==null) throw new Exception();
        DataBaseConnect database = new DataBaseConnect();
        FanInfo fanInfo = new FanInfo();
        fan.setFotoPerfil(foto);
        fanInfo.registrarFan(fan, database.getConnection());
    }
    
    public Message logUser(String username, String password) throws SQLException, Exception{
        DataBaseConnect database = new DataBaseConnect();
        UserInfo project = new UserInfo();
        return project.login(username,password, database.getConnection());
    }
    
}
