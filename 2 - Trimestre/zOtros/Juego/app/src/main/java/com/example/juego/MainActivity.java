package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Puntuaciones[] puntos = new Puntuaciones[10];
    private int numero;
    private String nombre;
    private int puntospartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new VistaJuego(this));
        setContentView(R.layout.activity_main);
        comprobarFichero();
        leerFichero();

        if (this.getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            String score = (String) b.get("puntuacion");
            if (score == null)
                score="0";
            puntospartida=Integer.parseInt(score);
            comprobarScore(puntospartida);
        }

    }

    public void comprobarScore(int s){
        if (s > 0 &&(numero<10 || puntos[9].getPuntuacion()<s)){
            setContentView(R.layout.intro_nombre_puntuacion);
        }
    }

    public void jugar(View view){
        setContentView(new VistaJuego(this));
    }

    public void ayuda(View view){
        Toast.makeText(getApplicationContext(),
                "En este botón veremos la ayuda",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AyudaActivity.class);
        startActivity(intent);
    }

    public void mostrar(View view){
        Toast.makeText(getApplicationContext(),
                "En este botón se mostrarán las puntuaciones",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Mostrar_puntuaciones_activity.class);
        startActivity(intent);
    }

    public void salir(View view){
        escribirFichero();
        // Eliminamos las actividades si existen
        ActivityCompat.finishAffinity(this);
        System.exit(0);
        finish();
    }

    public void aceptarNombre(View view){
        EditText name = (EditText)findViewById(R.id.editTextNombreJugador);
        nombre = name.getText().toString();
        if (numero<10)numero++;
        insertaPuntuacion(nombre,puntospartida);
        setContentView(R.layout.activity_main);
    }

    public void resetear(View view){
        Toast.makeText(getApplicationContext(),
                "En este botón resetearemos todas la puntuaciones",
                Toast.LENGTH_SHORT).show();
        crearFichero();

    }

    public void insertaPuntuacion(String n,int s){
        int aux=10;
        Puntuaciones aux2 = new Puntuaciones(n,s);
        for (int i=0;i<numero-1;i++){
            if (puntos[i].getPuntuacion()<s){
                aux = i;
                break;
            }
        }
        if (aux == 10)aux=numero-1;
        for (int j=numero-1;j>aux;j--){
            puntos[j]=puntos[j-1];
        }
        puntos[aux]=aux2;
        escribirFichero();
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

    public void escribirFichero() {
        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("puntuaciones.txt", Context.MODE_PRIVATE));

            fout.write(numero + "\n");
            for (int i = 0; i < numero; i++) {
                fout.write(puntos[i].getNombre() + "\n");
                fout.write(puntos[i].getPuntuacion() + "\n");
            }
            fout.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }

    public void comprobarFichero(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("puntuaciones.txt")));
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            crearFichero();
        }
    }

    public void crearFichero(){
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("puntuaciones.txt", Context.MODE_PRIVATE));

            fout.write("0");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }
}
