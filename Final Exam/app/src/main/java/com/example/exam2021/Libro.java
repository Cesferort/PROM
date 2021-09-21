package com.example.exam2021;

import java.io.Serializable;

public class Libro implements Serializable
{
    private String titulo;
    private String autor;
    private String editorial;
    private long isbn;
    private int numPag;
    private boolean leido;

    public Libro(String titulo, String autor, String editorial, long isbn, int numPag, boolean leido)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.numPag = numPag;
        this.leido = leido;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public String getEditorial()
    {
        return editorial;
    }

    public long getIsbn()
    {
        return isbn;
    }

    public int getNumPag()
    {
        return numPag;
    }

    public boolean getLeido()
    {
        return leido;
    }

    public String toString()
    {
        return titulo;
    }
}