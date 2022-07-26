package com.android.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText username , password , repassword;
    Button signin , signup;
    dbConnectionForLog DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        DB = new dbConnectionForLog(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(SignUp.this,"All Fields Required" , Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkUser = DB.checkUserName(user);
                        if(checkUser == false) {
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true) {
                                Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SignUp.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUp.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Passwords Are Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);

            }
        });

    }
}