package com.example.greenvillage.network;

import com.example.greenvillage.models.Category;
import com.example.greenvillage.models.Product;
import com.example.greenvillage.models.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameCatalogApi {

    @GET("categories")
    Call<List<Category>> getCategories();

    @GET("cat/{categorySlug}")
    Call<List<SubCategory>> getSubCategories(@Path("categorySlug") String categorySlug);

    @GET("subcategories/{subCategoryId}/products")
    Call<List<Product>> getProducts(@Path("subCategoryId") String subCategoryId);

    @GET("products/{productId}")
    Call<Product> getProductDetail(@Path("productId") String productId);
}