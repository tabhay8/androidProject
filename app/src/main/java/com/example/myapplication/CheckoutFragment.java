package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    private Button placeOrderButton;
    private Callback callback;

    public CheckoutFragment() {

    }

    public static CheckoutFragment newInstance() {
        CheckoutFragment fragment = new CheckoutFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_checkout_form, container, false);
        placeOrderButton = (Button) view.findViewById(R.id.bthPlaceOrder);
        placeOrderButton.setOnClickListener(v -> callback.onOrderPlaced());
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CheckoutFragment.Callback) {
            callback = (CheckoutFragment.Callback) context;
        } else {
            throw new RuntimeException(context + " must implement `CheckoutFragment.Callback`");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    interface Callback {
        void onOrderPlaced();
    }
}
