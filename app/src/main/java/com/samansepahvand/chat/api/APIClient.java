package com.samansepahvand.chat.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    APIInterface apiService;

    private static Retrofit retrofit = null;
    private  static  final String BASE_URL="http://192.168.42.97:52028/api/";

    private  static  final String BASE_URL1="http://10.0.2.2:52028/api/";
    public  static  Retrofit  getClient() {

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request orginal=chain.request();
                        Request.Builder requestbuilder=orginal.newBuilder()
                                .header("Host","localhost")
                                .method(orginal.method(),orginal.body());

                        Request request=requestbuilder.build();

                        return chain.proceed(request);
                    }
                })
                .build();


         retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

       return   retrofit;


    }



}
