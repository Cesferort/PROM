package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityBDPrincipal extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdprincipal);
    }

    public void listener_butNuevoLibro(View view)
    {
        Intent intent = new Intent(ActivityBDPrincipal.this, ActivityBDNuevoLibro.class);
        startActivity(intent);
    }

    public void listener_butListarLibros(View view)
    {
        Intent intent = new Intent(ActivityBDPrincipal.this, ActivityBDListarLibros.class);
        startActivity(intent);
    }

    public void listener_butBuscarLibros(View view)
    {
        Intent intent = new Intent(ActivityBDPrincipal.this, ActivityBDBuscarLibros.class);
        startActivity(intent);
    }

    public void listener_butSalir(View view)
    {
        finish();
    }
}