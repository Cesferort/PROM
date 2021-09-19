package com.example.tratamientoxml_ejercicio;
import java.util.ArrayList;

public class Tiempo
{
    private String fecha;
    private ArrayList<String> precipitacion;
    private ArrayList<String> cotaNieve ;
    private ArrayList<String> estadosCielo;

    public Tiempo(String fecha,ArrayList<String> precipitacion,ArrayList<String> cotaNieve,ArrayList<String> estadosCielo)
    {
        this.fecha = fecha;
        this.precipitacion = precipitacion;
        this.cotaNieve = cotaNieve;
        this.estadosCielo = estadosCielo;
    }

    //Definimos los getter
    public String getFecha()
    {
        return fecha;
    }

    public ArrayList<String> getPrecipitacion()
    {
        return precipitacion;
    }

    public ArrayList<String> getCotaNieve()
    {
        return cotaNieve;
    }

    public ArrayList<String> getEstadosCielo()
    {
        return estadosCielo;
    }
}