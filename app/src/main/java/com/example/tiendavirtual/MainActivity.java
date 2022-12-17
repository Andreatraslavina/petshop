package com.example.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

       Button btEntry = (Button) findViewById(R.id.btEntry);
       btEntry.setOnClickListener(btnEntryListener);

    }

    private View.OnClickListener btnEntryListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent (MainActivity.this, Menu.class);
            startActivity(intent);
        }
    };

}