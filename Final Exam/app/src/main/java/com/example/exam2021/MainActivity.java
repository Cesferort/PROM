package com.example.exam2021;
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

    public void listener_butBD(View view)
    {
        Intent intent = new Intent(MainActivity.this, ActivityBDPrincipal.class);
        startActivity(intent);
    }

    public void listener_butXML(View view)
    {
        Intent intent = new Intent(MainActivity.this, ActivityXML.class);
        startActivity(intent);
    }

    public void listener_butSalir(View view)
    {
        finish();
    }
}