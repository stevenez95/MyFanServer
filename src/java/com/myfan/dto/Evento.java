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
public class Evento {
    
    private int idEvento, concierto, idBanda;
    private String titulo, contenido, fechaCreacion, fechaEvento, ubicacion;

    /**
     * @return the idEvento
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the concierto
     */
    public int getConcierto() {
        return concierto;
    }

    /**
     * @param concierto the concierto to set
     */
    public void setConcierto(int concierto) {
        this.concierto = concierto;
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
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaEvento
     */
    public String getFechaEvento() {
        return fechaEvento;
    }

    /**
     * @param fechaEvento the fechaEvento to set
     */
    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
