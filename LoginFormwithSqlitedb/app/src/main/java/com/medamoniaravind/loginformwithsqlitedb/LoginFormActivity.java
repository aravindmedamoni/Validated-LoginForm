package com.medamoniaravind.loginformwithsqlitedb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFormActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText lgn_email,lgn_pswd;
    Button lgnbtn_login;
    String Email,Password;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        lgn_email=(EditText)findViewById(R.id.lgnmail);
        lgn_pswd=(EditText)findViewById(R.id.lgnpswd);
        lgnbtn_login=(Button)findViewById(R.id.lgnbtn);
    }

    public void loginhere(View view) {
        Email=lgn_email.getText().toString();
        Password=lgn_pswd.getText().toString();
        cursor=sqLiteDatabase.rawQuery("select * from "+databaseHelper.Tablename+" where "+databaseHelper.columnMail+"=? and "+databaseHelper.columnpassword+"=?",new String[] {Email,Password});
        if (cursor==null)
            Toast.makeText(this, "Your's Login Failed,,Please Enter Details", Toast.LENGTH_LONG).show();
        else  if (cursor!=null){
            if (cursor.getCount()>0){
                lgn_email.setText("");
                lgn_pswd.setText("");
                Toast.makeText(this, "Successfully you Login", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Please Enter All Valid Deatils", Toast.LENGTH_LONG).show();
        }


    }

    public void registerhere(View view) {
        startActivity(new Intent(this,RegisterForm.class));
    }
}
