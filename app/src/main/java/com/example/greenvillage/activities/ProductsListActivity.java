package com.example.greenvillage.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.greenvillage.R;
import com.example.greenvillage.adapters.ProductsListAdapter;
import com.example.greenvillage.models.Product;
import com.example.greenvillage.network.GameCatalogApi;
import com.example.greenvillage.network.GameCatalogService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsListActivity extends AppCompatActivity {

    private GameCatalogApi gameCatalogApi;
    private RecyclerView recyclerView;
    private ProductsListAdapter productsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set the title of the toolbar
        getSupportActionBar().setTitle("");

        // Initialize the GameCatalogApi instance
        gameCatalogApi = GameCatalogService.getGameCatalogApi();

        recyclerView = findViewById(R.id.recycler_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsListAdapter = new ProductsListAdapter(new ArrayList<>());
        recyclerView.setAdapter(productsListAdapter);

        // Retrieve the slug from the intent extras
        String slug = getIntent().getStringExtra("subcategory_slug");

        //make the API call to get the list of products
        getProductsList(slug);
    }

    private void getProductsList(String slug) {
        Call<List<Product>> call = gameCatalogApi.getProducts(slug);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    List<Product> productList = response.body();
                    if(productList != null) {
                        productsListAdapter = new ProductsListAdapter(productList);
                        recyclerView.setAdapter(productsListAdapter);
                    }
                } else {
                    // Handle the case when the API call returns an error response
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle the failure
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}