package com.example.greenvillage.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // Get the request
        Request request = chain.request();

        // Modify the request (add Java only)
        Request modifiedRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer my_token")
                .build();

        // Proceed with the modified request
        Response response = chain.proceed(modifiedRequest);

        //Modify response (if needed)
        Response modifyResponse = response.newBuilder()
                .removeHeader("Expires")
                .build();

        return modifyResponse;
    }
}
