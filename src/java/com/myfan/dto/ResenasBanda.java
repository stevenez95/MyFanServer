/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfan.dto;

/**
 *
 * @author Daniel
 */
public class ResenasBanda {
    private int idBanda,idFan,calificacion;
    private String comentario;

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
     * @return the idFan
     */
    public int getIdFan() {
        return idFan;
    }

    /**
     * @param idFan the idFan to set
     */
    public void setIdFan(int idFan) {
        this.idFan = idFan;
    }

    /**
     * @return the calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
