package com.example.fetchrewardscodingexercise.service

import com.example.fetchrewardscodingexercise.data.CandidateDao
import com.example.fetchrewardscodingexercise.model.Candidate
import com.example.fetchrewardscodingexercise.networking.CandidateNetworking
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CandidateService {
   fun getCandidateList(jsonFileName: String): Observable<List<Candidate>>
}

class CandidateServiceImpl @Inject constructor(
   private val candidateNetworking: CandidateNetworking,
   private val candidateDao: CandidateDao
): CandidateService {

   override fun getCandidateList(jsonFileName: String): Observable<List<Candidate>> {
      return candidateNetworking.getCandidateList(jsonFileName)
         .flatMapSingle { candidateDao.saveCandidateList(it).andThen(Single.just(it)) }
         .subscribeOn(Schedulers.computation())
   }
}