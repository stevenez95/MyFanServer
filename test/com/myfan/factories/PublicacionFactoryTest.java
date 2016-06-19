/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.factories;

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
public class PublicacionFactoryTest {
    
    public PublicacionFactoryTest() {
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
     * Test of getPublicacion method, of class PublicacionFactory.
     */
    @Test
    public void testGetPublicacion() {
        System.out.println("getPublicacion");
        String tipo = "calificacion";
        PublicacionFactory instance = new PublicacionFactory();
        IPublicacion result = instance.getPublicacion(tipo);
        assertTrue(result instanceof Calificacion);
    }
    
    @Test
    public void testGetPublicacion2() {
        System.out.println("getPublicacion");
        String tipo = "seguimiento";
        PublicacionFactory instanceF = new PublicacionFactory();
        IPublicacion result = instanceF.getPublicacion(tipo);
        assertTrue(result instanceof Seguimiento);
    }
    
}
