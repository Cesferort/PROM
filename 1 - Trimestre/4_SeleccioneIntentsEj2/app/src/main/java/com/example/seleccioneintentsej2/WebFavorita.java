package com.example.seleccioneintentsej2;

public class WebFavorita
{
    private int idWeb;
    private String nomWeb;
    private String urlWeb;
    private int imgWeb;

    public WebFavorita(int idWeb,String nomWeb,String urlWeb,int imgWeb)
    {
        this.idWeb = idWeb;
        this.nomWeb = nomWeb;
        this.urlWeb = urlWeb;
        this.imgWeb = imgWeb;
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

    public int getImgWeb()
    {
        return imgWeb;
    }

    public String toString()
    {
        return nomWeb;
    }
}