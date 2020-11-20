package com.example.fetchrewardscodingexercise.dagger

import com.example.fetchrewardscodingexercise.service.CandidateService
import com.example.fetchrewardscodingexercise.service.CandidateServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkingModule::class])
interface ServicesModule {

   @Binds
   @Singleton
   fun bindCandidateService(impl: CandidateServiceImpl): CandidateService
}