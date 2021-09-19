package com.example.controlesbasicos_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText primerValor;
    private EditText segundoValor;
    private TextView resultado;

    private Button butSuma;
    private Button butResta;
    private Button butMultiplica;
    private Button butDivide;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primerValor = (EditText) findViewById(R.id.primerValor);
        segundoValor = (EditText) findViewById(R.id.segundoValor);
        resultado = (TextView) findViewById(R.id.resultado);

        butSuma = (Button) findViewById(R.id.butSuma);
        butResta = (Button) findViewById(R.id.butResta);
        butMultiplica = (Button) findViewById(R.id.butMultiplica);
        butDivide = (Button) findViewById(R.id.butDivide);

    }

    public void operar(View view)
    {
        try
        {
            int n1 = Integer.parseInt(primerValor.getText().toString());
            int n2 = Integer.parseInt(segundoValor.getText().toString());

            if(butSuma.isPressed())                                    //SUMA
            {
                resultado.setText("Resultado:\t"+(n1+n2));
            }
            else if(butResta.isPressed())                               //RESTA
            {
                resultado.setText("Resultado:\t"+(n1-n2));
            }
            else if(butMultiplica.isPressed())                               //MULTIPLICA
            {
                resultado.setText("Resultado:\t"+(n1*n2));
            }
            else                                                       //DIVIDE
            {
                resultado.setText("Resultado:\t"+(n1/n2));
            }
        }
        catch(NumberFormatException e)
        {
            resultado.setText("Resultado: Se han introcido valores de un tipo incorrecto.");
        }
        catch(Exception e)
        {
            resultado.setText("Resultado: Operación matemática no permitida.");
        }
    }
}