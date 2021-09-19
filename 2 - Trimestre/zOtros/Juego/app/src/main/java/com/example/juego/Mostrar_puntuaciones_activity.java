package com.example.juego;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Mostrar_puntuaciones_activity extends AppCompatActivity {
    private Puntuaciones[] puntos = new Puntuaciones[10];
    private int numero;
    private ListView mainListView;
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mostrar_puntuaciones_layout);

        // recurso ListView.
        leerFichero();
        mainListView = (ListView) findViewById( R.id.mainListView );

        String[] puntuaciones = new String[numero];
        for (int i=0;i<numero;i++){
            puntuaciones[i]=i+1+". "+puntos[i].getNombre()+" "
                    +puntos[i].getPuntuacion();
        }
        ArrayList<String> puntuacionesLista = new ArrayList<String>();
        puntuacionesLista.addAll( Arrays.asList(puntuaciones) );
        listAdapter = new ArrayAdapter<String>(this, R.layout.fila_puntuacion,
                puntuacionesLista);
        mainListView.setAdapter( listAdapter );
    }

    public void leerFichero(){
        String nombre;
        int puntuaje;
        Puntuaciones aux;
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("puntuaciones.txt")));

            numero = Integer.parseInt(fin.readLine());
            for (int i=0;i<numero;i++){
                nombre = fin.readLine();
                puntuaje = Integer.parseInt(fin.readLine());
                aux = new Puntuaciones(nombre,puntuaje);
                puntos[i]=aux;
            }
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }
    }


}
