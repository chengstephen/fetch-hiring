package com.example.fetchrewardscodingexercise.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "candidates")
data class Candidate(
   @PrimaryKey(autoGenerate = false) val id: Int,
   val listId: Int,
   val name: String?,
) : Comparable<Candidate> {
   override fun compareTo(other: Candidate): Int {
      return when {
         this.listId > other.listId -> 1
         this.listId == other.listId -> {
            if (this.name != null && other.name != null) {
               this.name.compareTo(other.name)
            } else if (other.name != null) {
               -1
            } else {
               1
            }
         }
         else -> -1
      }
   }

   override fun toString(): String {
      return name ?: ""
   }
}