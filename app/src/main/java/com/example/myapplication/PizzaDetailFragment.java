package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PizzaDetailFragment extends Fragment {

    private static final String ARG_PIZZA_DETAIL = "ARG_PIZZA_DETAIL";
    private Product pizza;

    public PizzaDetailFragment() {
        // Required empty public constructor
    }

    public static PizzaDetailFragment newInstance(Product pizzaDetail) {
        PizzaDetailFragment fragment = new PizzaDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PIZZA_DETAIL, pizzaDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pizza = getArguments().getParcelable(ARG_PIZZA_DETAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pizza_detail, container, false);
    }
}