package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PizzaDetailFragment extends Fragment {

    private static final String ARG_PIZZA_DETAIL = "ARG_PIZZA_DETAIL";
    private static final String TAG = "PizzaDetailFragment";
    private Product pizza;
    private TextView tvPizzaName;
    private TextView tvProductDescriptionLine1;
    private TextView tvProductDescriptionLine2;
    private TextView tvProductDiscount;
    private TextView tvProductPrice;
    private Button btnAddToCart;
    private ImageView ivProductImage;

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
        View view = inflater.inflate(R.layout.fragment_pizza_detail, container, false);

        tvPizzaName = (TextView) view.findViewById(R.id.tvProductDetailName);
        tvProductDiscount = (TextView) view.findViewById(R.id.tvProductDetailDiscount);
        tvProductPrice = (TextView) view.findViewById(R.id.tvProductDetailPrice);
        btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);
        ivProductImage =  (ImageView) view.findViewById(R.id.ivProductImage);

        // TODO: 15-04-2023 Set values dynamically for following views.
        tvProductDescriptionLine1 = (TextView) view.findViewById(R.id.tvProductDescriptionPara1);
        tvProductDescriptionLine2 = (TextView) view.findViewById(R.id.tvProductDescriptionPara2);
        // END

        tvPizzaName.setText(pizza.getPizzaName());
        tvProductDiscount.setText("Discount: " + pizza.getPizzaDiscount() + "%");
        tvProductPrice.setText("Price: $" + pizza.getPizzaPrice());
        tvProductDescriptionLine1.setText(pizza.getDescription());
        tvProductDescriptionLine2.setText(pizza.getIngredients());
        btnAddToCart.setOnClickListener(view1 -> {
            Log.i(TAG, "onCreateView: Add to Cart button clicked.");
        });
        Glide.with(view)
                .load(pizza.getImageURL())
                .error(R.drawable.pizza_2)
                .into(ivProductImage);

        // Inflate the layout for this fragment
        return view;
    }
}