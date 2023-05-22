package com.example.greenvillage.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.greenvillage.R;
import com.example.greenvillage.adapters.CategoriesAdapter;
import com.example.greenvillage.models.Category;
import com.example.greenvillage.network.GameCatalogApi;
import com.example.greenvillage.network.GameCatalogService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.greenvillage.activities.MESSAGE";
    private GameCatalogApi gameCatalogApi;
    private RecyclerView recyclerView;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize the GameCatalogApi instance
        gameCatalogApi = GameCatalogService.getGameCatalogApi();

        recyclerView = findViewById(R.id.recycler_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesAdapter = new CategoriesAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(categoriesAdapter);

        // Set the click listener for the categories
        categoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                String slug = category.getSlug();
                // Start the SubCategoryActivity and pass the slug
                Intent intent = new Intent(CategoryActivity.this, SubCategoryActivity.class);
                intent.putExtra(EXTRA_MESSAGE, slug);
                startActivity(intent);
            }
        });

        // Make the API call
        getCategories();

    }

    private void getCategories(){
        Call<List<Category>> call = gameCatalogApi.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if(response.isSuccessful()){
                    List<Category> categories = response.body();
                    categoriesAdapter.setCategories(categories);
                } else {
                    // Response is not successful
                    Log.d("Fail", " response unsecceful");
                    // Handle the case when the API call returns an error response
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                // Handle the failure
                Log.d("Fail", "You failed");
                // This method will be called if there is a network failure or an exception during the API call
            }
        });
    }
}