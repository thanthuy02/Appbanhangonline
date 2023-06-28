package com.example.appbanhangonline.activities.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.dbhandler.LoginHandler;

public class RegisterActivity extends Activity {
    Button btnRegister;
    EditText etCustomerName;
    EditText etCustomerAddress;
    EditText etCustomerPhone;
    EditText etCustomerEmail;
    EditText etCustomerPassword;
    TextView backToLogin;
    LoginHandler loginHandler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginHandler = new LoginHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etCustomerName = (EditText) findViewById(R.id.etCustomerName);
        etCustomerAddress = (EditText) findViewById(R.id.etCustomerAddress);
        etCustomerPhone = (EditText) findViewById(R.id.etCustomerPhone);
        etCustomerEmail = (EditText) findViewById(R.id.etCustomerEmail);
        etCustomerPassword = (EditText) findViewById(R.id.etCustomerPassword);
        backToLogin = (TextView) findViewById(R.id.backToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String name = etCustomerName.getText().toString().trim();
                String address = etCustomerAddress.getText().toString().trim();
                String phone = etCustomerPhone.getText().toString().trim();
                String email = etCustomerEmail.getText().toString().trim();
                String password = etCustomerPassword.getText().toString().trim();

                if (loginHandler.register(name, phone, address, email, password)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản thành công.",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản không thành công.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
