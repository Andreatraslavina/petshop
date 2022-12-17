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

public class DescripcionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.strHome);

        ImageView ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvProductPrice =(TextView) findViewById(R.id.tvProductPrice);
        Button btBuy = (Button) findViewById(R.id.btBuy);



        Bundle resultIntent = getIntent().getExtras();
        String productName = resultIntent.getString("name");
        String productDescription = resultIntent.getString("information");
        int productImage = resultIntent.getInt("image");
        int productPrice = resultIntent.getInt("price");



        ivProductImage.setImageResource(productImage);
        tvName.setText(productName);
        tvDescription.setText(productDescription);
        tvProductPrice.setText("$"+productPrice);

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