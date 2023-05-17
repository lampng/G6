package Personal_development.g6.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.duan1_lampngpk02586.DBHelper;
import poly.edu.duan1_lampngpk02586.model.drinks_model;

public class drinks_dao {
    // Table drinks
    private static final String drinks_table = "drinks_table";
    private static final String id_drinks = "id_drinks";//0
    private static final String cat_ID = "cat_ID";//1
    private static final String name_drinks = "name_drinks";//2
    private static final String image_drinks = "image_drinks";//3
    private static final String des_drinks = "des_drinks";//4
    private static final String price_drinks = "price_drinks";//5

    private static final String sumQuantity = "sumQuantity";//5

    DBHelper dbHelper;

    public drinks_dao(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<drinks_model> getList(String... selectArgs) {
        ArrayList<drinks_model> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + drinks_table + " ORDER BY " + id_drinks + " DESC", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new drinks_model(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4),
                        cursor.getDouble(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<drinks_model> getTop10() {
        ArrayList<drinks_model> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT d.id_drinks, d.cat_ID, d.name_drinks, d.image_drinks,d.des_drinks, d.price_drinks FROM drinks_table d INNER JOIN cart_table c WHERE d.id_drinks = c.drinks_ID GROUP BY c.drinks_ID ORDER BY SUM(c.quantity) DESC LIMIT 10", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new drinks_model(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4),
                        cursor.getDouble(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<drinks_model> search(String text) {
        ArrayList<drinks_model> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM drinks_table d INNER JOIN category_table c ON d.cat_ID = c.id_cat WHERE d.name_drinks like '%" + text + "%' Or c.name_cat LIKE '%" + text + "%'", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new drinks_model(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getBlob(3),
                        cursor.getString(4),
                        cursor.getDouble(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //check duplicate category
    public boolean isDuplicate(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + drinks_table + " WHERE " + name_drinks + " = ?", new String[]{name});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("Range")
    public byte[] getImage(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + image_drinks + " FROM " + drinks_table + " WHERE " + id_drinks + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getBlob(cursor.getColumnIndex(image_drinks));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public String getName(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + name_drinks + " FROM " + drinks_table + " WHERE " + id_drinks + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(name_drinks));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public double getPrice(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + price_drinks + " FROM " + drinks_table + " WHERE " + id_drinks + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getDouble(cursor.getColumnIndex(price_drinks));
        } else {
            return Double.parseDouble(null);
        }
    }


    @SuppressLint("Range")
    public int getSumQuantity(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(c.quantity) FROM drinks_table d, cart_table c WHERE d.id_drinks = c.drinks_ID AND d.id_drinks = ? AND c.order_ID != 0 GROUP BY d.id_drinks ORDER BY SUM(c.quantity) DESC LIMIT 10", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return Integer.parseInt(null);
        }
    }

    public boolean create(drinks_model drinks) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(cat_ID, drinks.getCat_ID());
            contentValues.put(name_drinks, drinks.getName_drinks());
            contentValues.put(image_drinks, drinks.getImage_drinks());
            contentValues.put(des_drinks, drinks.getDes_drinks());
            contentValues.put(price_drinks, drinks.getPrice_drinks());
            db.insert(drinks_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean tempt(int id_drink, int cat_id, String name, byte[] image, String des, Double price) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(id_drinks, id_drink);
            contentValues.put(cat_ID, cat_id);
            contentValues.put(name_drinks, name);
            contentValues.put(image_drinks, image);
            contentValues.put(des_drinks, des);
            contentValues.put(price_drinks, price);
            db.insert(drinks_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(drinks_model drinks) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(cat_ID, drinks.getCat_ID());
            contentValues.put(name_drinks, drinks.getName_drinks());
            contentValues.put(image_drinks, drinks.getImage_drinks());
            contentValues.put(des_drinks, drinks.getDes_drinks());
            contentValues.put(price_drinks, drinks.getPrice_drinks());
            db.update(drinks_table, contentValues, "" + id_drinks + " = ?",
                    new String[]{String.valueOf(drinks.getId_drinks())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(drinks_table, "" + id_drinks + "=?", new String[]{String.valueOf(id)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
