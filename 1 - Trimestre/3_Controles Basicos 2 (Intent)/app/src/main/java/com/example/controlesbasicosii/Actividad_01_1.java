package com.example.controlesbasicosii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Actividad_01_1 extends AppCompatActivity
{
    private TextView txtHolaNombre;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_01_1);

        txtHolaNombre = (TextView) findViewById(R.id.txtHolaNombre);
        escribirHolaNombre();
    }

    private void escribirHolaNombre()
    {
        Bundle extras = getIntent().getExtras();
        String nom = extras.getString("nombre");
        String ape = extras.getString("apellidos");

        txtHolaNombre.setText("Hola " + nom + " " + ape + " ¿Aceptas las condiciones?");
    }

    public void listenerRespuesta(View view)
    {
        Intent intent;
        switch(view.getId())
        {
            case (R.id.butAceptar):
                intent = new Intent();
                intent.putExtra("respuesta", true);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case(R.id.butRechazar):
                intent = new Intent();
                intent.putExtra("respuesta", false);
                setResult(RESULT_OK, intent);
                finish();
        }
    }
}