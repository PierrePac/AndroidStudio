package com.example.greenvillage.adapters;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenvillage.R;
import com.example.greenvillage.models.Product;
import com.example.greenvillage.utils.Constants;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;

    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.image_product);
        productName = itemView.findViewById(R.id.text_product_name);
        productPrice = itemView.findViewById(R.id.text_product_price);
    }

    public void bind(Product product) {
        // Set the product image using Glide
        Glide.with(itemView.getContext())
                .load(Constants.BASE_URL_IMG_PRINCIPAL + product.getId())
                .into(productImage);
        productName.setText(product.getShortLibel());
        productPrice.setText(product.getPrixHt() + "€");
    }
}
