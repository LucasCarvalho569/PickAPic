package com.lucas.pickapic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
    }

    public void login(View view) {
        final Intent intent = new Intent(this, HomeActivity.class);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                Log.d("TESTE","facebook:onSuccess:" + result);
                String token = result.getAccessToken().toString();
                Log.d("TESTE","toekn:" + token);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d("TESTE", "cancelou");
            }

            @Override
            public void onError(FacebookException fe) {
                Log.d("TESTE", "erro");
            }
        });

    }

}
