package com.udara.traveltime;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Project.db";

    public DatabaseHelper(Context context) {
        super(context, "Project.db", null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase MyDB) {
        super.onConfigure(MyDB);
        MyDB.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {


        MyDB.execSQL("create Table users(ID INTEGER primary key autoincrement,f_name TEXT, l_name TEXT, nic TEXT, email TEXT, Passwd TEXT)");

        // create bus registration table
        MyDB.execSQL("create Table busReg(BUSPLATEID TEXT primary key, DNAME TEXT, NIC TEXT, TOTALSEAT TEXT)");
        // create routes registration table
        MyDB.execSQL("create Table routes(ROUTEID primary key autoincrement, BUSPLATEID TEXT, ROUTENO TEXT, DEPARTURE TEXT, ARRIAVAL TEXT, DATE TEXT, TIME TEXT, FOREIGN KEY (BUSPLATEID) REFERENCES busReg (BUSPLATEID))");

        MyDB.execSQL("create Table admin(username TEXT primary key, password TEXT, email TEXT, fullname TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists buses");
        MyDB.execSQL("drop table if exists admin");
        MyDB.execSQL("drop table if exists routes");
    }

    // **********************************************************************************************
// insert users.
    public Boolean insertData(String f_name, String l_name, String email, String nic, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("f_name", f_name);
        contentValues.put("l_name", l_name);
        contentValues.put("nic", nic);
        contentValues.put("email", email);
        contentValues.put("Passwd", password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertadmin(String fullname, String email, String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("admin",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkadminusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and Passwd = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkadminusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //************************************************************************************************
// bus registration table

    public Boolean registerBus(String plateNO, String driverName, String NIC, String total_seats)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSPLATEID", plateNO);
        contentValues.put("DNAME", driverName);
        contentValues.put("NIC", NIC);
        contentValues.put("TOTALSEAT", total_seats);

        long result = MyDB.insert("busReg",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateBus(String plateNO, String driverName, String NIC, String total_seats)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("BusPlateID", plateNO);
        contentValues.put("Driver_name", driverName);
        contentValues.put("NIC", NIC);
        contentValues.put("total_seats", total_seats);

        Cursor cursor = MyDB.rawQuery("Select * from buses where id = ?", new String[] {plateNO});
        if (cursor.getCount()>0){
            long result = MyDB.update("buses", contentValues,"id=?", new String[] {plateNO});
            if(result==-1) return false;
            else
                return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean deleteBus(String id)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from buses where id = ?", new String[] {id});
        if (cursor.getCount()>0){
            long result = MyDB.delete("buses","id=?", new String[] {id});
            if(result==-1) return false;
            else
                return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor viewbuses()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from buses", null);
        return cursor;
    }

    /*public Cursor getListContents(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor data = MyDB.rawQuery("Select * from buses", null);
        return data;*/


    public boolean checkid(String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from buses where id = ?", new String[] {id});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //************************************************************************************************
    // bus routes registration table
    public Boolean registerRoute(String plateNO, String routeNo, String departure, String arriaval, String date, String time)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSPLATEID", plateNO);
        contentValues.put("ROUTENO", routeNo);
        contentValues.put("DEPARTURE", departure);
        contentValues.put("ARRIAVAL", arriaval);
        contentValues.put("DATE", date);
        contentValues.put("TIME", time);

        long result = MyDB.insert("busReg",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //**************************************************************************************************

// Get the user id by email
    public Cursor getBusPlateNO() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT BUSPLATEID FROM busReg", null);

        return cursor;
    }

}
