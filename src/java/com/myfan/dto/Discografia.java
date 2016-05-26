/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.dto;

/**
 *
 * @author Steven
 */
public class Discografia {
    private int idDiscografia,anioPublicacion,idBanda;
    private String nombre,descripcion,generoMusical,selloDiscografico;

    /**
     * @return the idDiscografia
     */
    public int getIdDiscografia() {
        return idDiscografia;
    }

    /**
     * @param idDiscografia the idDiscografia to set
     */
    public void setIdDiscografia(int idDiscografia) {
        this.idDiscografia = idDiscografia;
    }

    /**
     * @return the anioPublicacion
     */
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * @param anioPublicacion the anioPublicacion to set
     */
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * @return the idBanda
     */
    public int getIdBanda() {
        return idBanda;
    }

    /**
     * @param idBanda the idBanda to set
     */
    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the generoMusical
     */
    public String getGeneroMusical() {
        return generoMusical;
    }

    /**
     * @param generoMusical the generoMusical to set
     */
    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    /**
     * @return the selloDiscografico
     */
    public String getSelloDiscografico() {
        return selloDiscografico;
    }

    /**
     * @param selloDiscografico the selloDiscografico to set
     */
    public void setSelloDiscografico(String selloDiscografico) {
        this.selloDiscografico = selloDiscografico;
    }
    
    
    
    
}
