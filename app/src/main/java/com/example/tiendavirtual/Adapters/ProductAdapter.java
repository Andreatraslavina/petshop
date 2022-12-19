package com.example.tiendavirtual.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiendavirtual.DescripcionActivity;
import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.MainActivity;
import com.example.tiendavirtual.Menu;
import com.example.tiendavirtual.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto>  arrayProducts;


    public ProductAdapter(Context context, ArrayList<Producto> arrayProducts) {
        this.context = context;
        this.arrayProducts = arrayProducts;

    }


    @Override
    public int getCount() {
        return arrayProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.product_template, null);

        Producto producto = arrayProducts.get(position);


        Button btAdd = (Button) view.findViewById(R.id.btAdd);
        ImageView imgCat = (ImageView) view.findViewById(R.id.imgCat);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);

        //int resourceId = context.getResources().getIdentifier(producto.getImage(), "drawable", context.getPackageName());
        //imgCat.setImageResource(resourceId);

        byte[] image = producto.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        imgCat.setImageBitmap(bitmap);
        txtName.setText(producto.getName());
        txtDescription.setText(producto.getDescription());
        txtPrice.setText(String.valueOf(producto.getPrice()));

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DescripcionActivity.class);
                intent.putExtra("id", String.valueOf(producto.getId()));
                context.startActivity(intent);
            }
        });


        return view;
    }

}