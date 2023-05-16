package com.example.greenvillage.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameCatalogService {

    private static final String BASE_URL = "https://pierrep.amorce.org/";

    private static GameCatalogApi gameCatalogApi;

    public static GameCatalogApi getGameCatalogApi() {
        if (gameCatalogApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClientProvider.getClient())
                    .build();
            gameCatalogApi = retrofit.create(GameCatalogApi.class);
        }
        return gameCatalogApi;
    }
}
