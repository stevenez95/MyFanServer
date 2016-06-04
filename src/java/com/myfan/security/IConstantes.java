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
    
    /************   PASSWORD HASH  ***************/
    public static final String KEY="asdfghjjsdfsdfsdfsdfsdfsdfqwerty";
    
    
    /************* ERRORES *****************/
    JsonParser jsonParser = new JsonParser();
    public static final String NOT_FOUND = jsonParser.parse("{success:false, error:notFound}").toString();
    
    public static final String USUARIO_EXISTE="{success:false, error:Usuario ya existe}";
    public static final String USUARIO_INCORRECTO=jsonParser.parse("{success:false, error:usuario_o_contrasena_incorrecto}").toString();
    public static final String MYFEST_URL="";
    public static final String SUCCESS= jsonParser.parse("{success:true}").toString();
    
    /****** TWITTER *********************/
    public static final String ACCESS_TOKEN = "723371134947749889-A1iGcS04t6Pi8jgiRdmV4FBY6U96zbH";
    public static final String ACCESS_TOKEN_SECRET = "xWHBeSaJyW1pp8rHMY6avgfaIk0OZg01TfJEKKr3gOtVp";
    public static final String CONSUMER_KEY = "GEYIbxf6f4rncxcRGPE60qS4K";
    public static final String CONSUMER_SECRET = "Vr0OJgA9CVA7qk1xokIDLZEMDgiXUodMkiv55bRF4wyHkY2RCA";
    
}
