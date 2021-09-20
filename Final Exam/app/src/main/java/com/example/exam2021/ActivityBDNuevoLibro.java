package com.example.exam2021;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityBDNuevoLibro extends AppCompatActivity
{
    private EditText txtTituloLibro;
    private EditText txtAutorLibro;
    private EditText txtIsbnLibro;
    private EditText txtEditorialLibro;
    private EditText txtNumPagLibro;
    private CheckBox checkLeido;
    private BibliotecaSQLiteHelper bsdbh;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdnuevo_libro);

        txtTituloLibro = (EditText)findViewById(R.id.txtTituloLibro);
        txtAutorLibro = (EditText)findViewById(R.id.txtAutorLibro);
        txtIsbnLibro = (EditText)findViewById(R.id.txtIsbnLibro);
        txtEditorialLibro = (EditText)findViewById(R.id.txtEditorialLibro);
        txtNumPagLibro = (EditText)findViewById(R.id.txtNumPagLibro);
        checkLeido = (CheckBox)findViewById(R.id.checkLeido);

        bsdbh = new BibliotecaSQLiteHelper(this, "Biblioteca", null, 3);
    }

    public void listener_butInsertarLibro(View view)
    {
        SQLiteDatabase db = bsdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if (db != null)
        {
            try
            {
                //Recuperar datos a insertar
                String titulo = txtTituloLibro.getText().toString();
                if(titulo.equals(""))
                    throw new Exception("El libro debe tener un título.");
                String autor = txtAutorLibro.getText().toString();
                if(autor.equals(""))
                    throw new Exception("El libro debe tener un autor.");
                String editorial = txtEditorialLibro.getText().toString();
                if(editorial.equals(""))
                    throw new Exception("El libro debe tener una editorial.");

                long isbn = Long.parseLong(txtIsbnLibro.getText().toString());
                int numPag = Integer.parseInt(txtNumPagLibro.getText().toString());
                boolean leido = checkLeido.isChecked();

                //Insertar
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("titulo", titulo);
                nuevoRegistro.put("autor", autor);
                nuevoRegistro.put("editorial", editorial);
                nuevoRegistro.put("isbn", isbn);
                nuevoRegistro.put("numPag", numPag);
                nuevoRegistro.put("leido", leido);

                db.insert("Libro", null, nuevoRegistro);
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(this,"Los valores introducidos como ISBN y número de páginas deben ser numéricos.",Toast.LENGTH_LONG ).show();
            }
            catch(Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG ).show();
            }
        }
        db.close();
        limpiarCampos();
    }

    public void listener_butCancelarLibro(View view)
    {
        limpiarCampos();
    }

    public void listener_butSalir(View view)
    {
        finish();
    }

    private void limpiarCampos()
    {
        txtTituloLibro.setText("");
        txtAutorLibro.setText("");
        txtIsbnLibro.setText("");
        txtEditorialLibro.setText("");
        txtNumPagLibro.setText("");
        checkLeido.setChecked(false);
    }
}