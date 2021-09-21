package com.example.fragments;
import java.util.ArrayList;

public class Album
{
    private String compositor;
    private String nombreAlb;
    private int duracion;
    private int anioAlb;
    private ArrayList<Cancion> listaCanciones;

    public Album(String compositor, String nombreAlb, int duracion, int anioAlb, ArrayList<Cancion> listaCanciones)
    {
        this.compositor = compositor;
        this.nombreAlb = nombreAlb;
        this.duracion = duracion;
        this.anioAlb = anioAlb;
        this.listaCanciones = listaCanciones;
    }

    public String getCompositor()
    {
        return compositor;
    }

    public String getNombreAlb()
    {
        return nombreAlb;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public int getAnioAlb()
    {
        return anioAlb;
    }

    public ArrayList<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public String toString()
    {
        return nombreAlb;
    }
}