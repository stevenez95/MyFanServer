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
import com.myfan.model.ProjectManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Steven
 */
public class TwitterConnect {

    public TwitterConnect() {
    }
    
    private ConfigurationBuilder getConfig(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(CONSUMER_KEY)
                    .setOAuthConsumerSecret(CONSUMER_SECRET)
                    .setOAuthAccessToken(ACCESS_TOKEN)
                    .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
            return cb;
    }
    
    public void publicarCalificacion(Resena resenaBanda){
        
        try {
            ProjectManager manager = new ProjectManager();
            Banda banda = manager.getBandInfo(resenaBanda.getId());
            Fan fan = manager.getFanInfo(resenaBanda.getIdFan());
            TwitterFactory tf = new TwitterFactory(getConfig().build());
            Twitter twitter = tf.getInstance();
            Status s = twitter.updateStatus(fan.getUsername() +" comenta a "+banda.getNombreBanda() +" " + banda.getHashtag());
        } catch (Exception ex) {
            Logger.getLogger(TwitterConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void publicarSeguimiento(int idFan, int idBanda){
        try {
            ProjectManager manager = new ProjectManager();
            Banda banda = manager.getBandInfo(idBanda);
            Fan fan = manager.getFanInfo(idFan);
            TwitterFactory tf = new TwitterFactory(getConfig().build());
            Twitter twitter = tf.getInstance();
            Status s = twitter.updateStatus(fan.getUsername() +" sigue a "+banda.getNombreBanda() +" " + banda.getHashtag());
        } catch (Exception ex) {
            Logger.getLogger(TwitterConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
