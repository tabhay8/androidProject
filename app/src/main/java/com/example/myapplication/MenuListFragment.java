package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuListFragment extends Fragment implements PizzaMenuAdapter.OnPizzaItemClickListener {
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

        // Hide the ActionBar
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Set the Window fullscreen
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Define a new divider drawable
        Drawable dividerDrawable = ContextCompat.getDrawable(view.getContext(), R.drawable.recycler_view_divider);

        // Create a new DividerItemDecoration with the divider drawable
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(dividerDrawable);

        // Add the divider to your RecyclerView
        pizzaMenuRecyclerView.addItemDecoration(dividerItemDecoration);

        PizzaMenuAdapter adapter = new PizzaMenuAdapter(view.getContext(), pizzaMenuList);
        adapter.setOnPizzaItemClickListener(this);
        pizzaMenuRecyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onPizzaItemIsClicked(int position) {
        // open pizza detail fragment
        Log.i(TAG, "A pizza was clicked: " + pizzaMenuList.get(position).getPizzaName());
        FragmentManager manager = getParentFragmentManager();
        manager.beginTransaction()
                .add(R.id.main_fragment_container, PizzaDetailFragment.newInstance(pizzaMenuList.get(position)))
                .addToBackStack(null)
                .commit();
    }

}