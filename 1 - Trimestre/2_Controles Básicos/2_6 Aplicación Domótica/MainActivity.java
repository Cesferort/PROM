package com.example.controlesbasicos_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private ImageView imgCam1;
    private ImageView imgCam2;
    private ImageView imgLuz1;
    private ImageView imgLuz2;
    private Boolean cam1Encendida;
    private Boolean cam2Encendida;
    private Boolean luz1Encendida;
    private Boolean luz2Encendida;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCam1 = (ImageView) findViewById(R.id.imgCam1);
        imgCam2 = (ImageView) findViewById(R.id.imgCam2);
        imgLuz1 = (ImageView) findViewById(R.id.imgLuz1);
        imgLuz2 = (ImageView) findViewById(R.id.imgLuz2);

        cam1Encendida = false;
        cam2Encendida = false;
        luz1Encendida = false;
        luz2Encendida = false;
    }

    public void swiCam1Listener(View view)
    {
        if(cam1Encendida == false)
        {
            imgCam1.setVisibility(View.VISIBLE);
            cam1Encendida = true;
        }
        else
        {
            imgCam1.setVisibility(View.INVISIBLE);
            cam1Encendida = false;
        }
    }

    public void swiLuz1Listener(View view)
    {
        if(luz1Encendida == false)
        {
            imgLuz1.setImageResource(R.drawable.bombilla_encendida);
            luz1Encendida = true;
        }
        else
        {
            imgLuz1.setImageResource(R.drawable.bombilla_apagada);
            luz1Encendida = false;
        }
    }

    public void togCam2Listener(View view)
    {
        if(cam2Encendida == false)
        {
            imgCam2.setVisibility(View.VISIBLE);
            cam2Encendida = true;
        }
        else
        {
            imgCam2.setVisibility(View.INVISIBLE);
            cam2Encendida = false;
        }
    }

    public void togLuz2Listener(View view)
    {
        if(luz2Encendida == false)
        {
            imgLuz2.setImageResource(R.drawable.bombilla_encendida);
            luz2Encendida = true;
        }
        else
        {
            imgLuz2.setImageResource(R.drawable.bombilla_apagada);
            luz2Encendida = false;
        }
    }
}