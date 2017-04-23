package com.MyAndroidCollection.db;

import com.MyAndroidCollection.R;
import com.MyAndroidCollection.util.LogX;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SQLiteActivity extends Activity implements OnClickListener {
    private static final String TAG="SQLiteActivity";
    private Button  createBtn=null;
    private Button  updateDateBaseBtn=null;
    private Button  insertBtn=null;
    private Button  updateBtn=null;
    private Button  queryBtn=null;
    private Button  deleteBtn=null;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LogX.i(TAG, "oncreate");
        setContentView(R.layout.sqlite);
        initResource();
    }


    private void initResource() {
        // TODO Auto-generated method stub
        createBtn=(Button) findViewById(R.id.sql_createDatabase);
        updateDateBaseBtn=(Button) findViewById(R.id.sql_updateDatabase);
        insertBtn=(Button) findViewById(R.id.sql_insert);
        updateBtn=(Button) findViewById(R.id.sql_update);
        queryBtn=(Button) findViewById(R.id.sql_query);
        deleteBtn=(Button) findViewById(R.id.sql_delete);


        createBtn.setOnClickListener(this);
        updateDateBaseBtn.setOnClickListener(this);
        insertBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }


    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        LogX.i(TAG, "onSaveInstanceState");
    }


    @Override
    public void onClick(View v) {
        LogX.i(TAG, "onclick");
        switch (v.getId()) {
        case R.id.sql_createDatabase:

            DataBaseHelper dbBaseHelper1=new DataBaseHelper(SQLiteActivity.this, "test_db");

            SQLiteDatabase  db1= dbBaseHelper1.getReadableDatabase();
            break;

        case R.id.sql_updateDatabase:
            DataBaseHelper dbBaseHelper2 =new DataBaseHelper(SQLiteActivity.this, "test_db",2);
            SQLiteDatabase db2= dbBaseHelper2.getReadableDatabase();

            break;
        case R.id.sql_insert:
           ContentValues values = new ContentValues();
           values.put("id", 1);
           values.put("name", "zhouxiong");
           DataBaseHelper dbDataBaseHelper3 =new DataBaseHelper(SQLiteActivity.this, "test_db");
           SQLiteDatabase db3= dbDataBaseHelper3.getWritableDatabase();
           db3.insert("user", null, values);


            break;
        case R.id.sql_update:

            DataBaseHelper dbHelper4= new DataBaseHelper(SQLiteActivity.this, "test_db");
            SQLiteDatabase db4= dbHelper4.getWritableDatabase();
            ContentValues values2= new ContentValues();
            values2.put("name", "zhoujie");
            db4.update("user", values2, "id=?", new String[]{"1"});


            break;
        case R.id.sql_query:

            DataBaseHelper dbHelper5= new DataBaseHelper(SQLiteActivity.this, "test_db");
            SQLiteDatabase db5= dbHelper5.getWritableDatabase();

            Cursor cursor= db5.query("user", new String[]{"id","name"}, "id=?", new String[]{"1"}, null, null, null, null);
            while (cursor.moveToNext()) {
              String name=cursor.getString(cursor.getColumnIndex("name"));
              LogX.i(TAG, "query--->" + name);

            }



            break;
        case R.id.sql_delete:


            DataBaseHelper dbHelper6= new DataBaseHelper(SQLiteActivity.this, "test_db");
            SQLiteDatabase db6= dbHelper6.getWritableDatabase();

            db6.delete("user", "id=?", new String[]{"1"});

            break;


        default:
            Log.i(TAG, "error");
            break;
        }

    }


}
