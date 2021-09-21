package com.example.ficheros;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner spinProvincias;
    private boolean provinciaSeleccionada;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        provinciaSeleccionada = false;

        spinProvincias = (Spinner)findViewById(R.id.spinProvincias);
        spinProvincias.setOnItemSelectedListener(this);

        llenarSpinnerDeProvincias();
    }

    private void llenarSpinnerDeProvincias()
    {
        try
        {
            ArrayList<String>datos = new ArrayList<String>();

            InputStream is = getResources().openRawResource(R.raw.ej2_provincias);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            int contProvincia = -1;
            String linea = br.readLine();
            while (linea != null)
            {
                datos.add(++contProvincia + ".- " + linea);
                linea = br.readLine();
            }
            br.close();

            //Creamos el adaptador para el spinner
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinProvincias.setAdapter(adaptador);
        }
        catch(Exception e)
        {
            Toast.makeText(this,"No se ha podido cargar la lista de provincias",Toast.LENGTH_LONG ).show();
        }
    }

    public void listener_butEj3(View view)
    {
        Intent intent = new Intent(Ejercicio_2.this, Ejercicio_3.class);
        startActivityForResult(intent,1);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if (provinciaSeleccionada == true)
        {
            TextView txtView = (TextView) view;
            Toast.makeText(this, "Provincia " + txtView.getText() + " seleccionada.", Toast.LENGTH_SHORT).show();
        }
        else
            provinciaSeleccionada = true;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //DO NOTHING
    }
}