package com.example.fetchrewardscodingexercise.dagger

import android.app.Application
import androidx.room.Room
import com.example.fetchrewardscodingexercise.data.CandidateDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

   private val candidateDatabase: CandidateDatabase =
      Room.databaseBuilder(
         application.applicationContext,
         CandidateDatabase::class.java,
         "Candidate.db"
      ).build()

   @Singleton
   @Provides
   fun providesCandidateDatabase() = candidateDatabase

   @Singleton
   @Provides
   fun providesCandidateDao(candidateDatabase: CandidateDatabase) =
      candidateDatabase.candidateDao()
}