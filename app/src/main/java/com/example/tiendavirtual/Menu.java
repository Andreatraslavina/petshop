package com.example.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiendavirtual.Adapters.ProductAdapter;
import com.example.tiendavirtual.DB.DBHelper;
import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.Services.ProductService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;
    private ListView listviewProducts;
    private ArrayList<Producto> arrayProducts;
    private ProductAdapter productAdapter;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.strHome);


        try {
            dbHelper = new DBHelper(this);
            byte[] img = "".getBytes();
            /*dbHelper.insertData("Galletas para gato", "Delicioso snack", "15000", "snack_gato_3", getResources().getString(R.string.strInfo1));
            dbHelper.insertData("Galletas para perro", "Delicioso snack", "13000", "galletas_perro2", getResources().getString(R.string.strInfo2));
            dbHelper.insertData("Comida para gato", "Sabor a pollo", "43000", "comida_gato", getResources().getString(R.string.strInfo3));
            dbHelper.insertData("Comida para perro", "Sabor a carne", "50000",  "comida_para_perro_3",getResources().getString(R.string.strInfo4));
            dbHelper.insertData("Juguete para perro", "Diferentes colores", "12000", "pelota_perro", getResources().getString(R.string.strInfo5));
            dbHelper.insertData("Cortaunas mascotas", "Tamaño mediano", "30000", "cortaunas_gato", getResources().getString(R.string.strInfo6));*/
            productService = new ProductService();
            Cursor cursor =dbHelper.getData();
            arrayProducts = productService.cursorToArray(cursor);
            //Toast.makeText(this, "Insert OK", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Database", e.toString());
            //Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();
        }

        listviewProducts = (ListView) findViewById(R.id.listViewProducts);
        productAdapter = new ProductAdapter(this, arrayProducts);
        listviewProducts.setAdapter(productAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionAdd:
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
                startActivity(intent);
                return true;

            case R.id.itemMap:
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                return true;

            case android.R.id.home:
                this.finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}