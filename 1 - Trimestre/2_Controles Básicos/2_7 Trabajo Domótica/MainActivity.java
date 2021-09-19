package com.example.controlesbasicos_6;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Button butMenosTemperatura;
    private Button butMasTemperatura;
    private TextView txtTemperatura;
    private int temperatura;
    private boolean elecOn;

    private ImageView bombilla1;
    private ImageView bombilla2;
    private ImageView bombilla3;
    private ImageView bombilla4;
    private ImageView bombilla5;
    private ImageView bombilla6;
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;
    private Switch switch4;
    private Switch switch5;
    private Switch switch6;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butMenosTemperatura = (Button) findViewById(R.id.butMenosTemperatura);
        butMasTemperatura = (Button) findViewById(R.id.butMasTemperatura);
        txtTemperatura = (TextView) findViewById(R.id.txtTemperatura);

        bombilla1 = (ImageView) findViewById(R.id.bombilla1);
        bombilla2 = (ImageView) findViewById(R.id.bombilla2);
        bombilla3 = (ImageView) findViewById(R.id.bombilla3);
        bombilla4 = (ImageView) findViewById(R.id.bombilla4);
        bombilla5 = (ImageView) findViewById(R.id.bombilla5);
        bombilla6 = (ImageView) findViewById(R.id.bombilla6);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        switch4 = (Switch) findViewById(R.id.switch4);
        switch5 = (Switch) findViewById(R.id.switch5);
        switch6 = (Switch) findViewById(R.id.switch6);

        temperatura = Integer.parseInt(txtTemperatura.getText().toString());
        elecOn = true;
        eventos();
    }

    private void eventos()
    {
        butMenosTemperatura.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                temperatura = Integer.parseInt(txtTemperatura.getText().toString());
                if(temperatura > 6)
                {
                    temperatura--;
                    butMasTemperatura.setEnabled(true);
                    if(temperatura < 11)
                        txtTemperatura.setBackgroundColor(Color.BLUE);
                    else if(temperatura < 21)
                        txtTemperatura.setBackgroundColor(Color.BLACK);
                    else
                        txtTemperatura.setBackgroundColor(Color.RED);

                    txtTemperatura.setText(String.valueOf(temperatura));
                }
                else
                    butMenosTemperatura.setEnabled(false);
            }
        });

        butMasTemperatura.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                temperatura = Integer.parseInt(txtTemperatura.getText().toString());
                if (temperatura < 28)
                {
                    temperatura++;
                    butMenosTemperatura.setEnabled(true);
                    if(temperatura < 10)
                        txtTemperatura.setBackgroundColor(Color.BLUE);
                    else if(temperatura < 25)
                        txtTemperatura.setBackgroundColor(Color.BLACK);
                    else
                        txtTemperatura.setBackgroundColor(Color.RED);

                    txtTemperatura.setText(String.valueOf(temperatura));
                }
                else
                    butMasTemperatura.setEnabled(false);
            }
        });
    }

    public void switchListener(View view)
    {
        Switch switchSeleccionado = (Switch) findViewById(view.getId());
        ImageView bombillaSeleccionada;
        switch(view.getId())
        {
            case (R.id.switch1):
                bombillaSeleccionada = bombilla1;
                break;
            case (R.id.switch2):
                bombillaSeleccionada = bombilla2;
                break;
            case (R.id.switch3):
                bombillaSeleccionada = bombilla3;
                break;
            case (R.id.switch4):
                bombillaSeleccionada = bombilla4;
                break;
            case (R.id.switch5):
                bombillaSeleccionada = bombilla5;
                break;
            default:
                bombillaSeleccionada = bombilla6;
        }

        if(switchSeleccionado.isChecked())
            bombillaSeleccionada.setImageResource(R.drawable.bombilla_encendida);
        else
            bombillaSeleccionada.setImageResource(R.drawable.bombilla_apagada);
    }

    public void togElectricidadListener(View view)
    {
        if(elecOn == true)
        {
            elecOn = false;
            butMasTemperatura.setEnabled(false);
            butMenosTemperatura.setEnabled(false);
            bombilla1.setImageResource(R.drawable.bombilla_apagada);
            bombilla2.setImageResource(R.drawable.bombilla_apagada);
            bombilla3.setImageResource(R.drawable.bombilla_apagada);
            bombilla4.setImageResource(R.drawable.bombilla_apagada);
            bombilla5.setImageResource(R.drawable.bombilla_apagada);
            bombilla6.setImageResource(R.drawable.bombilla_apagada);
            switch1.setChecked(false);
            switch2.setChecked(false);
            switch3.setChecked(false);
            switch4.setChecked(false);
            switch5.setChecked(false);
            switch6.setChecked(false);
            switch1.setEnabled(false);
            switch2.setEnabled(false);
            switch3.setEnabled(false);
            switch4.setEnabled(false);
            switch5.setEnabled(false);
            switch6.setEnabled(false);
        }
        else
        {
            elecOn = true;
            switch1.setEnabled(true);
            switch2.setEnabled(true);
            switch3.setEnabled(true);
            switch4.setEnabled(true);
            switch5.setEnabled(true);
            switch6.setEnabled(true);

            if(temperatura > 6)
                butMenosTemperatura.setEnabled(true);
            if(temperatura < 28)
                butMasTemperatura.setEnabled(true);
        }
    }
}