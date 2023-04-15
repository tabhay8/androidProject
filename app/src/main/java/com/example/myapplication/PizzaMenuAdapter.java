package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaMenuAdapter extends RecyclerView.Adapter<PizzaMenuAdapter.ViewHolder> {

    private List<Product> pizzaMenuList;
    private OnPizzaItemClickListener onPizzaItemClickListener;

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

        double pizzaPrice = pizzaMenuList.get(position).getPizzaPrice();
        if (Double.isNaN(pizzaPrice)) {
            holder.getTvProductPrice().setText("Price: N/A");
        } else {
            holder.getTvProductPrice().setText("Price: $" + pizzaPrice);
        }

        double pizzaDiscount = pizzaMenuList.get(position).getPizzaDiscount();
        if (Double.isNaN(pizzaDiscount)) {
            holder.getTvProductDiscount().setText("Discount: N/A");
        } else {
            holder.getTvProductDiscount().setText("Discount: " + pizzaDiscount);
        }

        holder.getCvProductView().setOnClickListener(view -> {
            onPizzaItemClickListener.onPizzaItemIsClicked(position);
        });
    }


    @Override
    public int getItemCount() {
        return pizzaMenuList.size();
    }

    public void setPizzaMenuList(List<Product> pizzaMenuList) {
        this.pizzaMenuList = pizzaMenuList;
    }

    public void setOnPizzaItemClickListener(OnPizzaItemClickListener listener) {
        this.onPizzaItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProductName;
        private final TextView tvProductPrice;
        private final TextView tvProductDiscount;
        private final CardView cvProductView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductDiscount = (TextView) itemView.findViewById(R.id.tvProductDiscount);
            cvProductView = (CardView) itemView.findViewById(R.id.cvProduct1);
        }

        public CardView getCvProductView() { return cvProductView; }

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

    public interface OnPizzaItemClickListener{
        void onPizzaItemIsClicked(int position);
    }
}
