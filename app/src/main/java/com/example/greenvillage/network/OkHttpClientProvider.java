package com.example.greenvillage.network;

import okhttp3.OkHttpClient;

public class OkHttpClientProvider {

    private static OkHttpClient client;

    public static OkHttpClient getClient() {
        if(client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            // Add the interceptor
            builder.addInterceptor(new MyInterceptor());

            client = builder.build();
        }
        return client;
    }
}
