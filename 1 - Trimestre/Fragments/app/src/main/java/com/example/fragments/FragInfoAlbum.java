package com.example.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Iterator;

public class FragInfoAlbum extends Fragment
{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_frag_info_album, container,false);
    }

    public void mostrarDetalle (ArrayList<Cancion> listaCanciones)
    {
        String texto = "";
        Iterator<Cancion> itListaCanciones = listaCanciones.iterator();
        while(itListaCanciones.hasNext())
        {
            Cancion cancionRecipiente = itListaCanciones.next();
            texto += cancionRecipiente.toString() + "\n";
        }

        TextView tvInfo =(TextView)getView().findViewById(R.id.tvInfo);
        tvInfo.setText(texto);
    }
}