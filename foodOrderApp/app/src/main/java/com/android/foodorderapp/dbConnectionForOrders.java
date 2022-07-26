package com.android.foodorderapp;


// db connection for order crud operations


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbConnectionForOrders extends SQLiteOpenHelper {
    public dbConnectionForOrders(@Nullable Context context) {
        super(context, "FoodOrder.db", null ,1);
    }
    // table name is: OrderDetails
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table OrderDetails(name TEXT primary key, cardNumber TEXT, cardExpiry TEXT, cardPin TEXT, totalPay TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists Orderdetails");
    }

    // Insertion Method
    public Boolean insertOrderData(String name, String cardNumber, String cardExpiry, String cardPin, String totalPay){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name: ", name);
        contentValues.put("Card number: ", cardNumber);
        contentValues.put("Card Expiry: ", cardExpiry);
        contentValues.put("Card Pin: ", cardPin);
        contentValues.put("Total: ", totalPay);
        long result = db.insert("OrderDetails", null, contentValues);
        if (result == -1)
        {
            return false;
        }else{
            return true;
        }
    }

    // Update Method
    public Boolean updateOrderData(String name, String cardNumber, String cardExpiry, String cardPin, String totalPay){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Card number: ", cardNumber);
        contentValues.put("Card Expiry: ", cardExpiry);
        contentValues.put("Card Pin: ", cardPin);
        contentValues.put("Total: ", totalPay);
        Cursor cursor = db.rawQuery("Select * from OrderDetails where name = ?", new String[]{name});
        if (cursor.getCount()>0){
        long result = db.update("OrderDetails", contentValues, "name=?", new String[]{name});
            if (result == -1)
            {
                return false;
            }else{
                return true;}
            }
        else{
                return false;
            }
    }

        // Delete Method
        public Boolean deleteOrderData(String name){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from OrderDetails where name = ?", new String[]{name});
            if (cursor.getCount() > 0) {
                long result = db.delete("OrderDetails", "name=?", new String[]{name});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

    // Retrieve Method
    public Cursor retrieveOrderData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from OrderDetails", null);
        return cursor;
    }


}




/*
 sqLiteDatabase.execSQL("create table orderss(Name TEXT , Cardnum TEXT , Carddate TEXT , Cardpass TEXT , Total TEXT   )");

public Boolean insertorder(String Name , String Cardnum, String Carddate , String Cardpass, String Total){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("Name" , Name);
        contentValues.put("Cardnum" , Cardnum);
        contentValues.put("Carddate" , Carddate);
        contentValues.put("Cardpass" , Cardpass);
        contentValues.put("Total" , Total);

        long result  = sqLiteDatabase.insert("orderss" , null , contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
  public ArrayList<String> order (String n)
    {
        ArrayList<String> arrayList = null;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orderss where Cardnum = ?",new String[]{n});
        if(cursor.moveToFirst())
        {
            do
            {
                String Name = cursor.getString(0);
                String Number = cursor.getString(1);
                String Carddate = cursor.getString(2);
                String Cardpass = cursor.getString(3);
                String Total = cursor.getString(4);
                arrayList = new ArrayList<String>(Arrays.asList(Name,Number,Carddate,Cardpass,Total));
            }
            while(cursor.moveToNext());
        }
        return arrayList;
    }

 */
