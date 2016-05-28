/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.connection;

import static com.myfan.security.IConstantes.ACCESS_TOKEN;
import static com.myfan.security.IConstantes.ACCESS_TOKEN_SECRET;
import static com.myfan.security.IConstantes.CONSUMER_KEY;
import static com.myfan.security.IConstantes.CONSUMER_SECRET;
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
    
    public void publicarCalificacion(String fan, String banda,String hashtag){
        try {
            TwitterFactory tf = new TwitterFactory(getConfig().build());
            Twitter twitter = tf.getInstance();
            Status s = twitter.updateStatus(fan +" comenta a "+banda +" " + hashtag);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void publicarSeguimiento(String fan, String banda,String hashtag){
        try {
            TwitterFactory tf = new TwitterFactory(getConfig().build());
            Twitter twitter = tf.getInstance();
            Status s = twitter.updateStatus(fan +" sigue a "+banda +" " + hashtag);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
