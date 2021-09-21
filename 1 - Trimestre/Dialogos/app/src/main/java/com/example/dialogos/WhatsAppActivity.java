package com.example.dialogos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WhatsAppActivity extends AppCompatActivity
{
    private String nomUser;
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
        setContentView(R.layout.whatsapp);

        prepararToolbar();

        lvChats = (ListView)findViewById(R.id.lvChats);
        lvEstados = (ListView)findViewById(R.id.lvEstados);
        lvLlamadas = (ListView)findViewById(R.id.lvLlamadas);

        tabs = (TabHost)findViewById(android.R.id.tabhost);

        rellenarLista();
        prepararTabs();
    }

    private void prepararToolbar()
    {
        //Recibir nombre de usuario a través del intent
        Bundle extras = getIntent().getExtras();
        nomUser = (String) extras.get("nomUser");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getText(R.string.WhatsAppActivity_toolbar_bienvenido) + nomUser);
    }

    private void rellenarLista()
    {
        final Chat[] CHATS =
                {
                        new Chat("Pepa", "Hola " + nomUser + ", blablabla..."),
                        new Chat("Para", "Hola " + nomUser + ", blebleble..."),
                        new Chat("Pepin", "Hola " + nomUser + ", bliblibli..."),
                        new Chat("Pon", "Hola " + nomUser + ", blobloblo..."),
                        new Chat("Pan", "Hola " + nomUser + ", blublublu..."),
                        new Chat("Pepe", "Hola " + nomUser + ", bla bla bla..."),
                        new Chat("La Jenny", "Hola " + nomUser + ", ble ble ble..."),
                        new Chat("El Jonan", "Hola " + nomUser + ", bli bli bli..."),
                        new Chat("El Txori", "Hola " + nomUser + ", blo blo blo..."),
                        new Chat("Desconocido", "Hola " + nomUser + ", blu blu lu..."),
                        new Chat("Desconocida", "Hola " + nomUser + ", blablabla..."),
                        new Chat("Desconocido2", "Hola " + nomUser + ", blebleble..."),
                        new Chat("Desconocida2", "Hola " + nomUser + ", bliblibli...")
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
        //Comenzar configuración del TabHost
        tabs.setup();

        //Primer tab
        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getText(R.string.WhatsAppActivity_tabs_mitab1));
        tabs.addTab(spec);

        //Segundo tab
        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getText(R.string.WhatsAppActivity_tabs_mitab2));
        tabs.addTab(spec);

        //Tercer tab
        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator(getText(R.string.WhatsAppActivity_tabs_mitab3));
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
                System.out.println("0");
                return true;
            case R.id.mnItem_NuevoGrupo:
                System.out.println("1");
                return true;
            case R.id.mnItem_NuevaDifusion:
                System.out.println("2");
                return true;
            case R.id.mnItem_WhatsWeb:
                System.out.println("3");
                return true;
            case R.id.mnItem_MsnDestacados:
                System.out.println("4");
                return true;
            case R.id.mnItem_PrivEstados:
                System.out.println("5");
                return true;
            case R.id.mnItem_BorrarLlamadas:
                System.out.println("6");
                return true;
            case R.id.mnItem_Ajustes:
                System.out.println("7");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //App minimazada
    /*
    protected void onUserLeaveHint()
    {
        System.out.println("APP MINIMIZADA");
        super.onUserLeaveHint();
    }
    */
}