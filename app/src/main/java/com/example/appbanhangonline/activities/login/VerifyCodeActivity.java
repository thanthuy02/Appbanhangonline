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
import com.example.appbanhangonline.dbhandler.LoginHandler;

import java.util.Random;

public class VerifyCodeActivity extends Activity {
    EditText etVerifyCode;
    Button btnVerify;
    TextView tvResendCode;
    LoginHandler loginHandler;
    String code, email;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        loginHandler = new LoginHandler(this);
        etVerifyCode = (EditText) findViewById(R.id.etVerifyCode);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        tvResendCode = (TextView) findViewById(R.id.tvResendCode);

        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        email = intent.getStringExtra("email");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String verifyCode = etVerifyCode.getText().toString().trim();
                if (verifyCode.equals(code)) {
                    Intent i = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Mã xác nhận sai, vui lòng nhập lại",
                            Toast.LENGTH_LONG).show();
                    etVerifyCode.setText("");
                }
            }
        });

        tvResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Random random = new Random();
                String codeResend = String.valueOf(random.nextInt(900000) + 100000);

                loginHandler.sendEmail(email, codeResend);
                code = codeResend;
                Toast.makeText(getApplicationContext(),
                        "Đã gửi lại mã xác nhận",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}
