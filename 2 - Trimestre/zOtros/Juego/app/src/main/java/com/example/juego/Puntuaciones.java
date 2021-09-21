package com.example.juego;

import java.io.Serializable;

public class Puntuaciones implements Serializable {
    private static final long serialVersionUID = 345L;
    private String nombre="";
    private int puntuacion;

    public Puntuaciones(){}
    public Puntuaciones(String n,int p){
        this.nombre = n;
        this.puntuacion = p;
    }

    public String getNombre(){return nombre;}
    public int getPuntuacion(){return puntuacion;}
}
