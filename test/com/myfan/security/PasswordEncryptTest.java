/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.security;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Steven
 */
public class PasswordEncryptTest {
    
    public PasswordEncryptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validatePassword method, of class PasswordEncrypt.
     */
    @Test
    public void testValidatePassword() {
        System.out.println("validatePassword");
        String password = "password";
        String hashed = "$2a$10$iaLdPCO8IueeVzrv/grhv.ojAyZeILckhfCPoMxecKQ1jLjYMjvcC";
        boolean expResult = true;
        boolean result = PasswordEncrypt.validatePassword(password, hashed);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashPassword method, of class PasswordEncrypt.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String password = "password";
        boolean expResult = true;
        String hash = PasswordEncrypt.hashPassword(password);
        boolean result = PasswordEncrypt.validatePassword(password, hash);
        assertEquals(expResult, result);
    }
    
}
