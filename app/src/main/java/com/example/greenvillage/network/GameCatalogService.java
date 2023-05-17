package com.example.greenvillage.network;

import com.example.greenvillage.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameCatalogService {

    private static GameCatalogApi gameCatalogApi;

    public static GameCatalogApi getGameCatalogApi() {
        if (gameCatalogApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClientProvider.getClient())
                    .build();
            gameCatalogApi = retrofit.create(GameCatalogApi.class);
        }
        return gameCatalogApi;
    }
}
