package com.example.fcis_ecommerce;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static String databaseName="ShoppingDatabase";
    SQLiteDatabase db;
    public Database(@Nullable Context context) {
        super(context, databaseName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table customer(CustID integer primary key autoincrement" +
                ",username text not null,email text not null,password text not null," +
                "gender text not null,Birthdate text not null,job text not null)");

        sqLiteDatabase.execSQL("create table Categories(CatID integer primary key autoincrement,CatName text not null)");

        sqLiteDatabase.execSQL("create table product (id integer primary key autoincrement, name text not null ," +
                "price real not null , quantity integer not null , cate_id integer not null ," +
                "foreign key (cate_id)references Categories (CatID))");

        sqLiteDatabase.execSQL("create table Orders(OrdID integer primary key autoincrement" +
                ",OrdDate Date not null,Cust_ID  integer not null," +
                "Address text not null,Foreign KEY (Cust_ID) References customer (CustID))");

        sqLiteDatabase.execSQL("create table Order_details(Ord_ID integer not null" +
                ",pro_ID integer,Quantity integer not null,primary key(Ord_ID,pro_ID),Foreign KEY (Ord_ID) References Orders (OrdID)," +
                "Foreign KEY (pro_ID) References Product (proID))");


       // ShoppingDatabase.execSQL("insert into product (id,name,price,quantity,cat_id)"+"values (1,'purple shirt',100,10,1)");
        //insert cat
        /*sqLiteDatabase.execSQL("insert into Categories(CatName) values('shirts')");
        sqLiteDatabase.execSQL("insert into Categories(CatName) values('shoes')");
        sqLiteDatabase.execSQL("insert into Categories(CatName) values('accessries')");*/
        /*ContentValues values=new ContentValues();
        values.put("CatName","clothes" );
        ShoppingDatabase.insert("Categories",null,values);
        values.put("CatName","shoes" );
        ShoppingDatabase.insert("Categories",null,values);
        values.put("CatName","accessories" );
        ShoppingDatabase.insert("Categories",null,values);

        //insert product
        ContentValues product=new ContentValues();
        product.put("name","purple shirt" );
        ShoppingDatabase.insert("product",null,product);
        product.put("name","black shirt" );
        ShoppingDatabase.insert("product",null,product);
        product.put("name","yellow shirt" );
        ShoppingDatabase.insert("product",null,product);
        product.put("name","green shirt" );
        ShoppingDatabase.insert("product",null,product);
        product.put("name","pink shirt" );
        ShoppingDatabase.insert("product",null,product);*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS customer");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Produsts");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Order_details");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Categories");
        onCreate(sqLiteDatabase);
    }
    public void new_customer(String username,String mail,String password,String gender,String Birthdate,String job ){
        ContentValues rows=new ContentValues();
        rows.put("username",username);
        rows.put("password",password);
        rows.put("gender",gender);
        rows.put("email" , mail);
        rows.put("Birthdate",Birthdate);
        rows.put("job",job);
        db=getWritableDatabase();
        db.insert("customer",null,rows);
        db.close();
    }
    public Boolean checkUserName(String name){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * FROM customer where username=?",new String[]{name});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkUserName_Pass(String name,String pass){

        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * FROM customer where username=? and password=?",new String[]{name,pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean updatepassword(String name,String pass){
        //SQLiteDatabase mydb=this.getWritableDatabase();
        db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",pass);
        long res=db.update("customer",contentValues,"username=?",new String[]{name});
        if(res==-1)
            return  false;
        else
            return true;
    }

     public void insertProduct(String name , double price , int quantity , int cat_id ){
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name", name);
        values.put("price",price);
        values.put("quantity",quantity);
        values.put("cate_id",cat_id);
        db.insert("product",null,values);
        db.close();
    }
    public void insertCategory(String name){
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("CatName", name);
        db.insert("Categories",null,values);
        db.close();
    }

   public Cursor getCategory(){
        db=getReadableDatabase();
        String []fields={"CatID","CatName"};
        Cursor cursor= db.query("Categories",fields,null,null,null,null,null);
        //Cursor getname = ShoppingDatabase.rawQuery("select CatName from Categories");
        if (cursor.getCount()>0)
            cursor.moveToFirst();

        db.close();

        return cursor;
    }
    public String getCatId(String name ){
        db=getReadableDatabase();
        String[]args={name};
        Cursor cursor=db.rawQuery("select CatID from Categories where CatName =?",args);

        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            db.close();
            return cursor.getString(0);
        }
        db.close();

        cursor.close();
        return null;

    }

    public String resetPassword(String userName) {
        db = getReadableDatabase();
        String[] arg = {userName};
        Cursor curs = db.rawQuery("select * from customer where username =? ", new String[]{userName});
        if (curs != null) {
            curs.moveToFirst();
        }
        db.close();
        return curs.getString(3);
    }

    public class_product getProductWithID(int proID) {
        db = getReadableDatabase();
        String[] rowDetails = {"ProName", "Price", "Quantity", "CatID", "proID"};
        class_product pro = null;
        Cursor cursor = db.query("product", rowDetails, "proID=" + proID + "", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            pro = new class_product(cursor.getInt(4), cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
        }
        db.close();
        return pro;
    }
    public Cursor getproductname (String name ){
        db = getReadableDatabase();
        Cursor matched = db.rawQuery("select * from product where name like ? ", new String[] {"%"+name+"%"});
        if (matched.getCount() != 0){
            matched.moveToFirst();
            db.close();
            return matched ;
        }
        db.close();
        return null ;
    }

    public void addtocart (String name , int quan , double price ){

    }
}
