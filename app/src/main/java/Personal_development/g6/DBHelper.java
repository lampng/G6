package Personal_development.g6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

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

    // Table role
    private static final String role_table = "role_table";
    private static final String id_role = "id_role";
    private static final String name_role = "name_role";

    // Table order
    private static final String order_table = "order_table";
    private static final String id_order = "id_order";
    private static final String customer_ID = "customer_ID";
    private static final String price_total = "price_total";
    private static final String date_order = "date_order";
    private static final String order_status = "order_status";

    // Table cart
    private static final String cart_table = "cart_table";
    private static final String ac_ID_cart = "ac_ID";
    private static final String drinks_ID = "drinks_ID";
    private static final String quantity = "quantity";
    private static final String order_ID = "order_ID";

    // Table drinks
    private static final String drinks_table = "drinks_table";
    private static final String id_drinks = "id_drinks";
    private static final String cat_ID = "cat_ID";
    private static final String name_drinks = "name_drinks";
    private static final String image_drinks = "image_drinks";
    private static final String des_drinks = "des_drinks";
    private static final String price_drinks = "price_drinks";

    // Table category_drink
    private static final String category_table = "category_table";
    private static final String id_cat = "id_cat";
    private static final String name_cat = "name_cat";
    private static final String image_cat = "image_cat";

    public DBHelper(Context context) {
        super(context, "Du_an_1.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //account
        String db_account = "CREATE TABLE " + account_table + "("
                + id_ac + " TEXT PRIMARY KEY,"
                + role_ID + " INTEGER REFERENCES " + role_table + "(" + id_role + "), "
                + first_name + " TEXT, "
                + middle_name + " TEXT, "
                + last_name + " TEXT, "
                + phone_ac + " TEXT, "
                + password_ac + " TEXT, "
                + address_ac + " TEXT, "
                + avatar_ac + " BLOB)";
        db.execSQL(db_account);
        //role
        String db_role = "CREATE TABLE " + role_table + "("
                + id_role + " INTEGER PRIMARY KEY,"
                + name_role + " TEXT)";
        db.execSQL(db_role);
        //order
        String db_order = "CREATE TABLE " + order_table + "("
                + id_order + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + customer_ID + " TEXT REFERENCES " + account_table + "(" + id_ac + "), "
                + price_total + " DOUBLE,"
                + date_order + " TEXT,"
                + order_status + " TEXT REFERENCES " + account_table + "(" + id_ac + "))";
        db.execSQL(db_order);
        //cart
        String db_cart = "CREATE TABLE " + cart_table + "("
                + ac_ID_cart + " TEXT REFERENCES " + account_table + "(" + id_ac + "), "
                + drinks_ID + " INTEGER REFERENCES " + drinks_table + "(" + id_drinks + "),"
                + quantity + " INTEGER,"
                + order_ID + " INTEGER REFERENCES " + order_table + "(" + id_order + "))";
        db.execSQL(db_cart);
        //drinks
        String db_drinks = "CREATE TABLE " + drinks_table + "("
                + id_drinks + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + cat_ID + " INTEGER REFERENCES " + category_table + "(" + id_cat + "), "
                + name_drinks + " TEXT,"
                + image_drinks + " BLOB,"
                + des_drinks + " TEXT,"
                + price_drinks + " Double)";
        db.execSQL(db_drinks);
        //category_drink
        String db_category_drink = "CREATE TABLE " + category_table + "("
                + id_cat + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + name_cat + " INTEGER,"
                + image_cat + " BLOB)";
        db.execSQL(db_category_drink);

        //======================================Dữ liệu cố định===============================================
        //role
        db.execSQL("INSERT INTO " + role_table + " VALUES(1,'Admin'),(2,'Nhân viên'),(3,'Người dùng')");
        //cart

        //account temp
        db.execSQL("INSERT INTO " + account_table + " VALUES('pnglam',1,'Phan','Nguyên Gia','Lâm','0845292728','123123','91/1 Nam Cao',null)");
        db.execSQL("INSERT INTO " + account_table + " VALUES('nhanvien',2,'Nguyễn','Văn','A','0123456789','123123','456 Tôn Đức Thắng',null)");
        db.execSQL("INSERT INTO " + account_table + " VALUES('nguoidung',3,'Nguyễn','Văn','B','0123456790','123123','51 Đặng dung',null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
