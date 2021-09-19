package com.example.ficheros;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    private final String ARCHIVO_INTERNO = "archivoInterno.txt";
    private final String ARCHIVO_EXTERNO = "archivoExterno.txt";
    private final int SOLICITUD_PERMISO_ESCRIBIR_SD = 0;
    private final int SOLICITUD_PERMISO_LEER_SD = 1;

    private EditText txtInput;
    private TextView txtOutput;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = (EditText)findViewById(R.id.txtInput);
        txtOutput = (TextView)findViewById(R.id.txtOutput);
    }

    /*
        -1      - SD Inaccesible
        0       - Solo lectura permitida
        1       - Lectura y escritura permitidas
     */
    private int comprobarSD()
    {
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED))
            return 1;
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            return 0;
        return -1;
    }

    private void leerSD()
    {
        try
        {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File ruta_sd = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            // File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File (ruta_sd.getAbsolutePath(), ARCHIVO_EXTERNO);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String txtResult = "";
            String linea = br.readLine();
            while(linea != null)
            {
                txtResult += linea;
                linea = br.readLine();
            }
            br.close();

            txtOutput.setText(txtResult);
        }
        catch (Exception e)
        {
            txtOutput.setText("");
            Log.e ("MainActivity", "Error al leer fichero en tarjeta SD");
        }
    }

    private void escribirSD()
    {
        try
        {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File ruta_sd = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            //File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File (ruta_sd.getAbsolutePath(), ARCHIVO_EXTERNO);
            FileOutputStream fos = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            osw.write(txtInput.getText().toString());
            osw.close();
        }
        catch (Exception e)
        {
            Log.e ("MainActivity", "Error al escribir fichero en tarjeta SD");
        }
    }

    public void listener_butAniadirInterno(View view)
    {
        try
        {
            FileOutputStream fos = openFileOutput(ARCHIVO_INTERNO, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            osw.write(txtInput.getText().toString());
            osw.close();
        }
        catch (Exception e)
        {
            Log.e ("MainActivity", "Error al escribir fichero en memoria interna");
        }
    }

    public void listener_butAniadirExterno(View view)
    {
        if(comprobarSD() == 1 || comprobarSD() == 0)
        {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                escribirSD();
            else
                solicitarPermiso
                        (
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                "Se necesita acceder a la SD para escribir archivos en ella.",
                                SOLICITUD_PERMISO_ESCRIBIR_SD,
                                this
                        );
        }
        else
        {
            Log.e ("MainActivity", "Error al escribir fichero en memoria externa");
        }
    }

    public void listener_butLeerInterno(View view)
    {
        try
        {
            FileInputStream fis = openFileInput(ARCHIVO_INTERNO);
            if(fis == null)
                System.out.println("s");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String txtResult = "";
            String linea = br.readLine();
            while(linea != null)
            {
                txtResult += linea;
                linea = br.readLine();
            }
            br.close();

            txtOutput.setText(txtResult);
        }
        catch (Exception e)
        {
            txtOutput.setText("");
            Log.e("MainActivity", "Error al leer fichero desde memoria interna");
        }
    }

    public void listener_butLeerExterno(View view)
    {
        if(comprobarSD() == 1 || comprobarSD() == 0)
        {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                leerSD();
            else
                solicitarPermiso
                        (
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                "Se necesita acceder a la SD para leer archivos en ella.",
                                SOLICITUD_PERMISO_LEER_SD,
                                this
                        );
        }
        else
        {
            txtOutput.setText("");
            Log.e ("MainActivity", "Error al leer fichero en memoria externa");
        }
    }

    public void listener_butLeerRecurso(View view)
    {
        try
        {
            InputStream is = getResources().openRawResource(R.raw.datos);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String txtResult = "";
            String linea = br.readLine();
            while (linea != null)
            {
                txtResult += linea;
                linea = br.readLine();
            }
            br.close();

            txtOutput.setText(txtResult);
        }
        catch (Exception e)
        {
            txtOutput.setText("");
            Log.e ("MainActivity", "Error al leer recurso");
        }

    }

    public void listener_butBorrarInterno(View view)
    {
        deleteFile(ARCHIVO_INTERNO);
    }

    public void listener_butBorrarExterno(View view)
    {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File ruta_sd = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        // File ruta_sd = Environment.getExternalStorageDirectory();
        File f = new File (ruta_sd.getAbsolutePath(), ARCHIVO_EXTERNO);

        if(f.exists())
            f.delete();
    }

    private static void solicitarPermiso (final String permiso,
                                          String justificacion,
                                          final int requestCode,
                                          final Activity actividad){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                actividad, permiso))
        {
            //Informamos al usuario para qué y por qué
            //se necesitan los permisos
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog,
                                                    int which)
                                {
                                    ActivityCompat.requestPermissions(actividad,
                                            new String[]{permiso},requestCode);
                                }
                            }).show();
        }
        else
        {
            //Muestra el cuadro de dialogo para la solicitud de permisos y
            //registra el permiso según respuesta del usuario
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults)
    {
        if (requestCode == SOLICITUD_PERMISO_ESCRIBIR_SD)
        {
            if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                escribirSD();
            else
                Toast.makeText(this,"No se puede ESCRIBIR en memoria SD",Toast.LENGTH_LONG ).show();
        }
        else if (requestCode == SOLICITUD_PERMISO_LEER_SD)
        {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                leerSD();
            else
                Toast.makeText(this,"No se puede LEER de memoria SD",Toast.LENGTH_LONG ).show();
        }
    }

    public void listener_butEj2(View view)
    {
        Intent intent = new Intent(MainActivity.this, Ejercicio_2.class);
        startActivityForResult(intent,1);
    }

    //shouldShowRequestPermissionRationale(). Este método, es de gran utilidad,
    //muestra true si el usuario ha rechazado la solicitud anteriormente y muestra false si el usuario
    //rechazó un permiso y selecciono la opción de No volver a preguntar en el dialogo de solicitud de
    //permiso, o si una política de dispositivo lo prohíbe.
}