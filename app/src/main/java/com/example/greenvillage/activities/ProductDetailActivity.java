package com.example.greenvillage.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.greenvillage.R;
import com.example.greenvillage.adapters.ImageAdapter;
import com.example.greenvillage.models.Image;
import com.example.greenvillage.models.Product;
import com.example.greenvillage.network.GameCatalogApi;
import com.example.greenvillage.network.GameCatalogService;
import com.example.greenvillage.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView shortLibelTextView;
    private TextView longLibelTextView;
    private TextView prixHtTextView;

    private GameCatalogApi gameCatalogApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set the title of the toolbar
        getSupportActionBar().setTitle("");

        //Initialize the GameCatalogApi instance
        gameCatalogApi = GameCatalogService.getGameCatalogApi();

        // Get the product slug from the intent extras
        Intent intent = getIntent();
        String productSlug = intent.getStringExtra("productSlug");

        // Initialize views
        productImageView = findViewById(R.id.image_product);
        shortLibelTextView = findViewById(R.id.text_short_libel);
        longLibelTextView = findViewById(R.id.text_long_libel);
        prixHtTextView = findViewById(R.id.text_prix_ht);

        // fetch product details
        fetchProductDetails(productSlug);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchProductDetails(String productSlug) {
        String productUrl = Constants.BASE_URL_PROD + productSlug;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(productUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle failure
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String json = responseBody.string();
                        Gson gson = new Gson();
                        Product product = gson.fromJson(json, Product.class);

                        // Fetch and display product images
                        fetchProductImages(product.getId());

                        // Update UI on the main Thread
                        runOnUiThread(() -> {
                            shortLibelTextView.setText(product.getShortLibel());
                            longLibelTextView.setText(product.getLongLibel());
                            prixHtTextView.setText(product.getPrixHt() + "0 €");
                        });
                    }
                }
            }
        });
    }

    private void fetchProductImages(int productId) {
        String imagesUrl = Constants.BASE_URL_IMAGES + productId;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(imagesUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle failure
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String json = responseBody.string();
                        Gson gson = new Gson();
                        Image[] images = gson.fromJson(json, Image[].class);

                        //Update UI on the main thread
                        runOnUiThread(() -> {
                            //Display images using ViewPager2 and custom adapter
                            if(images.length > 0) {
                                ViewPager2 viewPager = findViewById(R.id.image_pager);
                                ImageAdapter imageAdapter = new ImageAdapter(Arrays.asList(images), ProductDetailActivity.this);
                                viewPager.setAdapter(imageAdapter);
                            }
                        });
                    }
                }
            }
        });
    }
}
