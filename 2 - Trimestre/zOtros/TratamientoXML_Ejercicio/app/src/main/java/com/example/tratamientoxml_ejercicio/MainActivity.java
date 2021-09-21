package com.example.tratamientoxml_ejercicio;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private final String URL = "https://www.aemet.es/xml/municipios/localidad_01059.xml";
    private TextView tvResultado;
    private List<Tiempo> listaTiempos;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
        cargarXMLConDOM(tvResultado);
    }

    public void cargarXMLConDOM(View v)
    {
        //Carga del XML mediante tarea Asincrona
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(URL);
    }

    //Tarea Asíncrona para cargar un XML en segundo plano
    //AsyncTask<Parametros, Progreso, Resultado>
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean>
    {
        //Nuevo hilo
        protected Boolean doInBackground(String... params)
        {
            RssParserDOM domParser = new RssParserDOM(params[0]);
            listaTiempos = domParser.parse();

            return true;
        }
        //Hilo de interfaz de usuario
        protected void onPostExecute(Boolean result)
        {
            //tvResultado.setText("");

            //Tratamos la lista de tiempos
            if (listaTiempos != null)
            {
                String resultado = "";
                for (int i = 0; i < listaTiempos.size(); i++)
                {
                    resultado += ("\n" + String.valueOf(i+1) + "\n------------------------------------\n");

                    Tiempo tiempo = listaTiempos.get(i);
                    resultado += ("\nFecha:\t" + tiempo.getFecha());
                    resultado += ("\nPrecipitacion:\n" + leerArrayList(tiempo.getPrecipitacion()));
                    resultado += ("\nCotaNieve:\n" + leerArrayList(tiempo.getCotaNieve()));
                    resultado += ("\nEstadosCielo:\n" + leerArrayList(tiempo.getEstadosCielo()));
                }
                System.out.println(resultado);
            }
        }
    }

    private String leerArrayList(ArrayList<String> arrayList)
    {
        String resultado = "";
        Iterator<String>itArrayList = arrayList.iterator();
        for(int i = 0; itArrayList.hasNext(); i++)
        {
            String dato = "";
            dato += itArrayList.next();
            switch(i)
            {
                case 0:
                    dato = "00-24\t" + dato;
                    break;
                case 1:
                    dato = "00-12\t" + dato;
                    break;
                case 2:
                    dato = "12-24\t" + dato;
                    break;
                case 3:
                    dato = "00-06\t" + dato;
                    break;
                case 4:
                    dato = "06-12\t" + dato;
                    break;
                case 5:
                    dato = "12-18\t" + dato;
                    break;
                case 6:
                    dato = "18-24\t" + dato;
                    break;
                default:
                    dato = "?-?\t" + dato;
            }
            resultado += dato + "\n";
        }
        return resultado;
    }
}