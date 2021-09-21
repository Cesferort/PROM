package com.example.fragments;

import java.io.Serializable;

public class Cancion implements Serializable
{
    private String nomCan;
    private int durCan;

    public Cancion(String nomCan, int durCan)
    {
        this.nomCan = nomCan;
        this.durCan = durCan;
    }

    public String toString()
    {
        int durMin = 0;
        int durCanAux = durCan;
        while(durCanAux >= 60)
        {
            durCanAux -= 60;
            durMin++;
        }

        return nomCan + " - " + durMin + ":" + durCanAux;
    }
}