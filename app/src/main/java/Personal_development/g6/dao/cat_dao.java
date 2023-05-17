package Personal_development.g6.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Personal_development.g6.DBHelper;
import Personal_development.g6.model.cat_model;

public class cat_dao {
    // Table category_drink
    private static final String category_table = "category_table";
    private static final String id_cat = "id_cat";
    private static final String name_cat = "name_cat";
    private static final String image_cat = "image_cat";
    private DBHelper dbHelper;


    public cat_dao(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<cat_model> getList(String... selectArgs) {
        ArrayList<cat_model> list = new ArrayList<>();
        String queryString = "SELECT * FROM " + category_table + "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            cat_model cat = new cat_model();
            cat.setId_cat(cursor.getInt(cursor.getColumnIndex(id_cat)));
            cat.setName_cat(cursor.getString(cursor.getColumnIndex(name_cat)));
            cat.setImage_cat(cursor.getBlob(cursor.getColumnIndex(image_cat)));
            list.add(cat);
        }
        return list;
    }

    //Kiểm tra trùng lặp danh mục
    public boolean isDuplicate(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + category_table + " WHERE " + name_cat + " = ?", new String[]{name});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return false;
        } else {
            return true;
        }
    }

    //Lấy id danh mục
    @SuppressLint("Range")
    public int getId(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + id_cat + " FROM " + category_table + " WHERE " + name_cat + " = ?", new String[]{name});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(id_cat));
        } else {
            return Integer.parseInt(null);
        }
    }

    //Tạo danh mục
    public boolean create(cat_model cat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(name_cat, cat.getName_cat());
            contentValues.put(image_cat, cat.getImage_cat());
            db.insert(category_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //Hàm tạo dữ liệu mẫu
    public boolean tempt(int id, String name, byte[] image) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(id_cat, id);
            contentValues.put(name_cat, name);
            contentValues.put(image_cat, image);
            db.insert(category_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //Cập nhập danh mục
    public boolean update(cat_model cat) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(name_cat, cat.getName_cat());
            contentValues.put(image_cat, cat.getImage_cat());
            db.update(category_table, contentValues, "" + id_cat + " = ?",
                    new String[]{String.valueOf(cat.getId_cat())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Xoá danh mục
    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(category_table, "" + id_cat + "=?", new String[]{String.valueOf(id)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Lấy tên danh mục
    public ArrayList<String> getName() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + category_table;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    //Lấy tên danh mục bằng id danh mục
    @SuppressLint("Range")
    public String getNamePosition(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + name_cat + " FROM " + category_table + " WHERE " + id_cat + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(name_cat));
        } else {
            return null;
        }
    }
}
