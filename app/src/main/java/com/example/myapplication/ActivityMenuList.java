package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityMenuList extends AppCompatActivity {

    private static final String TAG = "ActivityMenuList";
    protected RecyclerView pizzaMenuRecyclerView;
    protected RecyclerView.LayoutManager pizzaMenuRecyclerViewLayoutManager;
    protected List<Product> pizzaMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        pizzaMenuRecyclerView = (RecyclerView) findViewById(R.id.pizza_menu_recycler_view);
        pizzaMenuRecyclerViewLayoutManager = new LinearLayoutManager(this);

        initializePizzaMenuList();
        PizzaMenuAdapter adapter = new PizzaMenuAdapter(pizzaMenuList);

        pizzaMenuRecyclerView.setAdapter(adapter);
        pizzaMenuRecyclerView.setLayoutManager(pizzaMenuRecyclerViewLayoutManager);

    }

    // TODO: 08-04-2023 integrate this with firebase so that menu data can be get dynamically.
    // Temporarily making the pizza menu to be hard coded.
    private void initializePizzaMenuList() {
        pizzaMenuList = new ArrayList<>();

        String[] pizzaName = {"Margherita", "Marinara", "Napoletana", "Capricciosa", "Quattro Formaggi"};
        double[] pizzaPrice = {19.00, 20.25, 22.50, 19.99, 25.00};
        double[] pizzaDiscount = {0.10, 0.10, 0.00, 0.25, 0.15};

        // Should be removed after the function is converted to have data dynamically through firebase.
        //noinspection ConstantConditions
        if(pizzaName.length == pizzaPrice.length && pizzaName.length == pizzaDiscount.length){

            for (int i=0; i < pizzaName.length; i++) {
                Product pizza = new Product();
                pizza.setPizzaName(pizzaName[i]);
                pizza.setPizzaPrice(pizzaPrice[i]);
                pizza.setPizzaDiscount(pizzaDiscount[i]);
                pizzaMenuList.add(pizza);
            }

        }else {
            Log.e(TAG, "Hardcoded pizza list is not equal in length");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            Log.i(TAG, "Action cart clicked.");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
