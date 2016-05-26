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
public class ResenaDisco {
    
    private int idDisco, idFan, calificacion;
    private String comentario;

    /**
     * @return the idDisco
     */
    public int getIdDisco() {
        return idDisco;
    }

    /**
     * @param idDisco the idDisco to set
     */
    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
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
