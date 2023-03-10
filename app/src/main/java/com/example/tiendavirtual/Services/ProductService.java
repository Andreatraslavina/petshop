package com.example.tiendavirtual.Services;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProductService {
    public ArrayList<Producto> cursorToArray(Cursor cursor) {
        ArrayList<Producto> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;

        } else {
            while (cursor.moveToNext()) {
                Producto producto = new Producto(
                        cursor.getInt(0),
                        cursor.getBlob(4),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(5)
                );
                list.add(producto);
            }
        }
        return list;
    }
        public byte[] imageViewToByte (ImageView imageView){
            Bitmap bitmap= ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[]byteArray = stream.toByteArray();
                return byteArray;

        }
        public Bitmap byteToBitmap(byte[]image){
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            return bitmap;
        }


}
