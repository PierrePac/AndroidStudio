package com.example.greenvillage.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.greenvillage.R;
import com.example.greenvillage.adapters.SubCategoriesAdapter;
import com.example.greenvillage.models.SubCategory;
import com.example.greenvillage.network.GameCatalogApi;
import com.example.greenvillage.network.GameCatalogService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {

    private GameCatalogApi gameCatalogApi;
    private RecyclerView recyclerView;
    private SubCategoriesAdapter subCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        // Initialize the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set the title of the toolbar
        getSupportActionBar().setTitle("");

        // Enable the back button in the toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Initialize the GameCatalogApi instance
        gameCatalogApi = GameCatalogService.getGameCatalogApi();

        recyclerView = findViewById(R.id.recycler_sub_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subCategoriesAdapter = new SubCategoriesAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(subCategoriesAdapter);

        // Retrieve the slug from the intent extras
        String slug = getIntent().getStringExtra(CategoryActivity.EXTRA_MESSAGE);

        // Make the API call to get the subcategories
        getSubCategories(slug);
    }

    private void getSubCategories(String slug) {
        Call<List<SubCategory>> call = gameCatalogApi.getSubCategories(slug);
        call.enqueue(new Callback<List<SubCategory>>() {
            @Override
            public void onResponse(@NonNull Call<List<SubCategory>> call, @NonNull Response<List<SubCategory>> response) {
                if (response.isSuccessful()) {
                    List<SubCategory> subCategories = response.body();
                    subCategoriesAdapter.setSubCategories(subCategories);
                } else {
                    // Response is not successful
                    Log.d("Fail", "Response unsuccessful");
                    // Handle the case when the API call returns an error response
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SubCategory>> call, @NonNull Throwable t) {
                // Handle the failure
                Log.d("Fail", "API call failed");
                // This method will be called if there is a network failure or an exception during the API call
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
