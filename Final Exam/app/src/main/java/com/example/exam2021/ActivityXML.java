package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityXML extends AppCompatActivity
{
    private TextView tvEleccionTiempoOutput;
    private TextView tvResultadoTiempo;
    private Tiempo tiempo;

    private String url;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        tvEleccionTiempoOutput = (TextView)findViewById(R.id.tvEleccionTiempoOutput);
        tvResultadoTiempo = (TextView)findViewById(R.id.tvResultadoTiempo);
    }

    private void buscarInfo()
    {
        //Carga del XML mediante tarea Asincrona
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url);
    }

    //Tarea Asíncrona para cargar un XML en segundo plano
    //AsyncTask<Parametros, Progreso, Resultado>
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean>
    {
        //Nuevo hilo
        protected Boolean doInBackground(String... params)
        {
            RssParserDOM domParser = new RssParserDOM(params[0]);
            tiempo = domParser.parse();

            return true;
        }
        //Hilo de interfaz de usuario
        protected void onPostExecute(Boolean result)
        {
            //Tratamos la lista de tiempos
            if (tiempo != null)
            {
                String resultado = getResources().getString(R.string.activity_xml_outputDia) + ": " + tiempo.getFecha() + "\n";
                resultado += getResources().getString(R.string.activity_xml_outputTempMax) + ": " + String.valueOf(tiempo.getTempMax()) + "\n";
                resultado += getResources().getString(R.string.activity_xml_outputTempMin) + ": " + String.valueOf(tiempo.getTempMin()) + "\n";
                resultado += getResources().getString(R.string.activity_xml_outputEstadoCielo) + ": " + tiempo.getEstadoCielo();

                tvResultadoTiempo.setText(resultado);
            }
        }
    }

    public void listener_butBilbao(View view)
    {
        url = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=8050";

        String output = getResources().getString(R.string.activity_xml_butBilbao);
        tvEleccionTiempoOutput.setText(output);

        buscarInfo();
    }

    public void listener_butVitoria(View view)
    {
        url = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=8043";

        String output = getResources().getString(R.string.activity_xml_butVitoria);
        tvEleccionTiempoOutput.setText(output);

        buscarInfo();
    }

    public void listener_butDonostia(View view)
    {
        url = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=4917";

        String output = getResources().getString(R.string.activity_xml_butDonosita);
        tvEleccionTiempoOutput.setText(output);

        buscarInfo();
    }

    public void listener_butVolver(View view)
    {
        finish();
    }
}