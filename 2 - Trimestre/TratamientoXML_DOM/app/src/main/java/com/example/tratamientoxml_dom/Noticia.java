package com.example.tratamientoxml_dom;

public class Noticia
{
    private String titulo;
    private String link;
    private String descripcion;
    private String guid;
    private String fecha;

    //Definimos los getter y los setter
    public String getTitulo()
    {
        return titulo;
    }
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getLink()
    {
        return link;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getGuid()
    {
        return guid;
    }
    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    public String getFecha()
    {
        return fecha;
    }
    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
}