package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PizzaMenuAdapter;
import com.example.myapplication.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityMenuList extends AppCompatActivity {

    private static final String TAG = "ActivityMenuList";

    protected List<Product> pizzaMenuList;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        db = FirebaseFirestore.getInstance();
        retrievePizzaMenuFromFirestore();
    }

    private void onFirebaseDataRetrieved(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, MenuListFragment.newInstance((ArrayList<Product>) pizzaMenuList))
                .commit();
    }

    private void retrievePizzaMenuFromFirestore() {
        pizzaMenuList = new ArrayList<>();

        CollectionReference pizzaMenuRef = db.collection("pizza_menu");

        pizzaMenuRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Map<String, Object> data = document.getData();
                        String pizzaName = (String) data.get("pizzaName");
                        double pizzaPrice = 0.0;
                        double pizzaDiscount = 0.0;

                        Object priceObj = data.get("pizzaPrice");
                        if (priceObj != null) {
                            pizzaPrice = ((Number) priceObj).doubleValue();
                        }

                        Object discountObj = data.get("pizzaDiscount");
                        if (discountObj != null) {
                            pizzaDiscount = ((Number) discountObj).doubleValue();
                        }

                        Product pizza = new Product();
                        pizza.setPizzaName(pizzaName);
                        pizza.setPizzaPrice(pizzaPrice);
                        pizza.setPizzaDiscount(pizzaDiscount);
                        pizzaMenuList.add(pizza);
                    }
                    onFirebaseDataRetrieved();
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                    Toast.makeText(ActivityMenuList.this, "Error retrieving pizza menu data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
