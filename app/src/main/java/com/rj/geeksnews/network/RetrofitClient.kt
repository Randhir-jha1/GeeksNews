package com.rj.geeksnews.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    object RetrofitInstance{
        private var retrofit: Retrofit?=null
        private const val BASE_URL="https://newsapi.org/v2/"

        @JvmStatic
        fun getClient():Retrofit?{
            val logging= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val builder = OkHttpClient.Builder()
            builder.connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(90,TimeUnit.SECONDS)
                .addInterceptor(logging)


            val  okHttpClient = builder.build()
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
    }
}