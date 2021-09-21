package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;

    private TextView txtN1;
    private TextView txtN2;
    private TextView txtResultado;
    private TextView txtCorrectas;
    private TextView txtIncorrectas;

    private int n1;
    private int n2;
    private int respuestasCorrectas;
    private int respuestasIncorrectas;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtN1 = (TextView) findViewById(R.id.txtN1);
        txtN2 = (TextView) findViewById(R.id.txtN2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtCorrectas = (TextView) findViewById(R.id.txtCorrectas);
        txtIncorrectas = (TextView) findViewById(R.id.txtIncorrectas);

        n1 = 0;
        n2 = 0;
        respuestasCorrectas = 0;
        respuestasIncorrectas = 0;
        crearNumsRandom();
    }

    private void crearNumsRandom()
    {
        n1 = (int)(Math.random() * 100);
        txtN1.setEnabled(true);
        txtN1.setText(String.valueOf(n1));
        txtN1.setEnabled(false);

        n2 = (int)(Math.random() * 100);
        txtN2.setEnabled(true);
        txtN2.setText(String.valueOf(n2));
        txtN2.setEnabled(false);
    }

    public void ButComprobarListener(View view)
    {
        try
        {
            int resultAComprobar = Integer.parseInt(txtResultado.getText().toString());
            Intent intent = new Intent(MainActivity.this, Resultado.class);
            intent.putExtra("n1",n1);
            intent.putExtra("n2",n2);
            intent.putExtra("resultAComprobar",resultAComprobar);
            startActivityForResult(intent,2);
        }
        catch(NumberFormatException e)
        {
            //VALOR INTRODUCIDO NO NUMÉRICO
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode==RESULT_OK )
        {
            //Limpiar y refrescar randoms
            crearNumsRandom();
            txtResultado.setText("");

            boolean check = data.getExtras().getBoolean("check");
            if(check == true)
            {
                respuestasCorrectas++;
                if(respuestasCorrectas == 10)
                    crearNotificacion();
            }
            else
                respuestasIncorrectas++;

            txtCorrectas.setText("PREGUNTAS CORRECTAS: " + respuestasCorrectas);
            txtIncorrectas.setText("INCORRECTAS: " + respuestasIncorrectas);
        }
    }

    private void crearNotificacion()
    {
        //Creamos notificacion
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Creamos el canal. SOLO puede hacerse en dispositivos con ver. 8 o más.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel
                    (
                        CANAL_ID, "Canal Ejercicio Notificaciones",
                        NotificationManager.IMPORTANCE_DEFAULT
                    );
            notificationChannel.setDescription("Descripción del Canal Ejercicio Notificaciones");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificacion =
                new NotificationCompat.Builder(MainActivity.this,CANAL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("¡ENHORABUENA!")
                        .setContentText("Has resuelto 10 operaciones con éxito ;D");

        //Código adicional con el que podríamos llamar a una actividad al ser pulsada la
        //notificación. Al no tener mas actividades, he puesto que reabra la única
        //actividad que hay.
        /*
        PendingIntent intencionPendiente =
                PendingIntent.getActivity(MainActivity.this, 0,
                        new Intent(MainActivity.this, MainActivity.class), 0);
        notificacion.setContentIntent(intencionPendiente);
        */

        notificationManager.notify(NOTIFICACION_ID, notificacion.build());
    }

    public void onDestroy()
    {
        super.onDestroy();
        notificationManager.cancel(NOTIFICACION_ID);
    }
}