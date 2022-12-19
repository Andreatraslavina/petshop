package com.example.tiendavirtual;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tiendavirtual.DB.DBHelper;
import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.Services.ProductService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProductForm extends AppCompatActivity {
    private ProductService productService;
    private DBHelper dbHelper;
    private Button btnFormProduct, btnGet, btnDelete, btnUpdate;
    private EditText editNameFormProduct, editDescriptionFormProduct, editPriceFormProduct, editInfoFormProduct, editIdFormProduct;
    private ImageView imgFormProduct;
    ActivityResultLauncher<String> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.strHome);


        btnGet = (Button) findViewById(R.id.btnGet);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnFormProduct = (Button) findViewById(R.id.btnFormProduct);
        editNameFormProduct = (EditText) findViewById(R.id.editNameFormProduct);
        editDescriptionFormProduct = (EditText) findViewById(R.id.editDescriptionFormProduct);
        editInfoFormProduct = (EditText) findViewById(R.id.editInfoFormProduct);
        editPriceFormProduct = (EditText) findViewById(R.id.editPriceFormProduct);
        editIdFormProduct = (EditText) findViewById(R.id.editIdFormProduct);
        imgFormProduct = (ImageView) findViewById(R.id.imgFormProduct);

        byte[] img = "".getBytes(StandardCharsets.UTF_8);
        try {
            productService = new ProductService();
            dbHelper = new DBHelper(this);
        } catch (Exception e) {
            Log.e("DB", e.toString());
        }

        content = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imgFormProduct.setImageURI(result);
                    }
                }

        );

        imgFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.launch("image/*");

            }
        });



        btnFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFormProduct.getDrawable();
                dbHelper.insertData(
                        editNameFormProduct.getText().toString(),
                        editDescriptionFormProduct.getText().toString(),
                        editPriceFormProduct.getText().toString(),
                        productService.imageViewToByte(imgFormProduct),
                        editInfoFormProduct.getText().toString()
                );

                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editIdFormProduct.getText().toString().trim();
                if (id.compareTo("") != 0) {
                    ArrayList<Producto> list = productService.cursorToArray(dbHelper.getDataById(id));
                    if (list.size() != 0) {
                        Producto producto = list.get(0);
                        imgFormProduct.setImageBitmap(productService.byteToBitmap(producto.getImage()));
                        editNameFormProduct.setText(producto.getName());
                        editDescriptionFormProduct.setText(producto.getDescription());
                        editInfoFormProduct.setText(producto.getInformation());
                        editPriceFormProduct.setText(String.valueOf(producto.getPrice()));


                    } else {
                        Toast.makeText(getApplicationContext(), "NO EXITE VERIFIQUE", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "INGRESE ID", Toast.LENGTH_SHORT).show();
                }
            }


        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editIdFormProduct.getText().toString().trim();
                if(id.compareTo("") != 0){
                    dbHelper.updateDataById(
                            id,
                            editNameFormProduct.getText().toString(),
                            editDescriptionFormProduct.getText().toString(),
                            editPriceFormProduct.getText().toString(),
                            productService.imageViewToByte(imgFormProduct),
                            editInfoFormProduct.getText().toString()

                    );
                    //clean();

                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editIdFormProduct.getText().toString().trim();
                if(id.compareTo("") != 0){
                    dbHelper.deleteDataById(id);
                    clean();
                }else{
                    Toast.makeText(getApplicationContext(), "INGRESE EL ID QUE DESEA ELIMINAR", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }


    }

        public  void clean(){
        editNameFormProduct.setText("");
        editDescriptionFormProduct.setText("");
        editPriceFormProduct.setText("");
        imgFormProduct.setImageResource(R.drawable.guardar_imagen1);
        editInfoFormProduct.setText("");


        }

}