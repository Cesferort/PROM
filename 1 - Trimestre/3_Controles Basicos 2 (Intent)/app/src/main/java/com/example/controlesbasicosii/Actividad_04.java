package com.example.controlesbasicosii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Actividad_04 extends AppCompatActivity
{
    private HashMap<String,HashMap<String,ArrayList<String>>>mapaPreguntas;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;
    private TextView txtCorrectas;
    private TextView txtIncorrectas;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_04);

        mapaPreguntas = new HashMap<String,HashMap<String,ArrayList<String>>>();
        respuestasCorrectas = 0;
        respuestasIncorrectas = 0;

        txtCorrectas = (TextView) findViewById(R.id.txtCorrectas);
        txtIncorrectas = (TextView) findViewById(R.id.txtIncorrectas);

        rellenarMapaPreguntas();
    }

    private void rellenarMapaPreguntas()
    {
        //Generar preguntas relacionadas con Cultura
        HashMap<String,ArrayList<String>>preguntasCultura = new HashMap<String,ArrayList<String>>();
        //1
        ArrayList<String>respCultura1 = new ArrayList<String>();
        respCultura1.add("Nairobi");
        respCultura1.add("Luanda");
        respCultura1.add("Jartum");
        respCultura1.add("Abiyán");
        preguntasCultura.put("¿Cuál es la capital de Kenia?",respCultura1);
        //2
        ArrayList<String>respCultura2 = new ArrayList<String>();
        respCultura2.add("J. R. R. Tolkien");
        respCultura2.add("J. K. Rowling");
        respCultura2.add("Brandon Sanderson");
        respCultura2.add("Dmitry Alexeevich Glukhovsky");
        preguntasCultura.put("¿Quién escribió el señor de los anillos?",respCultura2);
        //3
        ArrayList<String>respCultura3 = new ArrayList<String>();
        respCultura3.add("H. R. Giger");
        respCultura3.add("Andy Warhol");
        respCultura3.add("Ridley Scott");
        respCultura3.add("Dmitry Alexeevich Glukhovsky");
        preguntasCultura.put("¿Artista gráfico conocido por su arte biomecanoide?",respCultura3);
        //4
        ArrayList<String>respCultura4 = new ArrayList<String>();
        respCultura4.add("Groucho");
        respCultura4.add("Chico");
        respCultura4.add("Harpo");
        respCultura4.add("Zeppo");
        preguntasCultura.put("¿Cuál de los hermanos Marx tenía bigote?",respCultura4);
        mapaPreguntas.put("Cultura",preguntasCultura);

        //Generar preguntas relacionadas con Videojuegos
        HashMap<String,ArrayList<String>>preguntasVideojuegos = new HashMap<String,ArrayList<String>>();
        //1
        ArrayList<String>respVideojuegos1 = new ArrayList<String>();
        respVideojuegos1.add("Dr. Robotnik");
        respVideojuegos1.add("Shadow");
        respVideojuegos1.add("Dr. Doofenshmirtz");
        respVideojuegos1.add("Knuckles");
        preguntasVideojuegos.put("¿Cómo se llama el enemigo de Sonic?",respVideojuegos1);
        //2
        ArrayList<String>respVideojuegos2 = new ArrayList<String>();
        respVideojuegos2.add("Racoon City");
        respVideojuegos2.add("Silent Hill");
        respVideojuegos2.add("Madrid");
        respVideojuegos2.add("Mushroom Kingdom");
        preguntasVideojuegos.put("¿Cómo se llama la ciudad en la que desenvuelve Resident Evil?",respVideojuegos2);
        //3
        ArrayList<String>respVideojuegos3 = new ArrayList<String>();
        respVideojuegos3.add("Turok");
        respVideojuegos3.add("Gears of War");
        respVideojuegos3.add("Yakuza 0");
        respVideojuegos3.add("Resident Evil 4");
        preguntasVideojuegos.put("¿Cuál de estos juegos es un First Person Shooter?",respVideojuegos3);
        //4
        ArrayList<String>respVideojuegos4 = new ArrayList<String>();
        respVideojuegos4.add("Quake 4");
        respVideojuegos4.add("Rage");
        respVideojuegos4.add("Commander Keen");
        respVideojuegos4.add("Dangerous Dave");
        preguntasVideojuegos.put("¿Cuál de estos videojuegos no fue desarrollado por idSoftware?",respVideojuegos4);
        mapaPreguntas.put("Videojuegos",preguntasVideojuegos);
    }

    public void listenerCategoria(View view)
    {
        HashMap<String,ArrayList<String>>map = new HashMap<String,ArrayList<String>>();
        switch(view.getId())
        {
            case R.id.butCultura:
                map = mapaPreguntas.get("Cultura");
                break;
            case R.id.butVideojuegos:
                map = mapaPreguntas.get("Videojuegos");
        }
        Intent intent = new Intent(Actividad_04.this, Actividad_04_1.class);
        intent.putExtra("map",map);
        startActivityForResult(intent,3);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3 && resultCode == RESULT_OK )
        {
            boolean check = data.getExtras().getBoolean("check");
            if(check == true)
                respuestasCorrectas++;
            else
                respuestasIncorrectas++;

            txtCorrectas.setText(String.valueOf(respuestasCorrectas));
            txtIncorrectas.setText(String.valueOf(respuestasIncorrectas));
        }
    }

    public void listenerButVolver(View view)
    {
        finish();
    }
}