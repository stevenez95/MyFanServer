/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.security;

import com.google.gson.JsonParser;

/**
 *
 * @author Steven
 */
public interface IConstantes {
    
    JsonParser jsonParser = new JsonParser();
    public static final String NOT_FOUND = jsonParser.parse("{success:false, error:notFound}").toString();
    public static final String KEY="asdfghjjsdfsdfsdfsdfsdfsdfqwerty";
    public static final String USUARIO_EXISTE="{success:false, error:Usuario ya existe}";
    public static final String USUARIO_INCORRECTO="{success:false, error:usuario o contrasena incorrecto}";
    public static final String MYFEST_URL="";
    public static final String SUCCESS= jsonParser.parse("{success:true}").toString();
    
}
