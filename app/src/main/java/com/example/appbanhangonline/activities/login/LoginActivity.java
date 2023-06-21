package com.example.appbanhangonline.activities.login;

import android.app.Activity;
import android.os.Bundle;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.dbhandler.LoginHandler;

public class LoginActivity extends Activity {
    LoginHandler loginHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginHandler = new LoginHandler(this);
    }
}
