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
import com.example.appbanhangonline.models.User;

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
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        ForgotPasswordActivity.class);
                i.putExtra("userEmail", etEmail.getText().toString().trim());
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                User user = loginHandler.checkLogin(email, password);
                if (user != null && Objects.equals(user.getRole(), "admin")) {
                    Intent i = new Intent(getApplicationContext(), MenuAdminActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else if (user != null && Objects.equals(user.getRole(), "customer")){
                    Intent i = new Intent(getApplicationContext(), HomeUserActivity.class);
                    i.putExtra("user_name", user.getUsername());
                    i.putExtra("user_email", user.getEmail());
                    i.putExtra("user_id", user.getUserID());
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
