/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.connection;

import com.myfan.dto.Message;


/**
 *
 * @author Steven
 */
public interface IConstantes {
    
    /************   PASSWORD HASH  ***************/
    public static final String KEY="asdfghjjsdfsdfsdfsdfsdfsdfqwerty";
    
    
    /************* ERRORES *****************/
    
    public static final String USUARIO_EXISTE="Usuario ya existe}";
    public static final String USUARIO_INCORRECTO="Usuario o contrasena incorrecto";
    public static final String MYFEST_URL="http://192.168.0.16:8080/MyFestServer/api/v1/";
    public static final String ERROR = "Ocurrio un errror en el servidor";    
    public static final Message m = new Message(false,ERROR,"",0);
    

    /****** TWITTER *********************/
    public static final String ACCESS_TOKEN = "723371134947749889-A1iGcS04t6Pi8jgiRdmV4FBY6U96zbH";
    public static final String ACCESS_TOKEN_SECRET = "xWHBeSaJyW1pp8rHMY6avgfaIk0OZg01TfJEKKr3gOtVp";
    public static final String CONSUMER_KEY = "GEYIbxf6f4rncxcRGPE60qS4K";
    public static final String CONSUMER_SECRET = "Vr0OJgA9CVA7qk1xokIDLZEMDgiXUodMkiv55bRF4wyHkY2RCA";
    
}
