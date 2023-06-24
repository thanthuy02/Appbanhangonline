package com.example.appbanhangonline.activities.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.admin.MenuAdminActivity;
import com.example.appbanhangonline.activities.user.HomeUserActivity;
import com.example.appbanhangonline.dbhandler.LoginHandler;

import java.util.Objects;

public class LoginActivity extends Activity {
    Button btnRegister;
    Button btnLogin;
    EditText etPassword;
    EditText etEmail;
    TextView forgotPassword;
    LoginHandler loginHandler;

//    @SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginHandler = new LoginHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),
                        ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (Objects.equals(loginHandler.checkLogin(email, password), "admin")) {
                    Intent i = new Intent(getApplicationContext(), MenuAdminActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else if (Objects.equals(loginHandler.checkLogin(email, password), "customer")){
                    Intent i = new Intent(getApplicationContext(), HomeUserActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản hoặc mật khẩu không đúng, hãy kiểm tra lại.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
