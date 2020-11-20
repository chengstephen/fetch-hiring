package com.example.fetchrewardscodingexercise.dagger

import com.example.fetchrewardscodingexercise.networking.CandidateNetworking
import com.example.fetchrewardscodingexercise.networking.CandidateNetworkingImpl
import com.example.fetchrewardscodingexercise.retrofit.RetrofitApiModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [RetrofitApiModule::class])
interface NetworkingModule {

   @Binds
   @Singleton
   fun bindCandidateNetworking(impl: CandidateNetworkingImpl): CandidateNetworking
}