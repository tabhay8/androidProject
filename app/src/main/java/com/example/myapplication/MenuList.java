package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuList<ProductEntity> extends Activity {
    public RecyclerView recyclerView;
    private List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView = findViewById(R.id.product_recyclerView);

    }


}
