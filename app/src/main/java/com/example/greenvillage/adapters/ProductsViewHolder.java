package com.example.greenvillage.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenvillage.R;
import com.example.greenvillage.activities.ProductDetailActivity;
import com.example.greenvillage.models.Image;
import com.example.greenvillage.models.Product;
import com.example.greenvillage.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Tag;

public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private Context context;
    private List<Product> productList;

    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        productImage = itemView.findViewById(R.id.image_product);
        productName = itemView.findViewById(R.id.text_product_name);
        productPrice = itemView.findViewById(R.id.text_product_price);
        itemView.setOnClickListener(this);
    }

    public ProductsViewHolder(@NonNull View itemview, List<Product> productList) {
        super(itemview);
        this.productList = productList;
        productImage = itemView.findViewById(R.id.image_product);
        productName = itemView.findViewById(R.id.text_product_name);
        productPrice = itemView.findViewById(R.id.text_product_price);
        itemView.setOnClickListener(this);
    }


    public void bind(Product product) {
        // Call the method to fetch the image url based on the Product ID
        fetchImageURL(product.getId());

        productName.setText(product.getShortLibel());
        productPrice.setText(product.getPrixHt() + "0 €");
    }

    private void fetchImageURL(int productId) {
        OkHttpClient client = new OkHttpClient();

        String url = Constants.BASE_URL_IMG_PRINCIPAL + productId;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //Handle the request failure
                e.fillInStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String json = responseBody.string();
                        // Parse the Json response and extract the "src" field
                        Gson gson = new Gson();
                        Image image = gson.fromJson(json, Image.class);
                        String imageUrl = Constants.BASE_URL_IMG + image.getSrc();

                        // Update the UI on the main thread
                        productImage.post(() -> {
                            //Set the product image using Glide
                            Glide.with(itemView.getContext())
                                    .load(imageUrl)
                                    .into(productImage);
                        });
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
            Product product = productList.get(position);

            // Start the PoductDetailActivity and pass the product slug as extra
            Intent intent = new Intent(itemView.getContext(), ProductDetailActivity.class);
            intent.putExtra("productSlug", product.getSlug());
            itemView.getContext().startActivity(intent);
        }

    }
}
