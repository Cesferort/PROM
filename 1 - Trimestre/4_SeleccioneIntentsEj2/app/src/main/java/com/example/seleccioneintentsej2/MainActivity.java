package com.example.seleccioneintentsej2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private ListView lvWebFavorita;
    private ImageView imgWeb;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWebFavorita = (ListView) findViewById(R.id.lvWebFavorita);
        imgWeb = (ImageView) findViewById(R.id.imgWeb);

        rellenarLista();
        eventos();
    }

    private void rellenarLista()
    {
        final WebFavorita[] DATOS =
                {
                    new WebFavorita(1,"YouTube","https://www.youtube.com/",R.drawable.img_youtube),
                    new WebFavorita(2,"Twitter","https://twitter.com/",R.drawable.img_twitter),
                    new WebFavorita(3,"Reddit","https://www.reddit.com/",R.drawable.img_youtube),
                    new WebFavorita(4,"Facebook","https://www.facebook.com/",R.drawable.img_twitter)
                };
        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, DATOS);
        lvWebFavorita.setAdapter(adaptadorTitulares);
    }

    private class AdaptadorTitulares extends ArrayAdapter<WebFavorita>
    {
        private WebFavorita[] datos;

        public AdaptadorTitulares(Context context, WebFavorita[] datos)
        {
            super(context, R.layout.listitem_web, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_web, null);

            TextView tvItem = (TextView)item.findViewById(R.id.tvNomWeb);
            tvItem.setText(datos[position].toString());

            TextView tvUrlWeb = (TextView)item.findViewById(R.id.tvUrlWeb);
            tvUrlWeb.setText(datos[position].getUrlWeb());

            //ImageView imgWeb = (ImageView) findViewById(R.id.imgWeb);

            if(imgWeb != null)
            {
                int idImg = datos[position].getImgWeb();

                Log.i("INFO","HOLAAAAAA "+idImg);
                imgWeb.setImageResource(idImg);
            }

            return item;
        }
    }

    private void eventos()
    {
        lvWebFavorita.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                WebFavorita webSeleccionada = (WebFavorita) parent.getItemAtPosition(position);
                String urlWeb = webSeleccionada.getUrlWeb();

                Uri uri = Uri.parse(urlWeb);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}