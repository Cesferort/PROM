package com.example.controlesbasicosii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Actividad_04_1 extends AppCompatActivity
{
    private TextView txtPregunta;
    private String respuestaCorrecta;
    private RadioGroup radioGroup;
    private RadioButton radBut1;
    private RadioButton radBut2;
    private RadioButton radBut3;
    private RadioButton radBut4;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_04_1);

        txtPregunta = (TextView) findViewById(R.id.txtPregunta);
        respuestaCorrecta = "";
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radBut1 = (RadioButton) findViewById(R.id.radBut1);
        radBut2 = (RadioButton) findViewById(R.id.radBut2);
        radBut3 = (RadioButton) findViewById(R.id.radBut3);
        radBut4 = (RadioButton) findViewById(R.id.radBut4);

        generarPregunta();
    }

    private void generarPregunta()
    {
        Bundle extras = getIntent().getExtras();
        HashMap<String, ArrayList<String>> map = (HashMap<String, ArrayList<String>>) extras.get("map");
        Iterator<String>itPreguntas = map.keySet().iterator();
        int preguntaAleatoria = (int)(Math.random() * map.size());
        for(int numPregunta = 0; itPreguntas.hasNext() && numPregunta <= preguntaAleatoria; numPregunta++)
        {
            String pregunta = itPreguntas.next();
            if(numPregunta == preguntaAleatoria)
            {
                txtPregunta.setText(pregunta);

                ArrayList<String>listaRespuestas = map.get(pregunta);
                respuestaCorrecta = listaRespuestas.get(0);
                Collections.shuffle(listaRespuestas);
                Iterator<String>itListaRespuesta = listaRespuestas.iterator();
                for(int i=0; itListaRespuesta.hasNext(); i++)
                {
                    String respuesta = itListaRespuesta.next();
                    switch(i)
                    {
                        case 0:
                            radBut1.setText(respuesta);
                            break;
                        case 1:
                            radBut2.setText(respuesta);
                            break;
                        case 2:
                            radBut3.setText(respuesta);
                            break;
                        case 3:
                            radBut4.setText(respuesta);
                    }
                }
            }
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }

    public void listenerRespuesta(View view)
    {
        RadioButton radioBut = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String respuestaAComprobar = radioBut.getText().toString();
        if(respuestaAComprobar.equals(respuestaCorrecta))
        {
            Intent intent = new Intent();
            intent.putExtra("check", true);
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra("check", false);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}