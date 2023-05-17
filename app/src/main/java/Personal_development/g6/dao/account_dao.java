package Personal_development.g6.dao;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.duan1_lampngpk02586.DBHelper;
import poly.edu.duan1_lampngpk02586.model.account_model;

public class account_dao {
    private static final String role_table = "role_table";
    private static final String id_role = "id_role";
    private static final String name_role = "name_role";
    // Table role
    // Table account
    private static final String account_table = "account_table";
    private static final String id_ac = "id_ac";
    private static final String role_ID = "role_ID";
    private static final String first_name = "first_name";
    private static final String middle_name = "middle_name";
    private static final String last_name = "last_name";
    private static final String phone_ac = "phone_ac";
    private static final String password_ac = "password_ac";
    private static final String address_ac = "address_ac";
    private static final String avatar_ac = "avatar_ac";
    SharedPreferences sharedPreferences;
    private DBHelper dbHelper;

    public account_dao(Context context) {
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("login_info", MODE_PRIVATE);
    }

    public ArrayList<account_model> getList() {
        ArrayList<account_model> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                "ac.id_ac," +
                "ac.role_ID," +
                "ac.first_name," +
                "ac.middle_name," +
                "ac.last_name," +
                "ac.phone_ac," +
                "ac.password_ac," +
                "ac.address_ac," +
                "r.name_role" +
                " FROM account_table ac, role_table r WHERE ac.role_ID = r.id_role ORDER BY ac.id_ac DESC", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new account_model(
                        cursor.getString(0),//id_ac
                        cursor.getInt(1),//role_ID
                        cursor.getString(2),//first_name
                        cursor.getString(3),//middle_name
                        cursor.getString(4),//last_name
                        cursor.getString(5),//phone_ac
                        cursor.getString(6),//password_ac
                        cursor.getString(7),//address_ac
                        cursor.getString(8)));//name_role
            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<account_model> getListUser() {
        ArrayList<account_model> list = new ArrayList<account_model>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM account_table", null);
        while (cursor.moveToNext()) {
            account_model account = new account_model();
            account.setId_ac(cursor.getString(cursor.getColumnIndex(id_ac)));
            account.setRole_ID(cursor.getInt(cursor.getColumnIndex(role_ID)));
            account.setFirst_name(cursor.getString(cursor.getColumnIndex(first_name)));
            account.setMiddle_name(cursor.getString(cursor.getColumnIndex(middle_name)));
            account.setLast_name(cursor.getString(cursor.getColumnIndex(last_name)));
            account.setPhone_ac(cursor.getString(cursor.getColumnIndex(phone_ac)));
            account.setAddress_ac(cursor.getString(cursor.getColumnIndex(address_ac)));
            account.setAvatar_ac(cursor.getBlob(cursor.getColumnIndex(avatar_ac)));
            list.add(account);
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<account_model> getUser(String username) {
        ArrayList<account_model> list = new ArrayList<account_model>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM account_table WHERE " + id_ac + " = ?", new String[]{username});
        while (cursor.moveToNext()) {
            account_model account = new account_model();
            account.setRole_ID(cursor.getInt(cursor.getColumnIndex(role_ID)));
            account.setFirst_name(cursor.getString(cursor.getColumnIndex(first_name)));
            account.setMiddle_name(cursor.getString(cursor.getColumnIndex(middle_name)));
            account.setLast_name(cursor.getString(cursor.getColumnIndex(last_name)));
            account.setPhone_ac(cursor.getString(cursor.getColumnIndex(phone_ac)));
            account.setAddress_ac(cursor.getString(cursor.getColumnIndex(address_ac)));
            account.setAvatar_ac(cursor.getBlob(cursor.getColumnIndex(avatar_ac)));
            list.add(account);
        }
        return list;
    }

    public boolean add(account_model account_model) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_ac, account_model.getId_ac());
        contentValues.put(role_ID, account_model.getRole_ID());
        contentValues.put(first_name, account_model.getFirst_name());
        contentValues.put(middle_name, account_model.getMiddle_name());
        contentValues.put(last_name, account_model.getLast_name());
        contentValues.put(phone_ac, account_model.getPhone_ac());
        contentValues.put(password_ac, account_model.getPassword_ac());
        contentValues.put(address_ac, account_model.getAddress_ac());
        contentValues.put(avatar_ac, account_model.getAvatar_ac());

        long check = db.insert(account_table, null, contentValues);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean login(String user, String pass) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + account_table + " WHERE " + id_ac + " = ? AND " + password_ac + " = ?", new String[]{user, pass});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            //Lưu thông tin người dùng
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", cursor.getString(0));
            editor.putString("role", cursor.getString(1));
            editor.putString("firstName", cursor.getString(2));
            editor.putString("LastName", cursor.getString(4));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public void reset_password(String user, String pass) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(password_ac, pass);
        db.update(account_table, contentValues, id_ac + " = ?", new String[]{user});
    }

    @SuppressLint("Range")
    public int getIdRole(String role_selected) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + role_ID + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(role_selected)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(role_ID));
        } else {
            return Integer.parseInt(null);
        }
    }

    @SuppressLint("Range")
    public String getFirstname(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + first_name + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(first_name));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public String getMiddle(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + middle_name + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(middle_name));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public String getLastname(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + last_name + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(last_name));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public String getPhone(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + phone_ac + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(phone_ac));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public String getAddress(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + address_ac + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(address_ac));
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public byte[] getAvatar(String fn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + avatar_ac + " FROM " + account_table + " WHERE " + id_ac + " = ?", new String[]{String.valueOf(fn)});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getBlob(cursor.getColumnIndex(avatar_ac));
        } else {
            return null;
        }
    }

    public void update_role(String user, int role) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(role_ID, role);
        db.update(account_table, contentValues, id_ac + " = ?", new String[]{user});
    }

    public void update_profile_HaveAvatar(String fn, String mn, String ln, String phone, String address, byte[] avatar, String user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(first_name, fn);
        contentValues.put(middle_name, mn);
        contentValues.put(last_name, ln);
        contentValues.put(phone_ac, phone);
        contentValues.put(address_ac, address);
        contentValues.put(avatar_ac, avatar);
        db.update(account_table, contentValues, id_ac + " = ?", new String[]{user});
    }

    public void update_profile_NoAvatar(String fn, String mn, String ln, String phone, String address, String user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(first_name, fn);
        contentValues.put(middle_name, mn);
        contentValues.put(last_name, ln);
        contentValues.put(phone_ac, phone);
        contentValues.put(address_ac, address);
        db.update(account_table, contentValues, id_ac + " = ?", new String[]{user});
    }

    public boolean check_User_Phone(String user, String phone) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + account_table + " WHERE " + id_ac + " = ? AND " + phone_ac + " = ?", new String[]{user, phone});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ac_id_forgot", cursor.getString(0));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicate(String username, String phone) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + account_table + " WHERE " + id_ac + " = ? AND " + phone_ac + " = ?", new String[]{username, phone});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return false;
        } else {
            return true;
        }
    }
}
