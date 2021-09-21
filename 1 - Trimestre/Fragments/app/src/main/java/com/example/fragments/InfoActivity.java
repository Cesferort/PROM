package com.example.fragments;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity
{
    public void onCreate(Bundle savedInstanceState)
    {

        System.out.println("MAL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        FragInfoAlbum detalle = (FragInfoAlbum)getSupportFragmentManager().findFragmentById(R.id.frgInfo);

        Bundle extras = getIntent().getExtras();
        ArrayList<Cancion> listaCanciones = (ArrayList<Cancion>)extras.get("listaCanciones");
        detalle.mostrarDetalle(listaCanciones);
    }
}