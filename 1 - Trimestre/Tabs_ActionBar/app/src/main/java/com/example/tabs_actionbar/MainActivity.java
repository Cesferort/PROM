package com.example.tabs_actionbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TabHost tabs;
    private ListView lvChats;
    private ListView lvEstados;
    private ListView lvLlamadas;
    private MenuItem mnItem_Buscador;
    private MenuItem mnItem_NuevoGrupo;
    private MenuItem mnItem_NuevaDifusion;
    private MenuItem mnItem_WhatsWeb;
    private MenuItem mnItem_MsnDestacados;
    private MenuItem mnItem_PrivEstados;
    private MenuItem mnItem_BorrarLlamadas;
    private MenuItem mnItem_Ajustes;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WhatsApp");

        lvChats = (ListView)findViewById(R.id.lvChats);
        lvEstados = (ListView)findViewById(R.id.lvEstados);
        lvLlamadas = (ListView)findViewById(R.id.lvLlamadas);

        tabs = (TabHost)findViewById(android.R.id.tabhost);

        rellenarLista();
        prepararTabs();
    }

    private void rellenarLista()
    {
        final Chat[] CHATS =
                {
                        new Chat("Pepa", "Hola me llamo..."),
                        new Chat("Para", "Hola me llamo..."),
                        new Chat("Pepin", "Hola me llamo..."),
                        new Chat("Pon", "Hola me llamo..."),
                        new Chat("Pan", "Hola me llamo..."),
                        new Chat("Desconocido", "Hola me llamo..."),
                        new Chat("Desconocida", "Hola me llamo..."),
                        new Chat("Pepe", "Hola me llamo..."),
                        new Chat("La Jenny", "Hola me llamo..."),
                        new Chat("El Jonan", "Hola me llamo..."),
                        new Chat("El Txori", "Hola me llamo..."),
                        new Chat("Desconocido2", "Hola me llamo..."),
                        new Chat("Desconocida2", "Hola me llamo...")
                };
        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, CHATS);
        lvChats.setAdapter(adaptadorTitulares);
        lvEstados.setAdapter(adaptadorTitulares);
        lvLlamadas.setAdapter(adaptadorTitulares);
    }

    private class AdaptadorTitulares extends ArrayAdapter<Chat>
    {
        private Chat[] datos;

        public AdaptadorTitulares(Context context, Chat[] datos)
        {
            super(context, R.layout.listitem_chat, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_chat, null);

            TextView tvNomContacto = (TextView)item.findViewById(R.id.tvNomContacto);
            tvNomContacto.setText(datos[position].toString());

            TextView tvTxtAbreviado = (TextView)item.findViewById(R.id.tvTxtAbreviado);
            tvTxtAbreviado.setText(datos[position].getTxtAbreviado());

            return item;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);

        mnItem_Buscador = (MenuItem)menu.findItem(R.id.mnItem_Buscador);
        mnItem_NuevoGrupo = (MenuItem)menu.findItem(R.id.mnItem_NuevoGrupo);
        mnItem_NuevaDifusion = (MenuItem)menu.findItem(R.id.mnItem_NuevaDifusion);
        mnItem_WhatsWeb = (MenuItem)menu.findItem(R.id.mnItem_WhatsWeb);
        mnItem_MsnDestacados = (MenuItem)menu.findItem(R.id.mnItem_MsnDestacados);
        mnItem_PrivEstados = (MenuItem)menu.findItem(R.id.mnItem_PrivEstados);
        mnItem_BorrarLlamadas = (MenuItem)menu.findItem(R.id.mnItem_BorrarLlamadas);
        mnItem_Ajustes = (MenuItem)menu.findItem(R.id.mnItem_Ajustes);

        return super.onCreateOptionsMenu(menu);
    }

    private void prepararTabs()
    {
        Resources res = getResources();

        //Comenzar configuración del TabHost
        tabs.setup();

        //Primer tab
        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Chats");
        tabs.addTab(spec);

        //Segundo tab
        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Estados");
        tabs.addTab(spec);

        //Tercer tab
        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Llamadas");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener()
        {
            public void onTabChanged(String selectedTab)
            {
                switch (selectedTab)
                {
                    case "mitab1":
                        mnItem_NuevoGrupo.setVisible(true);
                        mnItem_NuevaDifusion.setVisible(true);
                        mnItem_WhatsWeb.setVisible(true);
                        mnItem_MsnDestacados.setVisible(true);
                        mnItem_PrivEstados.setVisible(false);
                        mnItem_BorrarLlamadas.setVisible(false);
                        break;
                    case "mitab2":
                        mnItem_NuevoGrupo.setVisible(false);
                        mnItem_NuevaDifusion.setVisible(false);
                        mnItem_WhatsWeb.setVisible(false);
                        mnItem_MsnDestacados.setVisible(false);
                        mnItem_PrivEstados.setVisible(true);
                        mnItem_BorrarLlamadas.setVisible(false);
                        break;
                    case "mitab3":
                        mnItem_NuevoGrupo.setVisible(false);
                        mnItem_NuevaDifusion.setVisible(false);
                        mnItem_WhatsWeb.setVisible(false);
                        mnItem_MsnDestacados.setVisible(false);
                        mnItem_PrivEstados.setVisible(false);
                        mnItem_BorrarLlamadas.setVisible(true);
                        break;
                }
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mnItem_Buscador:
                System.out.println("Buscador seleccionado");
                return true;
            case R.id.mnItem_NuevoGrupo:
                System.out.println("NuevoGrupo seleccionado");
                return true;
            case R.id.mnItem_NuevaDifusion:
                System.out.println("NuevaDifusión seleccionado");
                return true;
            case R.id.mnItem_WhatsWeb:
                System.out.println("WhatsWeb seleccionado");
                return true;
            case R.id.mnItem_MsnDestacados:
                System.out.println("MensajesDestacados seleccionado");
                return true;
            case R.id.mnItem_PrivEstados:
                System.out.println("PrivacidadEstados seleccionado");
                return true;
            case R.id.mnItem_BorrarLlamadas:
                System.out.println("BorrarLlamadas seleccionado");
                return true;
            case R.id.mnItem_Ajustes:
                System.out.println("Ajustes seleccionado");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}