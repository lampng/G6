package Personal_development.g6.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Personal_development.g6.DBHelper;
import Personal_development.g6.model.role_model;

public class role_dao {
    // Table role
    private static final String role_table = "role_table";
    private static final String id_role = "id_role";
    private static final String name_role = "name_role";
    DBHelper dbHelper;
    Context context;
    private SQLiteDatabase db;

    public role_dao(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Lấy danh sách phân quyền
    @SuppressLint("Range")
    public ArrayList<role_model> getList(String... selectArgs) {
        ArrayList<role_model> list = new ArrayList<>();
        String queryString = "SELECT * FROM " + role_table + "";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        while (cursor.moveToNext()) {
            role_model role = new role_model();
            role.setId_role(cursor.getInt(cursor.getColumnIndex(id_role)));
            role.setName_role(cursor.getString(cursor.getColumnIndex(name_role)));
            list.add(role);
        }
        return list;
    }

    //Xem chi tiết quyền
    @SuppressLint("Range")
    public role_model getDetail(String... selectArgs) {
        String queryString = "SELECT * FROM " + role_table + " where " + id_role + " = ?";
        Cursor cursor = db.rawQuery(queryString, selectArgs);
        cursor.moveToFirst();
        role_model role = new role_model();
        role.setId_role(cursor.getInt(cursor.getColumnIndex(id_role)));
        role.setName_role(cursor.getString(cursor.getColumnIndex(name_role)));
        return role;
    }

    //Lấy tên quyền
    @SuppressLint("Range")
    public String getNameRole(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + name_role + " FROM " + role_table + " WHERE " + id_role + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(name_role));
        } else {
            return "";
        }
    }

    //Lấy id quyền
    @SuppressLint("Range")
    public int getIdRole(String role_selected) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + id_role + " FROM " + role_table + " WHERE " + name_role + " = ?", new String[]{String.valueOf(role_selected)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(id_role));
        } else {
            return Integer.parseInt(null);
        }
    }

}
