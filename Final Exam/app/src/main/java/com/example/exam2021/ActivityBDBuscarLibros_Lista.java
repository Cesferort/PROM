package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ActivityBDBuscarLibros_Lista extends AppCompatActivity
{
    private ListView lvLibros;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdbuscar_libros_lista);

        lvLibros = (ListView)findViewById(R.id.lvLibros);
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Libro libro = (Libro) parent.getItemAtPosition(position);
                String titulo = libro.toString();
                String autor = libro.getAutor();
                String editorial = libro.getEditorial();
                long isbn = libro.getIsbn();
                boolean leido = libro.getLeido();
                int numPag = libro.getNumPag();

                Intent intent = new Intent(ActivityBDBuscarLibros_Lista.this, ActivityBDListarLibros_Detalles.class);
                intent.putExtra("titulo",titulo);
                intent.putExtra("autor",autor);
                intent.putExtra("editorial",editorial);
                intent.putExtra("isbn",isbn);
                intent.putExtra("leido",leido);
                intent.putExtra("numPag",numPag);
                startActivityForResult(intent,1);
            }
        });

        llenarLista();
    }

    private void llenarLista()
    {
        Bundle extras = getIntent().getExtras();
        ArrayList<Libro> listaLibros = (ArrayList<Libro>) extras.get("listaLibros");

        Libro datos[] = new Libro[listaLibros.size()];
        for(int contDatos = 0; contDatos < datos.length; contDatos++)
            datos[contDatos] = listaLibros.get(contDatos);

        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, datos);
        lvLibros.setAdapter(adaptadorTitulares);
    }

    private class AdaptadorTitulares extends ArrayAdapter<Libro>
    {
        private Libro[] datos;

        public AdaptadorTitulares(Context context, Libro[] datos)
        {
            super(context, R.layout.listitem_libro, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_libro, null);

            TextView tvNomLibro = (TextView)item.findViewById(R.id.tvNomLibro);
            tvNomLibro.setText(datos[position].toString());

            TextView tvAutor = (TextView)item.findViewById(R.id.tvAutor);
            tvAutor.setText(datos[position].getAutor());

            return item;
        }
    }

    public void listener_butVolver(View view)
    {
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK )
        {
            boolean check = data.getExtras().getBoolean("check");
            if(check == true)
                llenarLista();
        }
    }
}