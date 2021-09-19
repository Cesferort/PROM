package com.example.controlesbasicosii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Actividad_02 extends AppCompatActivity
{
    private TextView txtN1;
    private TextView txtN2;
    private TextView txtResultado;
    private TextView txtCorrectas;
    private TextView txtIncorrectas;

    private int n1;
    private int n2;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_02);

        txtN1 = (TextView) findViewById(R.id.txtN1);
        txtN2 = (TextView) findViewById(R.id.txtN2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtCorrectas = (TextView) findViewById(R.id.txtCorrectas);
        txtIncorrectas = (TextView) findViewById(R.id.txtIncorrectas);

        n1 = 0;
        n2 = 0;
        respuestasCorrectas = 0;
        respuestasIncorrectas = 0;
        crearNumsRandom();
    }

    private void crearNumsRandom()
    {
        n1 = (int)(Math.random() * 100);
        txtN1.setEnabled(true);
        txtN1.setText(String.valueOf(n1));
        txtN1.setEnabled(false);

        n2 = (int)(Math.random() * 100);
        txtN2.setEnabled(true);
        txtN2.setText(String.valueOf(n2));
        txtN2.setEnabled(false);
    }

    public void ButComprobarListener(View view)
    {
        try
        {
            int resultAComprobar = Integer.parseInt(txtResultado.getText().toString());
            Intent intent = new Intent(Actividad_02.this, Actividad_02_1.class);
            intent.putExtra("n1",n1);
            intent.putExtra("n2",n2);
            intent.putExtra("resultAComprobar",resultAComprobar);
            startActivityForResult(intent,2);
        }
        catch(NumberFormatException e)
        {
            //VALOR INTRODUCIDO NO NUMÉRICO
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode==RESULT_OK )
        {
            //Limpiar y refrescar randoms
            crearNumsRandom();
            txtResultado.setText("");

            boolean check = data.getExtras().getBoolean("check");
            if(check == true)
                respuestasCorrectas++;
            else
                respuestasIncorrectas++;

            txtCorrectas.setText("PREGUNTAS CORRECTAS: " + respuestasCorrectas);
            txtIncorrectas.setText("INCORRECTAS: " + respuestasIncorrectas);
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }
}