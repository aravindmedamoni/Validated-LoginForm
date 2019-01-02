package com.medamoniaravind.loginformwithsqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME="usersdetails";
    public static final String Tablename="usersinfo";
    public static final String columnid="Id";
    public static final String columnname="Username";
    public static final String columnsurname="Surname";
    public static final String columnMail="E_mail";
    public static final String columnmobileno="Mobilenum";
    public static final String columnpassword="Password";
    public static final int databaseversion=1;
    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, databaseversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Tablename+"(Id integer primary key autoincrement,Username text,Surname text,E_mail text,Mobilenum number,Password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+Tablename);

    }

    public boolean insertuserdata(String Username,String Surname,String E_mail,String Mobilenum,String Password){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(columnname,Username);
        contentValues.put(columnsurname,Surname);
        contentValues.put(columnMail,E_mail);
        contentValues.put(columnmobileno,Mobilenum);
        contentValues.put(columnpassword,Password);
        long result=sqLiteDatabase.insert(Tablename,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

  /*  public Cursor getusrdata(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+Tablename+"where"+columnMail+"and"+columnpassword,null);
        return cursor;
    }*/
 /* public Integer deletedata(String id){
      SQLiteDatabase sqLiteDatabase=getWritableDatabase();
     return sqLiteDatabase.delete(Tablename,"Id=?",new String[] {id});
  }*/
}
