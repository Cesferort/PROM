package com.example.sqlite;

public class Libro
{
    private int idLibro;
    private String nomLibro;
    private String nomEditorial;
    private Propietario propietario;

    public Libro(int idLibro, String nomLibro, String nomEditorial, Propietario propietario)
    {
        this.idLibro = idLibro;
        this.nomLibro = nomLibro;
        this.nomEditorial = nomEditorial;
        this.propietario = propietario;
    }

    public int getIdLibro()
    {
        return idLibro;
    }

    public String getNomEditorial()
    {
        return nomEditorial;
    }

    public Propietario getPropietario()
    {
        return propietario;
    }

    public String toString()
    {
        return nomLibro;
    }
}