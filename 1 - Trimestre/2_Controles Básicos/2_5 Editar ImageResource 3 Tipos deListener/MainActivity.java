package com.example.controlesbasicos_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView imgNavegador;
    private Button butBing;
    private Button butGoogle;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgNavegador = (ImageView) findViewById(R.id.imgNavegador);
        butBing = (Button) findViewById(R.id.butBing);
        butBing.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                imgNavegador.setImageResource(R.drawable.bing);
            }
        });
        butGoogle = (Button) findViewById(R.id.butGoogle);
        butGoogle.setOnClickListener(this);
    }

    public void butYahooListener(View view)
    {
        imgNavegador.setImageResource(R.drawable.yahoo);
    }

    public void onClick(View view)
    {
        imgNavegador.setImageResource(R.drawable.google);
    }
}