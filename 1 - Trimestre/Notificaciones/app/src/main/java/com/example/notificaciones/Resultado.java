package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity
{
    private TextView txtResult;
    private boolean esCorrecto;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtResult = (TextView) findViewById(R.id.txtResult);
        esCorrecto = false;
        comprobarSuma();
    }

    private void comprobarSuma()
    {
        if(sumaEsCorrecta()==true)
        {
            txtResult.setText("El resultado de la operación es CORRECTO");
            esCorrecto = true;
        }
        else
        {
            txtResult.setText("El resultado de la operación es INCORRECTO");
            esCorrecto = false;
        }
    }

    private boolean sumaEsCorrecta()
    {
        Bundle extras = getIntent().getExtras();
        int n1 = extras.getInt("n1");
        int n2 = extras.getInt("n2");
        int resultAComprobar = extras.getInt("resultAComprobar");

        if(n1 + n2 == resultAComprobar)
            return true;
        return false;
    }

    public void listenerButVolver(View view)
    {
        if(esCorrecto==true)
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