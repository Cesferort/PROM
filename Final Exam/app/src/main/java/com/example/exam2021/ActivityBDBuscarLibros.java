package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityBDBuscarLibros extends AppCompatActivity
{
    private RadioGroup radioGroupBuscar;
    private TextView tvOpcionElegida_bdbuscar_libros;
    private EditText txtBusquedaLibro;
    private BibliotecaSQLiteHelper bsdbh;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdbuscar_libros);

        radioGroupBuscar = (RadioGroup)findViewById(R.id.radioGroupBuscar);
        tvOpcionElegida_bdbuscar_libros = (TextView)findViewById(R.id.tvOpcionElegida_bdbuscar_libros);
        txtBusquedaLibro = (EditText)findViewById(R.id.txtBusquedaLibro);

        tvOpcionElegida_bdbuscar_libros.setVisibility(View.INVISIBLE);
        txtBusquedaLibro.setVisibility(View.INVISIBLE);

        bsdbh = new BibliotecaSQLiteHelper(this, "Biblioteca", null, 3);
    }

    public void listener_butBuscar(View view)
    {
        RadioButton radButSeleccionado = findViewById(radioGroupBuscar.getCheckedRadioButtonId());
        SQLiteDatabase db = bsdbh.getWritableDatabase();
        try
        {
            if(radButSeleccionado == null)
                throw new Exception("No se ha seleccionado ninguna opción.");

            String texto = radButSeleccionado.getText().toString().trim();
            String opcion1 = getResources().getString(R.string.activity_bdbuscar_libros_radButBuscar1);
            String opcion2 = getResources().getString(R.string.activity_bdbuscar_libros_radButBuscar2);
            String opcion3 = getResources().getString(R.string.activity_bdbuscar_libros_radButBuscar3);

            String buscar = txtBusquedaLibro.getText().toString();
            if(texto.equals(opcion1))       //Buscar por título
            {
                Cursor c = db.rawQuery("SELECT titulo, autor, editorial, isbn, numPag, leido FROM Libro WHERE titulo = '" + buscar + "'", null);

                //Nos aseguramos de que existe el primer registro
                if (c.moveToFirst())
                {
                    Libro libro = null;
                    do
                    {
                        String titulo = c.getString(0);
                        String autor = c.getString(1);
                        String editorial = c.getString(2);
                        long isbn = c.getLong(3);
                        int numPag = c.getInt(4);
                        int intLeido = c.getInt(5);
                        boolean leido = true;
                        if(intLeido == 0)
                            leido = false;

                        libro = new Libro(titulo, autor, editorial, isbn, numPag, leido);
                    }
                    while(c.moveToNext());

                    //Comprobamos si se ha encontrado un libro
                    if(libro != null)
                    {
                        String titulo = libro.toString();
                        String autor = libro.getAutor();
                        String editorial = libro.getEditorial();
                        long isbn = libro.getIsbn();
                        boolean leido = libro.getLeido();
                        int numPag = libro.getNumPag();

                        Intent intent = new Intent(ActivityBDBuscarLibros.this, ActivityBDListarLibros_Detalles.class);
                        intent.putExtra("titulo",titulo);
                        intent.putExtra("autor",autor);
                        intent.putExtra("editorial",editorial);
                        intent.putExtra("isbn",isbn);
                        intent.putExtra("leido",leido);
                        intent.putExtra("numPag",numPag);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this,"No se ha encontrado ningún libro con ese nombre.",Toast.LENGTH_LONG ).show();
                }
                c.close();
            }
            else if(texto.equals(opcion2))      //Buscar por autor
            {
                Cursor c = db.rawQuery("SELECT titulo, autor, editorial, isbn, numPag, leido FROM Libro WHERE autor = '" + buscar + "'", null);

                //Nos aseguramos de que existe el primer registro
                if (c.moveToFirst())
                {
                    ArrayList<Libro> listaLibros = new ArrayList<Libro>();
                    do
                    {
                        String titulo = c.getString(0);
                        String autor = c.getString(1);
                        String editorial = c.getString(2);
                        long isbn = c.getLong(3);
                        int numPag = c.getInt(4);
                        int intLeido = c.getInt(5);
                        boolean leido = true;
                        if (intLeido == 0)
                            leido = false;

                        Libro libro = new Libro(titulo, autor, editorial, isbn, numPag, leido);
                        listaLibros.add(libro);
                    }
                    while (c.moveToNext());

                    Intent intent = new Intent(ActivityBDBuscarLibros.this, ActivityBDBuscarLibros_Lista.class);
                    intent.putExtra("listaLibros",listaLibros);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this,"No se han encontrado libros escritos por ese autor.",Toast.LENGTH_LONG ).show();
                c.close();
            }
            else if(texto.equals(opcion3))      //Buscar por editorial
            {
                Cursor c = db.rawQuery("SELECT titulo, autor, editorial, isbn, numPag, leido FROM Libro WHERE editorial = '" + buscar + "'", null);

                //Nos aseguramos de que existe el primer registro
                if (c.moveToFirst())
                {
                    ArrayList<Libro> listaLibros = new ArrayList<Libro>();
                    do
                    {
                        String titulo = c.getString(0);
                        String autor = c.getString(1);
                        String editorial = c.getString(2);
                        long isbn = c.getLong(3);
                        int numPag = c.getInt(4);
                        int intLeido = c.getInt(5);
                        boolean leido = true;
                        if (intLeido == 0)
                            leido = false;

                        Libro libro = new Libro(titulo, autor, editorial, isbn, numPag, leido);
                        listaLibros.add(libro);
                    }
                    while (c.moveToNext());

                    Intent intent = new Intent(ActivityBDBuscarLibros.this, ActivityBDBuscarLibros_Lista.class);
                    intent.putExtra("listaLibros",listaLibros);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this,"No se han encontrado libros escritos por ese autor.",Toast.LENGTH_LONG ).show();
                c.close();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG ).show();
        }
        db.close();
    }

    public void listener_radButBuscar(View view)
    {
        tvOpcionElegida_bdbuscar_libros.setVisibility(View.VISIBLE);
        txtBusquedaLibro.setVisibility(View.VISIBLE);

        RadioButton radButSeleccionado = findViewById(radioGroupBuscar.getCheckedRadioButtonId());
        tvOpcionElegida_bdbuscar_libros.setText(radButSeleccionado.getText().toString());
    }

    public void listener_butVolver_bdbuscar_libros(View view)
    {
        finish();
    }
}