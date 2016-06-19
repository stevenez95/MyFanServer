/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.factories;

import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Steven
 */
public interface IPublicacion {
    
    public void publicar(ConfigurationBuilder cb, int idFan, int idBanda);
    
}
