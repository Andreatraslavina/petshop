package com.example.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiendavirtual.DB.DBHelper;
import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.Services.ProductService;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DescripcionActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.strHome);

        ImageView ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvInformation = (TextView) findViewById(R.id.tvInformation);
        TextView tvProductPrice =(TextView) findViewById(R.id.tvProductPrice);
        Button btBuy = (Button) findViewById(R.id.btBuy);
        dbHelper = new DBHelper(this);
        productService = new ProductService();

        Bundle resultIntent = getIntent().getExtras();
        String productId = resultIntent.getString("id");
        ArrayList<Producto> list = productService.cursorToArray(dbHelper.getDataById(productId));
        Producto producto = list.get(0);


        ivProductImage.setImageBitmap(productService.byteToBitmap(producto.getImage()));
        tvName.setText(producto.getName());
        tvInformation.setText(producto.getInformation());
        tvProductPrice.setText("$"+producto.getPrice());


        btBuy.setOnClickListener(buyListener);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private View.OnClickListener buyListener = view -> {
        Toast toast = Toast.makeText(DescripcionActivity.this,"Gracias por tu compra y que vuelva pronto", Toast.LENGTH_LONG);
        toast.show();
    };

}