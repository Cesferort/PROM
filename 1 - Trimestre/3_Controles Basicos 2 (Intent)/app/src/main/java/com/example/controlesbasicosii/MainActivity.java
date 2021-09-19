package com.example.controlesbasicosii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listenerActividades(View view)
    {
        Intent intent;
        switch(view.getId())
        {
            case R.id.butAct_01:
                intent = new Intent(MainActivity.this, Actividad_01.class);
                startActivity(intent);
                break;
            case R.id.butAct_02:
                intent = new Intent(MainActivity.this, Actividad_02.class);
                startActivity(intent);
                break;
            case R.id.butAct_03:
                intent = new Intent(MainActivity.this, Actividad_03.class);
                startActivity(intent);
                break;
            case R.id.butAct_04:
                intent = new Intent(MainActivity.this, Actividad_04.class);
                startActivity(intent);
        }
    }

    public void listenerButSalir(View view)
    {
        finish();
    }
}