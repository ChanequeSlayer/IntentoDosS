package com.example.intentodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    }

    public void irPrincipal(View view){
        Intent irPrincipal = new Intent(this,MainActivity.class);
        startActivity(irPrincipal);
    }


}