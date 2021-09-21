package com.example.fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class FragListadoAlbum extends Fragment
{
    private ListView lvAlbum;
    private MainActivity listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_frag_listado_album, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Cancion>listaEP1 = new ArrayList<Cancion>();
        listaEP1.add(new Cancion("Escape From Midwitch Valley", 520));
        listaEP1.add(new Cancion("Disco Zombi Italia", 521));
        listaEP1.add(new Cancion("L.A Venice Bitch 80's", 522));
        listaEP1.add(new Cancion("Wake Up The President", 522));
        listaEP1.add(new Cancion("347 Midnight Demons", 522));
        listaEP1.add(new Cancion("Le Perv", 522));

        ArrayList<Cancion>listaEP2 = new ArrayList<Cancion>();
        listaEP2.add(new Cancion("Roller Mobster", 523));
        listaEP2.add(new Cancion("Meet Matt Stryker", 524));
        listaEP2.add(new Cancion("Obituary", 525));
        listaEP2.add(new Cancion("Looking for Tracy Tzu", 525));
        listaEP2.add(new Cancion("SexKiller on the Loose", 525));
        listaEP2.add(new Cancion("Hang'Em All", 525));

        ArrayList<Cancion>listaEP3 = new ArrayList<Cancion>();
        listaEP3.add(new Cancion("Division Ruine", 526));
        listaEP3.add(new Cancion("Paradise Warfare", 527));
        listaEP3.add(new Cancion("Run, Sally, Run!", 528));
        listaEP3.add(new Cancion("Turbo Killer ", 528));
        listaEP3.add(new Cancion("Anarchy Road", 528));
        listaEP3.add(new Cancion("Invasion A.D.", 528));

        Album datos[] =
                {
                    new Album("Carpenter Brut","EP I",1000,2012, listaEP1),
                    new Album("Carpenter Brut","EP II",1100,2013, listaEP2),
                    new Album("Carpenter Brut","EP III",1200,2015, listaEP3),
                };

        lvAlbum = (ListView) getView().findViewById(R.id.lvAlbum);
        AdaptadorAlbum adaptadorAlbum = new AdaptadorAlbum(getContext(), datos);
        lvAlbum.setAdapter(adaptadorAlbum);

        lvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (listener != null)
                    listener.onCorreoSeleccionado((Album)lvAlbum.getAdapter().getItem(position));
            }
        });
    }

    private class AdaptadorAlbum extends ArrayAdapter<Album>
    {
        private Album datos[];

        public AdaptadorAlbum(Context context, Album datos[])
        {
            super(context, R.layout.listitem_album, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_album, null);
            
            TextView tvAnioAlb = (TextView)item.findViewById(R.id.tvAnioAlb);
            tvAnioAlb.setText(String.valueOf(datos[position].getAnioAlb()));

            TextView tvNomAlb = (TextView)item.findViewById(R.id.tvNomAlb);
            tvNomAlb.setText(datos[position].getNombreAlb());

            TextView tvAutorAlb = (TextView)item.findViewById(R.id.tvAutorAlb);
            tvAutorAlb.setText(datos[position].getCompositor());

            TextView tvDurAlb = (TextView)item.findViewById(R.id.tvDurAlb);
            tvDurAlb.setText(String.valueOf(datos[position].getDuracion()));

            return item;
        }
    }

    public void setCorreoListener (MainActivity listener)
    {
        this.listener = listener;
    }
}