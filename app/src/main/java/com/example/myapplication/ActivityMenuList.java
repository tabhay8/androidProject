package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class ActivityMenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        // Initialize the Firestore database reference
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference menuItemsCollection = db.collection("menuItems");

        // Example data to add to Firestore
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("name", "Burger");
        menuItem1.put("price", 10.0);

        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("name", "Pizza");
        menuItem2.put("price", 15.0);

        Map<String, Object> menuItem3 = new HashMap<>();
        menuItem3.put("name", "Fries");
        menuItem3.put("price", 5.0);

        // Use a batched write to add multiple documents to Firestore
        WriteBatch batch = db.batch();
        DocumentReference docRef1 = menuItemsCollection.document("menuItem1");
        batch.set(docRef1, menuItem1);

        DocumentReference docRef2 = menuItemsCollection.document("menuItem2");
        batch.set(docRef2, menuItem2);

        DocumentReference docRef3 = menuItemsCollection.document("menuItem3");
        batch.set(docRef3, menuItem3);

        batch.commit()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ActivityMenuList.this, "Data added to Firestore", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActivityMenuList.this, "Failed to add data to Firestore", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
