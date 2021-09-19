package com.example.juego;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AyudaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda_activity_layout);
        getSupportActionBar().hide(); //Quitar el actionbar
    }
}
