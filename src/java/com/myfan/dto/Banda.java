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
public class Banda {
    
    private int idBanda,seguidores,anioCreacion;
    private double fechaCreacion;
    private String nombreBanda,username,password,hashtag,biografia,integrantes, pais,fotoPerfil;
    private boolean activo;

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
     * @return the fechaCreacion
     */
    public double getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(double fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the nombreBanda
     */
    public String getNombreBanda() {
        return nombreBanda;
    }

    /**
     * @param nombreBanda the nombreBanda to set
     */
    public void setNombreBanda(String nombreBanda) {
        this.nombreBanda = nombreBanda;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the anioCreacion
     */
    public int getAnioCreacion() {
        return anioCreacion;
    }

    /**
     * @param anioCreacion the anioCreacion to set
     */
    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    /**
     * @return the hashtag
     */
    public String getHashtag() {
        return hashtag;
    }

    /**
     * @param hashtag the hashtag to set
     */
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * @return the biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * @param biografia the biografia to set
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * @return the integrantes
     */
    public String getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(String integrantes) {
        this.integrantes = integrantes;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the fotoPerfil
     */
    public String getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * @param fotoPerfil the fotoPerfil to set
     */
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    /**
     * @return the seguidores
     */
    public int getSeguidores() {
        return seguidores;
    }

    /**
     * @param seguidores the seguidores to set
     */
    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }
    
    
}
