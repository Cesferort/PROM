package com.example.seleccioneintentsej3;

public class WebFavorita
{
    private int idWeb;
    private String nomWeb;
    private String urlWeb;

    public WebFavorita(int idWeb,String nomWeb,String urlWeb)
    {
        this.idWeb = idWeb;
        this.nomWeb = nomWeb;
        this.urlWeb = urlWeb;
    }

    public int getIdWeb()
    {
        return idWeb;
    }

    public String getNomWeb()
    {
        return nomWeb;
    }

    public String getUrlWeb()
    {
        return urlWeb;
    }

    public String toString()
    {
        return nomWeb;
    }
}