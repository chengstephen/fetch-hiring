package com.example.fetchrewardscodingexercise.retrofit

import com.example.fetchrewardscodingexercise.api.FetchHiringApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitApiModule {

   companion object {
      private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
   }

   @Provides
   @Singleton
   fun retrofit(): Retrofit {
      val httpLoggingInterceptor =
         HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
      val httpClientBuilder = OkHttpClient.Builder().build().newBuilder()
      val httpClient = httpClientBuilder.addInterceptor { chain ->
         val originalRequest = chain.request()

         val url = originalRequest
            .url
            .newBuilder()
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .build();

         val request = originalRequest.newBuilder().url(url).build()
         chain.proceed(request)
      }.addInterceptor(httpLoggingInterceptor).build()

      return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
         .client(httpClient)
         .baseUrl(BASE_URL)
         .build()
   }

   @Provides
   @Singleton
   fun fetchHiringApi(retrofit: Retrofit) = retrofit.create(FetchHiringApi::class.java)
}