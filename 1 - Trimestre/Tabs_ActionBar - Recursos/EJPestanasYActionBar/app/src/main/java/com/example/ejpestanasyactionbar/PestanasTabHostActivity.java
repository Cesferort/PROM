package com.example.ejpestanasyactionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

import com.example.ejpestanasyactionbar.R;

public class PestanasTabHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_tab_host);

        //Referencia al control principal TabHost y preparación para su configuración
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        //Objeto de tipo TabSpec para cada una de las pestañas
        //asignación layout correspondiente a la pestaña (setContent())
        //Indicaremos texto y/o icono de la pestaña (setIndicator(texto, icono))
        TabHost.TabSpec spec = tabs.newTabSpec("miTab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAB 1", getResources().getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB 2", getResources().getDrawable(android.R.drawable.ic_dialog_info));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab3");
        spec.setContent(R.id.tab3);
        //spec.setIndicator("TAB 3", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
        spec.setIndicator("", getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        //Pestaña activa al arrancar la aplicacion
        tabs.setCurrentTab(0);
    }
}