package com.example.fragments;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragListadoAlbum fragmentListado =(FragListadoAlbum)getSupportFragmentManager().findFragmentById(R.id.frgListado);
        fragmentListado.setCorreoListener(this);
    }

    public void onCorreoSeleccionado(Album album)
    {
        if (getSupportFragmentManager().findFragmentById(R.id.frgInfo) != null)
        {
            ((FragInfoAlbum)getSupportFragmentManager().findFragmentById(R.id.frgInfo)).mostrarDetalle(album.getListaCanciones());
        }
        else
        {
            Intent i = new Intent(this, InfoActivity.class);
            i.putExtra("listaCanciones", album.getListaCanciones());
            startActivity(i);
        }
    }
}