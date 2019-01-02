package com.medamoniaravind.loginformwithsqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class RegisterForm extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText rgstr_usrname,rgstr_surname,rgstr_mail,rgstr_mbnum,rgstr_pswd,rgstr_cnfmpswd;
    Button rgstbtn_register;
    TextView rgstr_dlt;
    String Id,Username,Surname,Email,Mobilenum,Password;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        databaseHelper=new DatabaseHelper(this);
        rgstr_usrname=(EditText)findViewById(R.id.rgstrusrname);
        rgstr_surname=(EditText)findViewById(R.id.rgstrsurname);
        rgstr_mail=(EditText)findViewById(R.id.rgstrusrmail);
        rgstr_mbnum=(EditText)findViewById(R.id.rgstrmbnum);
        rgstr_pswd=(EditText)findViewById(R.id.rgstrpswd);
        rgstr_cnfmpswd=(EditText)findViewById(R.id.rgstrcnfmpswd);
        rgstbtn_register=(Button)findViewById(R.id.rgstrbtn);
        /*rgstr_dlt=(TextView)findViewById(R.id.rgstrdlt);*/
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(this,R.id.rgstrusrname,"[a-zA-Z\\s]+",R.string.Username_error);
        awesomeValidation.addValidation(this,R.id.rgstrsurname,"[a-zA-Z\\s]+",R.string.Surname_error);
        awesomeValidation.addValidation(this,R.id.rgstrusrmail, android.util.Patterns.EMAIL_ADDRESS,R.string.Email_error);
        awesomeValidation.addValidation(this,R.id.rgstrmbnum, RegexTemplate.TELEPHONE,R.string.Mbnum_error);
        awesomeValidation.addValidation(this,R.id.rgstrpswd, regexPassword,R.string.Password_error);
        awesomeValidation.addValidation(this,R.id.rgstrcnfmpswd, R.id.rgstrpswd,R.string.ConfirmPassword_error);
    }

    public void registerhere(View view) {
        if (awesomeValidation.validate()){
            Username=rgstr_usrname.getText().toString();
            Surname=rgstr_surname.getText().toString();
            Email=rgstr_mail.getText().toString();
            Mobilenum=rgstr_mbnum.getText().toString();
            Password=rgstr_pswd.getText().toString();
            boolean isInserted=databaseHelper.insertuserdata(Username,Surname,Email,Mobilenum,Password);
            if (isInserted==true){
                rgstr_usrname.setText("");
                rgstr_surname.setText("");
                rgstr_mail.setText("");
                rgstr_mbnum.setText("");
                rgstr_pswd.setText("");
                Toast.makeText(this, "You Registered Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
               /* rgstr_usrname.setText("");
                rgstr_surname.setText("");
                rgstr_mail.setText("");
                rgstr_mbnum.setText("");
                rgstr_pswd.setText("");*/
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

   /* public void deletuserdata(View view) {
        Id=rgstr_id.getText().toString();
       Integer deletedrow= databaseHelper.deletedata(Id);
        if (deletedrow>0) {
            rgstr_id.setText("");
            Toast.makeText(this, "data is successfully deleted", Toast.LENGTH_SHORT).show();
        }else {
            rgstr_id.setText("");
            Toast.makeText(this, "data is not deleted", Toast.LENGTH_SHORT).show();
        }
    }*/
}
