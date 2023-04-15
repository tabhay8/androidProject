package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuListFragment extends Fragment {
    public static final String TAG = "MenuListFragment";

    private static final String ARG_PIZZA_LIST = "ARG_PIZZA_LIST";
    private List<Product> pizzaMenuList;
    protected RecyclerView pizzaMenuRecyclerView;
    protected RecyclerView.LayoutManager pizzaMenuRecyclerViewLayoutManager;

    public MenuListFragment() {
        // Required empty public constructor
    }

    public static MenuListFragment newInstance(ArrayList<Product> pizzaMenuList) {
        MenuListFragment fragment = new MenuListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PIZZA_LIST, pizzaMenuList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            pizzaMenuList = getArguments().getParcelableArrayList(ARG_PIZZA_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        pizzaMenuRecyclerView = view.findViewById(R.id.pizza_menu_recycler_view);
        pizzaMenuRecyclerView.setHasFixedSize(true);
        pizzaMenuRecyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        pizzaMenuRecyclerView.setLayoutManager(pizzaMenuRecyclerViewLayoutManager);
        pizzaMenuRecyclerView.setAdapter(new PizzaMenuAdapter(pizzaMenuList));

        // Inflate the layout for this fragment
        return view;
    }
}