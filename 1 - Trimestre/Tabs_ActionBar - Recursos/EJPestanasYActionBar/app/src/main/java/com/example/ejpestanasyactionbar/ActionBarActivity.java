package com.example.ejpestanasyactionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ejpestanasyactionbar.R;

public class ActionBarActivity extends AppCompatActivity {
    private TextView lblTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        lblTextView = findViewById(R.id.lblTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_opc1){
            lblTextView.append("\nBoton Opcion 1 pulsado");
            return true;
        }
        if (id == R.id.action_opc2){
            lblTextView.append("\nBoton opcion 2 pulsado");
            return true;
        }
        if (id == R.id.action_opc3){
            lblTextView.append("\nBoton opcion 3 pulsado");
            return true;
        }
        if (id == R.id.action_opc4){
            lblTextView.append("\nBoton opcion 4 pulsado");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}