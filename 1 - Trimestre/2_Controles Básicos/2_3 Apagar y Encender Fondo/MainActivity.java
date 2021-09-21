package com.example.controlesbasicos_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
{
    private Button butEncender;
    private Button butApagar;
    private LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butEncender = (Button) findViewById(R.id.butEncender);
        butApagar = (Button) findViewById(R.id.butApagar);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        butEncender.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                linearLayout.setBackgroundColor(Color.YELLOW);
            }
        });
        butApagar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                linearLayout.setBackgroundColor(Color.BLACK);
            }
        });
    }
}