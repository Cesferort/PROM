package com.example.controlesbasicosii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Actividad_01 extends AppCompatActivity
{
    private EditText txtNombre;
    private EditText txtApellidos;
    private TextView txtCondiciones;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_01);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtCondiciones = (TextView) findViewById(R.id.txtCondiciones);
    }

    public void listenerButVerificar(View view)
    {
        String nom = txtNombre.getText().toString();
        String ape = txtApellidos.getText().toString();
        
        Intent intent = new Intent(Actividad_01.this, Actividad_01_1.class);
        intent.putExtra("nombre",nom);
        intent.putExtra("apellidos",ape);
        startActivityForResult(intent,1);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode==RESULT_OK )
        {
            boolean checkRespuesta = data.getExtras().getBoolean("respuesta");
            if(checkRespuesta == true)
                txtCondiciones.setText("Aceptas condiciones: ACEPTADO");
            else
                txtCondiciones.setText("Aceptas condiciones: RECHAZADO");
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }
}