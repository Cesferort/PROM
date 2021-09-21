package com.example.controlesbasicosii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Actividad_03 extends AppCompatActivity
{
    private EditText txtNombre;
    private EditText txtApellidos;
    private RadioGroup radioGroup;
    private CheckBox checkMusica;
    private CheckBox checkLectura;
    private CheckBox checkDeporte;
    private CheckBox checkViajar;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_03);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        checkMusica = (CheckBox) findViewById(R.id.checkMusica);
        checkLectura = (CheckBox) findViewById(R.id.checkLectura);
        checkDeporte = (CheckBox) findViewById(R.id.checkDeporte);
        checkViajar = (CheckBox) findViewById(R.id.checkViajar);
    }

    public void listenerButEnviar(View view)
    {
        String listaErrores = "";
        int cantidadErrores = 0;

        //Comprobación Nombre
        if(txtNombre.getText().toString().length() == 0)
        {
            listaErrores += "\n- No se ha introducido un Nombre";
            cantidadErrores++;
        }
        //Comprobación Apellidos
        if(txtApellidos.getText().toString().length() == 0)
        {
            listaErrores += "\n- No se han introducido los Apellidos";
            cantidadErrores++;
        }
        //Comprobación Sexo
        if(radioGroup.getCheckedRadioButtonId() == -1)
        {
            listaErrores += "\n- No se ha escogido Sexo";
            cantidadErrores++;
        }

        if(cantidadErrores > 0)
        {
            if(cantidadErrores == 1)
                listaErrores = "Se ha encontrado un dato no permitido:" + listaErrores;
            else
                listaErrores = "Se han encontrado datos no permitidos:" + listaErrores;

            Intent intent = new Intent(Actividad_03.this, Actividad_03_1.class);
            intent.putExtra("listaErrores",listaErrores);
            startActivity(intent);
        }
        else
        {
            listaErrores = "Has sido registrado correctamente.";
            String nom = txtNombre.getText().toString();
            String ape = txtApellidos.getText().toString();
            RadioButton radBut = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            String sex = radBut.getText().toString();

            String afi = "";
            if(checkMusica.isChecked())
                afi += "\n\tMúsica";
            if(checkLectura.isChecked())
                afi += "\n\tLectura";
            if(checkDeporte.isChecked())
                afi += "\n\tDeporte";
            if(checkViajar.isChecked())
                afi += "\n\tViajar";
            if(afi == "")
                afi = " No tiene aficiones.";

            Intent intent = new Intent(Actividad_03.this, Actividad_03_1.class);
            intent.putExtra("listaErrores",listaErrores);
            intent.putExtra("nom",nom);
            intent.putExtra("ape",ape);
            intent.putExtra("sex",sex);
            intent.putExtra("afi",afi);
            startActivity(intent);
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }
}