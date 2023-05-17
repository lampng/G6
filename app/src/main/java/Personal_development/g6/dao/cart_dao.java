package Personal_development.g6.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import poly.edu.duan1_lampngpk02586.DBHelper;
import poly.edu.duan1_lampngpk02586.model.cart_model;

public class cart_dao {
    // Table cart
    private static final String cart_table = "cart_table";
    private static final String ac_ID_cart = "ac_ID";
    private static final String drinks_ID = "drinks_ID";
    private static final String quantity = "quantity";
    private static final String order_ID = "order_ID";
    DBHelper dbHelper;
    SQLiteDatabase db;

    public cart_dao(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<cart_model> getList(String user) {
        ArrayList<cart_model> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + cart_table + " WHERE " + ac_ID_cart + " = ? AND " + order_ID + " = 0", new String[]{user});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                cart_model cart_model = new cart_model();
                cart_model.setAc_id_cart(cursor.getString(cursor.getColumnIndex(ac_ID_cart)));
                cart_model.setDrink_id(cursor.getInt(cursor.getColumnIndex(drinks_ID)));
                cart_model.setQuantity(cursor.getInt(cursor.getColumnIndex(quantity)));
                cart_model.setOrder_ID(cursor.getInt(cursor.getColumnIndex(order_ID)));
                list.add(cart_model);
            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<cart_model> getListCartOrder(int order_id) {
        ArrayList<cart_model> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + cart_table + " WHERE " + order_ID + " = ?", new String[]{String.valueOf(order_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                cart_model cart_model = new cart_model();
                cart_model.setAc_id_cart(cursor.getString(cursor.getColumnIndex(ac_ID_cart)));
                cart_model.setDrink_id(cursor.getInt(cursor.getColumnIndex(drinks_ID)));
                cart_model.setQuantity(cursor.getInt(cursor.getColumnIndex(quantity)));
                cart_model.setOrder_ID(cursor.getInt(cursor.getColumnIndex(order_ID)));
                list.add(cart_model);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int checkCart(String user) {
        db = dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + cart_table + " WHERE " + ac_ID_cart + " = ? AND " + order_ID + " = 0", new String[]{user});
        if (cursor.getCount() != 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @SuppressLint("Range")
    public double getTotalPrice(String user) {
        db = dbHelper.getReadableDatabase();
        double totalPrice = 0;
        Cursor cursor = db.rawQuery("SELECT SUM(c.quantity * d.price_drinks) as Total FROM cart_table c,drinks_table d WHERE c.drinks_id = d.id_drinks AND c.ac_id = ? AND c.order_id = 0", new String[]{user});
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return totalPrice;
    }

    public boolean create(cart_model cart_model) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ac_ID_cart, cart_model.getAc_id_cart());
            contentValues.put(drinks_ID, cart_model.getDrink_id());
            contentValues.put(quantity, cart_model.getQuantity());
            contentValues.put(order_ID, cart_model.getOrder_ID());
            db.insert(cart_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(cart_model cart_model) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(quantity, cart_model.getQuantity());
            db.update(cart_table, contentValues, "" + ac_ID_cart + " = ? AND " + drinks_ID + " = ? AND " + order_ID + " = ?",
                    new String[]{String.valueOf(cart_model.getAc_id_cart()), String.valueOf(cart_model.getDrink_id()), String.valueOf(cart_model.getOrder_ID())});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reload(String user, int id_drinks, int quantityy) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(quantity, quantityy);
            db.update(cart_table, contentValues, "" + ac_ID_cart + " = ? AND " + drinks_ID + " = ?",
                    new String[]{String.valueOf(user), String.valueOf(id_drinks)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean redelete(String user, int id_drinks) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(cart_table, "" + ac_ID_cart + " = ? AND " + drinks_ID + " = ?", new String[]{String.valueOf(user), String.valueOf(id_drinks)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByOrderID(int order_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(cart_table, "" + order_ID + " = ?", new String[]{String.valueOf(order_id)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //set order id for cart
    public boolean setOrderID(String user, int id, int number) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(order_ID, id);
            db.update(cart_table, contentValues, "" + ac_ID_cart + " = ? AND " + order_ID + " = ?",
                    new String[]{user, String.valueOf(number)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findCart(String user, int id_drink) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + cart_table + " WHERE " + ac_ID_cart + " = ? AND " + drinks_ID + " = ? AND " + order_ID + " = 0", new String[]{String.valueOf(user), String.valueOf(id_drink)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("Range")
    public int getQuantity(int drink_id, String user) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + cart_table + " WHERE " + drinks_ID + " = ? AND " + ac_ID_cart + " = ? AND " + order_ID + " = 0", new String[]{String.valueOf(drink_id), user});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(quantity));
        } else {
            return 0;
        }
    }

}
