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

import java.util.Random;

public class ForgotPasswordActivity extends Activity {
    Button btnSendCode;
    EditText etCustomerEmail;
    LoginHandler loginHandler;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        loginHandler = new LoginHandler(this);
        btnSendCode = (Button) findViewById(R.id.btnSendCode);
        etCustomerEmail = (EditText) findViewById(R.id.etCustomerEmail);
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        etCustomerEmail.setText(userEmail);

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String email = etCustomerEmail.getText().toString().trim();
                if (!loginHandler.checkEmail(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản không tồn tại.",
                            Toast.LENGTH_LONG).show();
                    etCustomerEmail.setText("");
                } else {
                    Random random = new Random();
                    String code = String.valueOf(random.nextInt(900000) + 100000);

                    loginHandler.sendEmail(email, code);
                    Intent i = new Intent(getApplicationContext(), VerifyCodeActivity.class);
                    i.putExtra("code", code);
                    i.putExtra("email", email);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đã gửi mã xác nhận",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

}
