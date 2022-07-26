package com.android.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText username , password;
    Button signin;
    dbConnectionForLog DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.signin1);
        DB = new dbConnectionForLog(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(SignIn.this,"All Fields Required" ,Toast.LENGTH_SHORT ).show();
                else {
                    Boolean checkUserPass = DB.checkUserNamePassword(user,pass);
                    if(checkUserPass == true){
                        Toast.makeText(SignIn.this,"Login Successful" ,Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(SignIn.this,"Login Failed" ,Toast.LENGTH_SHORT ).show();

                    }
                }
            }
        });

    }
}