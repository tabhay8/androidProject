package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {


    private String imageURL;
    private String pizzaName;
    private double pizzaPrice;
    private double pizzaDiscount;

    private String description;

    private String ingredients;

    protected Product(Parcel in) {
        pizzaName = in.readString();
        pizzaPrice = in.readDouble();
        pizzaDiscount = in.readDouble();
        imageURL = in.readString();
        description = in.readString();
        ingredients = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>(){

        @Override
        public Product createFromParcel(Parcel parcel) {
            return new Product(parcel);
        }

        @Override
        public Product[] newArray(int i) {
            return new Product[i];
        }
    };

    public Product() {

    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public double getPizzaDiscount() {
        return pizzaDiscount;
    }

    public void setPizzaDiscount(double pizzaDiscount) {
        this.pizzaDiscount = pizzaDiscount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(pizzaName);
        parcel.writeDouble(pizzaPrice);
        parcel.writeDouble(pizzaDiscount);
        parcel.writeString(imageURL);
        parcel.writeString(description);
        parcel.writeString(ingredients);
    }
}
