package com.rishabhshukla.rubio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    EditText email,password;
    Button btnReg,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);
        btnReg = (Button) findViewById(R.id.btnReg);
        btnLogin = (Button) findViewById(R.id.btnSignIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(password==null||email==null){
                    Toast.makeText(LoginActivity.this, "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.getText().length()<=6){
                    Toast.makeText(LoginActivity.this, "Password must be greater than 6 chars", Toast.LENGTH_SHORT).show();
                }else if(!email.getText().toString().contains("@")){
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }else if(true){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);

                    finish();
                }
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}

