package com.example.ficheros;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Ejercicio_3 extends AppCompatActivity
{
    private ListView lvWebFav;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        lvWebFav = (ListView)findViewById(R.id.lvWebFav);
        lvWebFav.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                WebFavorita webFavorita = (WebFavorita) parent.getItemAtPosition(position);
                String urlWeb = webFavorita.getUrlWeb().trim();

                Uri uri = Uri.parse(urlWeb);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        llenarListaWebs();
    }

    private void llenarListaWebs()
    {
        try
        {
            ArrayList<WebFavorita>listaWebFav = conseguirWebsDeRecurso();
            if(listaWebFav == null)
                throw new Exception("No se ha conseguido acceder al archivo contenedor de Webs Favoritas.");

            WebFavorita datos[] = new WebFavorita[listaWebFav.size()];
            for(int contDatos = 0; contDatos < datos.length; contDatos++)
                datos[contDatos] = listaWebFav.get(contDatos);

            AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, datos);
            lvWebFav.setAdapter(adaptadorTitulares);
        }
        catch(Exception e)
        {
            //DO NOTHING
        }
    }

    private ArrayList<WebFavorita> conseguirWebsDeRecurso()
    {
        ArrayList<WebFavorita>listaWebFav = new ArrayList<WebFavorita>();
        try
        {
            InputStream is = getResources().openRawResource(R.raw.ej3_webs_fav);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linea = br.readLine();
            for (int contLinea = 0; linea != null; contLinea++)
            {
                try
                {
                    String lineaSeccionada[] = linea.split(";");
                    if(lineaSeccionada.length != 4)
                        throw new Exception("La línea número " + contLinea + " del archivo ej3_webs_fav.txt ha sido mal formateada.");
                    String nomWeb = lineaSeccionada[0];
                    String urlWeb = lineaSeccionada[1];
                    String logoWeb = lineaSeccionada[2];
                    String idWeb = lineaSeccionada[3];

                    WebFavorita webFavorita = new WebFavorita(nomWeb,urlWeb,logoWeb,idWeb);
                    listaWebFav.add(webFavorita);
                }
                catch(Exception e)
                {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG ).show();
                }
                linea = br.readLine();
            }
            br.close();
            return listaWebFav;
        }
        catch(Exception e)
        {
            Toast.makeText(this,"No se ha conseguido acceder al archivo contenedor de Webs Favoritas.",Toast.LENGTH_LONG ).show();
        }
        return null;
    }

    private class AdaptadorTitulares extends ArrayAdapter<WebFavorita>
    {
        private WebFavorita[] datos;

        public AdaptadorTitulares(Context context, WebFavorita[] datos)
        {
            super(context, R.layout.listitem_webfavorita, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_webfavorita, null);

            String idLogoWeb = datos[position].getLogoWeb().trim();
            int resLogoWebID = Ejercicio_3.getResID(idLogoWeb, R.drawable.class);
            ImageView imgWeb = (ImageView)item.findViewById(R.id.imgWeb);
            if(resLogoWebID != -1)
                imgWeb.setImageResource(resLogoWebID);

            TextView tvNomWeb = (TextView)item.findViewById(R.id.tvNomWeb);
            tvNomWeb.setText(datos[position].toString());

            TextView tvUrlWeb = (TextView)item.findViewById(R.id.tvUrlWeb);
            tvUrlWeb.setText(datos[position].getUrlWeb());

            TextView tvIdWeb = (TextView)item.findViewById(R.id.tvIdWeb);
            tvIdWeb.setText(datos[position].getIdWeb());

            return item;
        }
    }

    private static int getResID(String resName, Class<?> c)
    {
        try
        {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}