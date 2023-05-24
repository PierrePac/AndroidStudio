package com.example.greenvillage.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenvillage.R;
import com.example.greenvillage.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    private  List<Product> productList;

    public ProductsListAdapter(List<Product> productList) {
        this.productList = productList != null ? productList : new ArrayList<>();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList != null ? productList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductsViewHolder(view, productList); // Pass productList to the constructor
    }


    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = productList.get(position);
        Log.d("ProductSlug", product.getSlug());
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

