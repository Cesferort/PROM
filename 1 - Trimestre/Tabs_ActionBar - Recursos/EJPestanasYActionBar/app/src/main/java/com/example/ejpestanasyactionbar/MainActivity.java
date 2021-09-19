package com.example.ejpestanasyactionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ejpestanasyactionbar.R.layout.activity_main);
    }

    public void pestanas_TabHost(View v){
        Intent intento = new Intent (MainActivity.this, PestanasTabHostActivity.class);
        startActivity(intento);
    }
    public void pestanas_FragmentTabHost (View v){
        Intent intento = new Intent (MainActivity.this, PestanasFragmentTabHostActivity.class);
        startActivity(intento);
    }
    public void pestana_TabLayout (View  v){
        Intent intento = new Intent (MainActivity.this, PestanasViewPageActivity.class);
        startActivity(intento);
    }

    public void ejActionBar(View view) {
        Intent intento = new Intent (MainActivity.this, ActionBarActivity.class);
        startActivity(intento);
    }

}