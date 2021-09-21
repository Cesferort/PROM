package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements Dialog_Atras.OnDialogoConfirmacionListener
{
    private NotificationManager notificationManager;
    static final String CANAL_ID = "canal_inicioSesion";
    static final int NOTIFICACION_ID = 1;

    private final String NOM_USER = "usuario1";
    private final String PASS_USER = "123456";
    private EditText txtNomUsuario;
    private EditText txtPassword;

    private Dialog_Inicio dialog_inicio;
    private Dialog_Atras dialog_atras;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNomUsuario = (EditText)findViewById(R.id.txtNomUsuario);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
    }

    public void listener_butIniciar(View view)
    {
        boolean checkDatos = comprobarDatos();

        if(checkDatos)
        {
            //Inicio de sesión correcto. Mostramos notificación y creamos intent
            crearNotificacion();

            Intent intent = new Intent(MainActivity.this, WhatsAppActivity.class);
            intent.putExtra("nomUser",txtNomUsuario.getText().toString());
            startActivityForResult(intent,1);
        }
        else
        {
            //Inicio de sesión no válido. Mostramos advertencia y reseteamos campos
            if(dialog_inicio == null)
                dialog_inicio = new Dialog_Inicio();
            dialog_inicio.show(getSupportFragmentManager(), "dialog_inicio");

            txtNomUsuario.setText("");
            txtPassword.setText("");
        }
    }

    private boolean comprobarDatos()
    {
        if( txtNomUsuario.getText().toString().equals(NOM_USER)
        &&  txtPassword.getText().toString().equals(PASS_USER))
            return true;
        return false;
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
                            CANAL_ID, "Inicio de Sesión",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );
            notificationChannel.setDescription("Canal utilizado para notificar al usuario de un inicio de sesión correcto.");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificacion =
                new NotificationCompat.Builder(MainActivity.this,CANAL_ID)
                        .setSmallIcon(R.drawable.icono)
                        .setContentTitle(getText(R.string.MainActivity_notificacion_title))
                        .setContentText(getText(R.string.MainActivity_notificacion_text));

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

    //Flecha para volver atrás presionada
    public void onBackPressed()
    {
        dialog_atras = new Dialog_Atras();
        dialog_atras.show(getSupportFragmentManager(), "dialog_atras");
    }

    //Botón positivo pulsado
    public void onPossitiveButtonClick()
    {
        finish();
        System.exit(0);
    }

    //Botón negativo pulsado
    //public void onNegativeButtonClick() {}

    //App minimazada
    /*
    protected void onUserLeaveHint()
    {
        System.out.println("APP MINIMIZADA");
        super.onUserLeaveHint();
    }
    */
}