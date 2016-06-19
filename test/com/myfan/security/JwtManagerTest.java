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
public class JwtManagerTest {
    
    public JwtManagerTest() {
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
     * Test of jwtGenerate method, of class JwtManager.
     */
    @Test
    public void testJwtGenerate() {
        System.out.println("jwtGenerate");
        JwtManager instance = new JwtManager();
        boolean expResult = true;
        String jwt = instance.jwtGenerate();
        boolean result = instance.jwtValidate(jwt);
        assertEquals(expResult, result);

    }

    /**
     * Test of jwtValidate method, of class JwtManager.
     */
    @Test
    public void testJwtValidate() {
        System.out.println("jwtValidate");
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNeUZhbiIsImp0aSI6IjhDSW9FRENxd1dHVnFfbFBoaURRc3ciLCJpYXQiOjE0NjU5NDg3NTksInN1YiI6InN1YmplY3QifQ.UyWGtFXBgguGNeyZCd0cf4I0PpqsWm1zKrQLo_mMTz4";
        JwtManager instance = new JwtManager();
        boolean expResult = true;
        boolean result = instance.jwtValidate(jwt);
        assertEquals(expResult, result);
    }
    
}
