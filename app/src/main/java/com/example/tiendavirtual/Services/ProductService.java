package com.example.tiendavirtual.Services;

import android.database.Cursor;

import com.example.tiendavirtual.Entities.Producto;
import com.example.tiendavirtual.R;

import java.util.ArrayList;

public class ProductService {
    public ArrayList<Producto> cursorToArray(Cursor cursor) {
        ArrayList<Producto> list = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return list;

        } else {
            while (cursor.moveToNext()) {
                Producto producto = new Producto(
                        cursor.getString(4),
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



}
