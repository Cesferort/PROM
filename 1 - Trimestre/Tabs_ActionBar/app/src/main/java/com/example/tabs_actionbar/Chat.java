package com.example.tabs_actionbar;

public class Chat {
    private String nomContacto;
    private String txtAbreviado;

    public Chat(String nomContacto, String txtAbreviado) {
        this.nomContacto = nomContacto;
        this.txtAbreviado = txtAbreviado;
    }

    public String getTxtAbreviado()
    {
        return txtAbreviado;
    }

    public String toString()
    {
        return nomContacto;
    }
}