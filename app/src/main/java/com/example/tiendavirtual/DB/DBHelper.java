package com.example.tiendavirtual.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context){
        super (context, "Andre.db", null, 1);
        sqLiteDatabase = this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTS("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "description VARCHAR," +
                "price VARCHAR," +
                "image BLOB," +
                "information VARCHAR" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTS");


    }

    //METODO CRUD

    public void insertData (String name, String description, String price, String image, String information){
        String sql = "INSERT INTO PRODUCTS VALUES(null, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, name);
        statement.bindString(2, description);
        statement.bindString(3, price);
        statement.bindString(4, image);
        statement.bindString(5, information);
        statement.executeInsert();

    }

    public Cursor getData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTS", null);
        return cursor;
    }

}
