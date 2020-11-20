package com.example.fetchrewardscodingexercise.dagger

import android.app.Application
import com.example.fetchrewardscodingexercise.service.CandidateService
import dagger.Component
import javax.inject.Singleton

@Singleton
@ServicesScope
@Component(modules = [ServicesModule::class, RoomModule::class])
abstract class ServicesComponent {

   companion object {
      private lateinit var INSTANCE: ServicesComponent

      fun get(application: Application): ServicesComponent {
         if (!this::INSTANCE.isInitialized) {
            INSTANCE = DaggerServicesComponent
               .builder()
               .roomModule(RoomModule(application))
               .build()
         }
         return INSTANCE
      }
   }

   abstract fun candidateService(): CandidateService
}