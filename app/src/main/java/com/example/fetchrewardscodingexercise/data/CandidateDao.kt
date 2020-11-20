package com.example.fetchrewardscodingexercise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fetchrewardscodingexercise.model.Candidate
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface CandidateDao {

   @Query("SELECT * FROM candidates WHERE id = :id")
   fun getCandidateById(id: Int): Maybe<Candidate>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveCandidate(candidate: Candidate): Completable

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun saveCandidateList(candidateList: List<Candidate>): Completable

   @Query("DELETE FROM candidates")
   fun deleteAllCandidates()
}