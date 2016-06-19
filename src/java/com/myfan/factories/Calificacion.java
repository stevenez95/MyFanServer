/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.factories;

import com.myfan.dto.Banda;
import com.myfan.dto.Fan;
import com.myfan.model.Facade;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Steven
 */
public class Calificacion implements IPublicacion{

    @Override
    public void publicar(ConfigurationBuilder cb, int idFan, int idBanda) {
        Facade facade = new Facade();
        try {
            Fan fan = facade.getFanInfo(idFan);
            Banda banda = facade.getBandInfo(idBanda);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            Status s = twitter.updateStatus(fan.getUsername() +" comenta a "+banda.getNombreBanda() +" " + banda.getHashtag());
        } catch (Exception ex) {
        }
    }
    
}
