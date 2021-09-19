package com.example.threads;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private EditText edEnrada;
    private TextView tvSalida;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edEnrada = findViewById(R.id.edEntrada);
        tvSalida = findViewById(R.id.tvSalida);
    }

    //Utilizando hilos
    public void calcularOperacion (View v)
    {
        int n = Integer.parseInt(edEnrada.getText().toString());
        tvSalida.append(n + "! = ");

        MiTarea tarea = new MiTarea();
        tarea.execute(n);
    }

    private class MiTarea extends AsyncTask<Integer, Integer, Integer>
    {
        private ProgressDialog progreso;

        protected void onPreExecute()
        {
            progreso = new ProgressDialog(MainActivity.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Calculando ...");
            progreso.setCancelable(true);
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                public void onCancel(DialogInterface dialog)
                {
                    MiTarea.this.cancel(true);
                }
            });
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();
        }

        protected Integer doInBackground(Integer... n)
        {
            int res = 1;
            for (int i = 1; i <= n[0] && !isCancelled(); i++)
            {
                res *= i;
                SystemClock.sleep(1000);
                publishProgress(i*100 / n[0]);
            }
            return res;
        }

        protected void onProgressUpdate(Integer... porc)
        {
            progreso.setProgress(porc[0]);
        }

        protected void onPostExecute(Integer result)
        {
            progreso.dismiss();
            tvSalida.append(result + "\n");
        }

        protected void onCancelled()
        {
            tvSalida.append("Cancelado\n");
        }
    }
}