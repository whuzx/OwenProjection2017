package com.MyAndroidCollection.db;

import com.MyAndroidCollection.util.LogX;
import com.MyAndroidCollection.util.Proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int  VERSION=1;
    private static final String TAG="DataBaseHelper";



    public DataBaseHelper(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, version);

    }

    public DataBaseHelper(Context context,String name,int version){
        this(context, name, null, version);
    }

    public DataBaseHelper(Context context,String name){
        this(context, name, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        LogX.i(TAG, "create a DataBase");
        String sqlcmd="create table user(id int, name varcha(20))";
        db.execSQL(sqlcmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        LogX.i(TAG, "update a DataBase");


    }

    public static void main(String[] args) {
    }

}
