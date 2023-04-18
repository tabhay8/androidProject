package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private List<Product> cartItems;



    private RemoveButtonListener removeButtonListener;

    public CartItemAdapter( List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
        Product pizza = cartItems.get(position);

        holder.tvCartItemName.setText(pizza.getPizzaName());
        holder.tvCartItemPrice.setText("Price: $ " + pizza.getPizzaPrice());
        holder.tvCartItemQuantity.setText("Quantity: " + pizza.getCartQuantity());
        holder.tvCartItemRemoveButton.setOnClickListener(view -> removeButtonListener.onRemoveButtonClicked(pizza));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void setRemoveButtonListener(RemoveButtonListener removeButtonListener) {
        this.removeButtonListener = removeButtonListener;
    }

    interface RemoveButtonListener{
        void onRemoveButtonClicked(Product pizza);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCartItemName;
        private final TextView tvCartItemPrice;
        private final TextView tvCartItemQuantity;
        private final Button tvCartItemRemoveButton;

        public TextView getTvCartItemName() {
            return tvCartItemName;
        }

        public TextView getTvCartItemPrice() {
            return tvCartItemPrice;
        }

        public TextView getTvCartItemQuantity() {
            return tvCartItemQuantity;
        }

        public Button getTvCartItemRemoveButton() {
            return tvCartItemRemoveButton;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartItemName = itemView.findViewById(R.id.cart_item_product_name);
            tvCartItemPrice = itemView.findViewById(R.id.cart_item_product_price);
            tvCartItemQuantity = itemView.findViewById(R.id.cart_item_product_quantity);
            tvCartItemRemoveButton = itemView.findViewById(R.id.cart_item_remove_button);
        }
    }
}
