package com.lucas.pickapic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    public LoginButton loginButton;
    private CallbackManager callbackManager;
    private Intent intent;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AccessToken.isCurrentAccessTokenActive()) {
            redirecionarHome();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
    }

    public void login(View view) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                setResult(RESULT_OK);
                redirecionarHome();
            }

            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
                Toast.makeText(getApplicationContext(), "Login inválido", Toast.LENGTH_LONG + 2);
            }

            @Override
            public void onError(FacebookException e) {
                // Handle exception
            }
        });
    }

    private void redirecionarHome() {
        intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}