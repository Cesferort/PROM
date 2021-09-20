package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityBDListarLibros_Detalles extends AppCompatActivity
{
    private TextView tvTituloLibro_bdlistar_libros_detalles;
    private TextView tvAutor_bdlistar_libros_detalles;
    private TextView tvIsbn_bdlistar_libros_detalles;
    private TextView tvEditorial_bdlistar_libros_detalles;
    private TextView tvNumPag_bdlistar_libros_detalles;
    private TextView tvLeido_bdlistar_libros_detalles;

    private long isbn;
    private BibliotecaSQLiteHelper bsdbh;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdlistar_libros_detalles);

        tvTituloLibro_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvTituloLibro_bdlistar_libros_detalles);
        tvAutor_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvAutor_bdlistar_libros_detalles);
        tvIsbn_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvIsbn_bdlistar_libros_detalles);
        tvEditorial_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvEditorial_bdlistar_libros_detalles);
        tvNumPag_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvNumPag_bdlistar_libros_detalles);
        tvLeido_bdlistar_libros_detalles = (TextView)findViewById(R.id.tvLeido_bdlistar_libros_detalles);

        bsdbh = new BibliotecaSQLiteHelper(this, "Biblioteca", null, 3);

        mostrarDatos();
    }

    private void mostrarDatos()
    {
        Bundle extras = getIntent().getExtras();
        String titulo = (String)extras.get("titulo");
        String autor = (String)extras.get("autor");
        String editorial = (String)extras.get("editorial");
        isbn = (long)extras.get("isbn");
        boolean leido = (boolean)extras.get("leido");
        int numPag = (int)extras.get("numPag");

        tvTituloLibro_bdlistar_libros_detalles.setText(titulo);
        tvAutor_bdlistar_libros_detalles.setText(autor);
        tvIsbn_bdlistar_libros_detalles.setText(String.valueOf(isbn));
        tvEditorial_bdlistar_libros_detalles.setText(editorial);
        tvNumPag_bdlistar_libros_detalles.setText(String.valueOf(numPag) + " " + getResources().getString(R.string.activity_bdlistar_libros_detalles_tvNumPag));

        if(leido == false)
            tvLeido_bdlistar_libros_detalles.setText(R.string.activity_bdlistar_libros_detalles_tvLeido_false);
        else
            tvLeido_bdlistar_libros_detalles.setText(R.string.activity_bdlistar_libros_detalles_tvLeido_true);
    }

    public void listener_butEliminar(View view)
    {
        SQLiteDatabase db = bsdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if (db != null)
            db.execSQL("DELETE FROM Libro WHERE isbn = '" + isbn + "'");
        db.close();

        Intent intent = new Intent();
        intent.putExtra("check", true);
        setResult(RESULT_OK, intent);

        finish();
    }

    public void listener_butCancelar(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("check", false);
        setResult(RESULT_OK, intent);

        finish();
    }
}