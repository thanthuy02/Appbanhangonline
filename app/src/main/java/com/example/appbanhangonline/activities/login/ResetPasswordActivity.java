package com.example.appbanhangonline.activities.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.dbhandler.LoginHandler;

public class ResetPasswordActivity extends Activity {
    EditText etPassword;
    EditText etConfirmPassword;
    Button btnConfirm;
    LoginHandler loginHandler;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        loginHandler = new LoginHandler(this);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String password = etPassword.getText().toString().trim();
                String passwordConfirm = etConfirmPassword.getText().toString().trim();

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(),
                            "Mật khẩu không trùng khớp",
                            Toast.LENGTH_LONG).show();
                } else if (loginHandler.updatePassword(email,password)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Cập nhật mật khẩu thành công",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
