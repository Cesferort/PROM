package com.example.threads;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class backupMainActivity extends AppCompatActivity
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
        MiThread thread = new MiThread(n);
        thread.start();
    }

    private int factorial (int num)
    {
        int resultado =1;
        for (int i=1; i<=num; i++)
        {
            resultado *= i;
            SystemClock.sleep(1000); //Esperamos un segundo
        }
        return resultado;
    }

    private class MiThread extends Thread
    {
        private int n, res;

        public MiThread(int n)
        {
            this.n = n;
        }

        public void run()
        {
            runOnUiThread(new Runnable()
            {
                public void run()
                {
                    int res = factorial(n);
                    tvSalida.append(res + "\n");
                }
            });
        }
    }
}