/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Steven
 */
public class PasswordEncrypt {

    public PasswordEncrypt() {
    }
    
    public boolean validatePassword(String password,String hashed){
        
        if (BCrypt.checkpw(password, hashed))
            return true;
        else
            return false;
        
    }
    
    public String hashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }
    
}
