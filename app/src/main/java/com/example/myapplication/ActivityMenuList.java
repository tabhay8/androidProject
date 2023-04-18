package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityMenuList extends AppCompatActivity implements PizzaDetailFragment.Callback, CartItemsFragment.Callback, CheckoutFragment.Callback {

    private static final String TAG = "ActivityMenuList";
    private static final int MAX_ORDER_SIZE = 20;

    protected List<Product> pizzaMenuList;

    private FirebaseFirestore db;
    private Toolbar mainToolbar;
    private List<Product> productsInCart;
    private MenuListFragment menuListFragment;
    private CartItemsFragment cartItemsFragment;
    private CheckoutFragment checkoutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        mainToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mainToolbar);

        productsInCart = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        retrievePizzaMenuFromFirestore();
    }

    private void onFirebaseDataRetrieved(){
        menuListFragment = MenuListFragment.newInstance((ArrayList<Product>) pizzaMenuList);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, menuListFragment)
                .commit();
    }

    private void retrievePizzaMenuFromFirestore() {
        pizzaMenuList = new ArrayList<>();

        CollectionReference pizzaMenuRef = db.collection("pizza_menu");

        pizzaMenuRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    Map<String, Object> data = document.getData();
                    String pizzaName = (String) data.get("pizzaName");
                    String imageUrl = (String) data.get("imageUrl");
                    String description = (String)  data.get("description");
                    String ingredients = (String)  data.get("ingredients");

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
                    pizza.setImageURL(imageUrl);
                    pizza.setDescription(description);
                    pizza.setIngredients(ingredients);
                    pizzaMenuList.add(pizza);
                }
                onFirebaseDataRetrieved();
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
                Toast.makeText(ActivityMenuList.this, "Error retrieving pizza menu data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected: called.");
        cartItemsFragment = CartItemsFragment.newInstance((ArrayList<Product>) productsInCart);
        if (item.getItemId() == R.id.action_cart) {
            Log.i(TAG, "onOptionsItemSelected: Cart icon is pressed.");
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_fragment_container, cartItemsFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddToCartClicked(Product pizza) {
        Log.i(TAG, "onAddToCartClicked: Add to cart tapped.");

        int countOrderSize = 0;
        for (Product pizzaType : productsInCart) {
            countOrderSize += pizzaType.getCartQuantity();
        }
        if (countOrderSize < MAX_ORDER_SIZE) {
            if (productsInCart.contains(pizza)) {
                int cartQuantity = pizza.getCartQuantity();
                pizza.setCartQuantity(cartQuantity + 1);
            } else {
                pizza.setCartQuantity(1);
                productsInCart.add(pizza);
            }
        } else {
            String msg = "Maximum order size of " + MAX_ORDER_SIZE + " is reached.";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "onAddToCartClicked: Pizza Name: " + pizza.getPizzaName() + ", Cart Quantity: " + pizza.getCartQuantity());
    }

    @Override
    public void onCartItemRemovePressed(Product pizza) {
        Log.i(TAG, "onCartItemRemovePressed: The item remove button is pressed.");
        int originalItemQuantity = pizza.getCartQuantity();
        int newItemQuantity = originalItemQuantity - 1;
        pizza.setCartQuantity(newItemQuantity);
        if (newItemQuantity <= 0) {
            productsInCart.remove(pizza);
        }
        Log.i(TAG, "onAddToCartClicked: Pizza Name: " + pizza.getPizzaName() + ", Cart Quantity: " + pizza.getCartQuantity());
    }

    @Override
    public void onCheckoutButtonPressed() {
        if (productsInCart.size() > 0){
            checkoutFragment = CheckoutFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.main_fragment_container, checkoutFragment)
                    .commit();
        } else {
            Toast.makeText(this, "No items added to the cart.", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onOrderPlaced() {
        Log.i(TAG, "onOrderPlaced: Order place button is pressed.");

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().remove(checkoutFragment).commit();
//        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, menuListFragment).commit();

        productsInCart = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Placed!");
        builder.setMessage("Your order has been placed. Thank you for your purchase!");
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
