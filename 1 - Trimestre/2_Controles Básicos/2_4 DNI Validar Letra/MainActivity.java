package com.example.controlesbasicos_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    private EditText txtNumeroDNI;
    private EditText txtLetraDNI;
    private TextView result;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumeroDNI = (EditText) findViewById(R.id.txtNumeroDNI);
        txtLetraDNI = (EditText) findViewById(R.id.txtLetraDNI);
        result = (TextView) findViewById(R.id.result);
    }

    public void validar(View view)
    {
        try
        {
            int numeroDNI = Integer.parseInt(txtNumeroDNI.getText().toString());
            if(numeroDNI < 10000000 || numeroDNI > 99999999)
                throw new Exception("El número de DNI no es válido");

            String letraAComprobar = txtLetraDNI.getText().toString();
            HashMap<Integer, String> mapaDNI = new HashMap<Integer, String>()
            {{
                put(0,"T");
                put(1,"R");
                put(2,"W");
                put(3,"A");
                put(4,"G");
                put(5,"M");
                put(6,"Y");
                put(7,"F");
                put(8,"P");
                put(9,"D");
                put(10,"X");
                put(11,"B");
                put(12,"N");
                put(13,"J");
                put(14,"Z");
                put(15,"S");
                put(16,"Q");
                put(17,"V");
                put(18,"H");
                put(19,"L");
                put(20,"C");
                put(21,"K");
                put(22,"E");
            }};
            int resto = numeroDNI%23;
            String letraCorrecta = mapaDNI.get(resto);

            if(letraAComprobar.equals(letraCorrecta))
                result.setText("La letra es CORRECTA");
            else
                result.setText("La letra es INCORRECTA");
        }
        catch(NumberFormatException e)
        {
            result.setText("El valor introducido como DNI no es númerico");
        }
        catch (Exception e)
        {
            result.setText(e.getMessage());
        }
    }
}