package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaMenuAdapter extends RecyclerView.Adapter<PizzaMenuAdapter.ViewHolder> {

    private List<Product> pizzaMenuList;

    public PizzaMenuAdapter(List<Product> pizzaMenuList){
        this.pizzaMenuList = pizzaMenuList;
    }

    @NonNull
    @Override
    public PizzaMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaMenuAdapter.ViewHolder holder, int position) {
        holder.getTvProductName().setText(pizzaMenuList.get(position).getPizzaName());
        holder.getTvProductPrice().setText(String.valueOf(pizzaMenuList.get(position).getPizzaPrice()));
        holder.getTvProductDiscount().setText(String.valueOf(pizzaMenuList.get(position).getPizzaDiscount()));
    }

    @Override
    public int getItemCount() {
        return pizzaMenuList.size();
    }

    public void setPizzaMenuList(List<Product> pizzaMenuList) {
        this.pizzaMenuList = pizzaMenuList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProductName;
        private final TextView tvProductPrice;
        private final TextView tvProductDiscount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductDiscount = (TextView) itemView.findViewById(R.id.tvProductDiscount);
        }

        public TextView getTvProductName() {
            return tvProductName;
        }

        public TextView getTvProductPrice() {
            return tvProductPrice;
        }

        public TextView getTvProductDiscount() {
            return tvProductDiscount;
        }
    }
}
