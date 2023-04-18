package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartItemsFragment extends Fragment implements CartItemAdapter.RemoveButtonListener {
    private static final String ARG_CART_ITEMS = "CART_ITEMS";
    private static final String TAG = "CartItemsFragment";
    protected RecyclerView cartItemsRecyclerView;
    protected RecyclerView.LayoutManager cartItemsLayoutManager;
    private List<Product> cartItems;
    private Callback callback;
    private CartItemAdapter adapter;
    private Button checkoutButton;
    private double orderTotal;

    public CartItemsFragment() {}

    public static CartItemsFragment newInstance(ArrayList<Product> cartItems) {
        CartItemsFragment fragment = new CartItemsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CART_ITEMS, cartItems);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            cartItems = getArguments().getParcelableArrayList(ARG_CART_ITEMS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_items, container, false);

        checkoutButton = view.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(view1 -> callback.onCheckoutButtonPressed());

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recycler_view);
        cartItemsRecyclerView.setHasFixedSize(false);
        cartItemsLayoutManager = new LinearLayoutManager(view.getContext());
        cartItemsRecyclerView.setLayoutManager(cartItemsLayoutManager);

        adapter = new CartItemAdapter(cartItems);
        adapter.setRemoveButtonListener(this);
        cartItemsRecyclerView.setAdapter(adapter);

        orderTotal = getCartItemTotal(cartItems);
        checkoutButton.setText("CHECKOUT: $ " + orderTotal);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CartItemsFragment.Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context + " must implement `CartItemsFragment.Callback`");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onRemoveButtonClicked(Product pizza) {
        callback.onCartItemRemovePressed(pizza);
        adapter.notifyDataSetChanged();

        orderTotal = getCartItemTotal(cartItems);
        checkoutButton.setText(String.format("CHECKOUT: $ %.2f", orderTotal));
    }

    interface Callback {
        void onCartItemRemovePressed(Product pizza);

        void onCheckoutButtonPressed();
    }

    public double getCartItemTotal(List<Product> cartItems) {
        double totalPrice = 0;
        for (Product pizza : cartItems) {
            double price = pizza.getPizzaPrice();
            int quantity = pizza.getCartQuantity();
            double discount = pizza.getPizzaDiscount();
            double discountedPrice = price - price * discount / 100;
            totalPrice += discountedPrice * quantity;
            Log.i(TAG, "getCartItemTotal: price: " + price + ", quantity: " + quantity + ", discount: " + discount + ", discountPrice: " + discountedPrice + ", totalPrice: " + totalPrice);
        }
        return totalPrice;
    }
}
