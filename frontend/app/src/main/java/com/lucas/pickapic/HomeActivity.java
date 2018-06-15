package com.lucas.pickapic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lucas.pickapic.service.HttpService;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void listarVotacoes(View view) {
        Intent intent = new Intent(this, MinhasVotacoesActivity.class);
        startActivity(intent);
    }
}
