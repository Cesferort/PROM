package com.example.sqlite;

public class Propietario
{
    private int idPropietario;
    private String nomPropietario;

    public Propietario(int idPropietario, String nomPropietario)
    {
        this.idPropietario = idPropietario;
        this.nomPropietario = nomPropietario;
    }

    public int getIdPropietario()
    {
        return idPropietario;
    }

    public String toString()
    {
        return nomPropietario;
    }
}