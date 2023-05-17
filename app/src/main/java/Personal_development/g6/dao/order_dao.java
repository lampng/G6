package Personal_development.g6.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Personal_development.g6.DBHelper;
import Personal_development.g6.model.order_model;

public class order_dao {

    // Table order
    private static final String cart_table = "cart_table";
    private static final String ac_ID_cart = "ac_ID";
    private static final String order_ID = "order_ID";

    private static final String order_table = "order_table";
    private static final String id_order = "id_order";
    private static final String customer_ID = "customer_ID";
    private static final String price_total = "price_total";
    private static final String date_order = "date_order";
    private static final String order_status = "order_status";

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public order_dao(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Lấy danh sách đặt hàng cho người dùng
    @SuppressLint("Range")
    public ArrayList<order_model> getListCustomer(String customer) {
        ArrayList<order_model> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + order_table + " WHERE " + customer_ID + " = ? ORDER BY " + id_order + " DESC", new String[]{customer});
        while (cursor.moveToNext()) {
            order_model order = new order_model();
            order.setId_order(cursor.getInt(cursor.getColumnIndex(id_order)));
            order.setCustomer_ID(cursor.getString(cursor.getColumnIndex(customer_ID)));
            order.setPrice_total(cursor.getDouble(cursor.getColumnIndex(price_total)));
            order.setDate_order(cursor.getString(cursor.getColumnIndex(date_order)));
            order.setOrder_status(cursor.getString(cursor.getColumnIndex(order_status)));
            list.add(order);
        }
        return list;
    }

    //Lấy danh sách đặt hàng cho nhân viên và quản trị
    @SuppressLint("Range")
    public ArrayList<order_model> getListStaff() {
        ArrayList<order_model> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + order_table + " ORDER BY " + id_order + " DESC", null);
        while (cursor.moveToNext()) {
            order_model order = new order_model();
            order.setId_order(cursor.getInt(cursor.getColumnIndex(id_order)));
            order.setCustomer_ID(cursor.getString(cursor.getColumnIndex(customer_ID)));
            order.setPrice_total(cursor.getDouble(cursor.getColumnIndex(price_total)));
            order.setDate_order(cursor.getString(cursor.getColumnIndex(date_order)));
            order.setOrder_status(cursor.getString(cursor.getColumnIndex(order_status)));
            list.add(order);
        }
        return list;
    }

    //Cập nhập trạng thái đặt hàng
    public boolean setStatus(String staff, int id) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(order_status, staff);
            db.update(order_table, contentValues, "" + id_order + " = ?",
                    new String[]{String.valueOf(id)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Tạo danh sách lịch sử đặt hàng
    public boolean create(order_model order) {
        db = dbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(customer_ID, order.getCustomer_ID());
            contentValues.put(price_total, order.getPrice_total());
            contentValues.put(date_order, order.getDate_order());
            db.insert(order_table, null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Lấy id order bởi id người dùng
    @SuppressLint("Range")
    public int getIdOrder(String customerID, int number) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_order FROM order_table WHERE customer_id = ? ORDER BY id_order DESC", new String[]{customerID});
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(id_order));
        } else {
            return 0;
        }
    }

    //Xoá id order
    public boolean deleteByOrderID(int order_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(order_table, "" + id_order + " = ?", new String[]{String.valueOf(order_id)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Thống kê doanh thu
    public int getRevenue(String start, String end) {
        start = start.replace("/", "");
        end = end.replace("/", "");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(price_total) FROM order_table WHERE substr(date_order,7) ||substr(date_order,4,2) ||substr(date_order,1.2) BETWEEN ? AND ?", new String[]{start, end});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
