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
public class Cancion {
    private int idCancion, idDisco;
    private String nombre, linkVid,duracion;
    private boolean enVivo, bonus, limitada;

    /**
     * @return the idCancion
     */
    public int getIdCancion() {
        return idCancion;
    }

    /**
     * @param idCancion the idCancion to set
     */
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

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
     * @return the enVivo
     */
    public boolean isEnVivo() {
        return enVivo;
    }

    /**
     * @param enVivo the enVivo to set
     */
    public void setEnVivo(boolean enVivo) {
        this.enVivo = enVivo;
    }

    /**
     * @return the bonus
     */
    public boolean isBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    /**
     * @return the limitada
     */
    public boolean isLimitada() {
        return limitada;
    }

    /**
     * @param limitada the limitada to set
     */
    public void setLimitada(boolean limitada) {
        this.limitada = limitada;
    }

    /**
     * @return the duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the linkVid
     */
    public String getLinkVid() {
        return linkVid;
    }

    /**
     * @param linkVid the linkVid to set
     */
    public void setLinkVid(String linkVid) {
        this.linkVid = linkVid;
    }
    
    
}
