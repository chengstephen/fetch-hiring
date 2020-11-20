package com.example.fetchrewardscodingexercise.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fetchrewardscodingexercise.model.Candidate

@Database(entities = [Candidate::class], version = 1)
abstract class CandidateDatabase : RoomDatabase() {

   abstract fun candidateDao(): CandidateDao
}