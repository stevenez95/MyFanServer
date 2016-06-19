/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.connection;

import static com.myfan.connection.IConstantes.ACCESS_TOKEN;
import static com.myfan.connection.IConstantes.ACCESS_TOKEN_SECRET;
import static com.myfan.connection.IConstantes.CONSUMER_KEY;
import static com.myfan.connection.IConstantes.CONSUMER_SECRET;
import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.dto.Resena;
import com.myfan.factories.IPublicacion;
import com.myfan.factories.PublicacionFactory;
import com.myfan.model.Facade;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Steven
 */
public class TwitterConnect {

    public TwitterConnect() {
    }
    
    /**
     * Se encarga de crear la conexion con el sistema externo Twitter
     * @return La configuracion de la conexion
     */
    private ConfigurationBuilder getConfig(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(CONSUMER_KEY)
                    .setOAuthConsumerSecret(CONSUMER_SECRET)
                    .setOAuthAccessToken(ACCESS_TOKEN)
                    .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
            return cb;
    }
    
    public void publicar(int idFan, int idBanda, String tipo){
        PublicacionFactory factory = new PublicacionFactory();
        IPublicacion publicacion = factory.getPublicacion(tipo);
        publicacion.publicar(getConfig(), idFan, idBanda);
    }
    
}
