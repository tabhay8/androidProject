package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PizzaMenuAdapter extends RecyclerView.Adapter<PizzaMenuAdapter.ViewHolder> {

    private final Context mContext;
    private List<Product> pizzaMenuList;
    private OnPizzaItemClickListener onPizzaItemClickListener;

    public PizzaMenuAdapter(Context context, List<Product> pizzaMenuList){
        this.pizzaMenuList = pizzaMenuList;
        this.mContext = context;
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

        Glide.with(mContext)
                .load(pizzaMenuList.get(position).getImageURL())
                .error(R.drawable.pizza_2)
                .into(holder.getIvPizzaListImage());

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
        private final ImageView ivPizzaListImage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductDiscount = (TextView) itemView.findViewById(R.id.tvProductDiscount);
            cvProductView = (CardView) itemView.findViewById(R.id.cvProduct1);
            ivPizzaListImage = (ImageView) itemView.findViewById(R.id.ivProductImage);

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

        public ImageView getIvPizzaListImage() {
            return ivPizzaListImage;
        }
    }

    public interface OnPizzaItemClickListener{
        void onPizzaItemIsClicked(int position);
    }
}
