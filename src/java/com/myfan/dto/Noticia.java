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
public class Noticia {
    private int idNoticia, idBanda;
    private double fechaCreacion;
    private String contenido, titulo, creadoPor;

    /**
     * @return the idNoticia
     */
    public int getIdNoticia() {
        return idNoticia;
    }

    /**
     * @param idNoticia the idNoticia to set
     */
    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
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
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the creadoPor
     */
    public String getCreadoPor() {
        return creadoPor;
    }

    /**
     * @param creadoPor the creadoPor to set
     */
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
