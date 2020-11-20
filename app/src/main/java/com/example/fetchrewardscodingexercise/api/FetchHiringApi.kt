package com.example.fetchrewardscodingexercise.api

import com.example.fetchrewardscodingexercise.model.Candidate
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FetchHiringApi {

   @Headers("Content-Type:application/json")
   @GET("{fileName}")
   fun getJson(@Path("fileName") jsonFileName: String): Observable<List<Candidate>>
}

