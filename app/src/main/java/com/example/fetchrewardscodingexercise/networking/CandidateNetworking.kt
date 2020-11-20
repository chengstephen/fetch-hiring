package com.example.fetchrewardscodingexercise.networking

import com.example.fetchrewardscodingexercise.api.FetchHiringApi
import com.example.fetchrewardscodingexercise.model.Candidate
import io.reactivex.Observable
import javax.inject.Inject

interface CandidateNetworking {
   fun getCandidateList(jsonFileName: String): Observable<List<Candidate>>
}

class CandidateNetworkingImpl @Inject constructor(
   private val fetchHiringApi: FetchHiringApi
): CandidateNetworking {

   override fun getCandidateList(jsonFileName: String): Observable<List<Candidate>> {
      return fetchHiringApi.getJson(jsonFileName)
   }
}