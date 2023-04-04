package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAnalytics.getInstance(this);




    }

    @Override
    protected void onStart() {
        super.onStart();


        Button viewProductsButton = findViewById(R.id.btnViewProducts);
        viewProductsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityMenuList.class);
            startActivity(intent);
        });

    }
}