package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Set the Window fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "MainActivity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

        startMenuActivity();
    }


    private void startMenuActivity() {
        Button viewProductsButton = findViewById(R.id.btnViewProducts);
        viewProductsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityMenuList.class);
            startActivity(intent);
        });
    }

}
