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
import com.lucas.pickapic.model.Usuario;
import com.lucas.pickapic.service.UsuarioService;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    public LoginButton loginButton;
    private CallbackManager callbackManager;
    private Intent intent;
    private UsuarioService usuarioService = new UsuarioService();
    private Usuario usuario;

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
                criarUsuario();
                try {
                    usuarioService.salvarUsuario(usuario);
                    redirecionarHome();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
                Toast.makeText(getApplicationContext(), "Login inválido", Toast.LENGTH_LONG + 2);
            }

            @Override
            public void onError(FacebookException e) {
                // Handle exception
                Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG + 2);
            }
        });
    }

    private void redirecionarHome() {
        intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void criarUsuario() {
        usuario = new Usuario();
        usuario.setFacebookId(AccessToken.getCurrentAccessToken().getUserId());
    }
}