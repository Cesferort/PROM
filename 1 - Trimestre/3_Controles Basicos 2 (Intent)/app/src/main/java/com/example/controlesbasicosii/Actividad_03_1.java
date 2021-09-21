package com.example.controlesbasicosii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Actividad_03_1 extends AppCompatActivity
{
    private TextView txtErrores;
    private TextView txtNomCompleto;
    private TextView txtSexo;
    private TextView txtAficiones;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_03_1);

        txtErrores = (TextView) findViewById(R.id.txtErrores);
        txtNomCompleto = (TextView) findViewById(R.id.txtNomCompleto);
        txtSexo = (TextView) findViewById(R.id.txtSexo);
        txtAficiones = (TextView) findViewById(R.id.txtAficiones);

        rellenarCampos();
    }

    private void rellenarCampos()
    {
        Bundle extras = getIntent().getExtras();
        String listaErrores = extras.getString("listaErrores");
        if(listaErrores.equals("Has sido registrado correctamente."))
        {
            String nom = extras.getString("nom");
            String ape = extras.getString("ape");
            String sex = extras.getString("sex");
            String afi = extras.getString("afi");

            txtNomCompleto.setText(txtNomCompleto.getText().toString() + nom + " " + ape);
            txtSexo.setText(txtSexo.getText().toString() + sex);
            txtAficiones.setText(txtAficiones.getText().toString() + afi);

            txtErrores.setVisibility(View.GONE);
        }
        else
        {
            txtErrores.setText(listaErrores);
            txtNomCompleto.setVisibility(View.GONE);
            txtSexo.setVisibility(View.GONE);
            txtAficiones.setVisibility(View.GONE);
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }
}