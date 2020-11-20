package com.example.fetchrewardscodingexercise.presentation.candidatelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardscodingexercise.R
import com.example.fetchrewardscodingexercise.model.Candidate
import com.example.fetchrewardscodingexercise.presentation.candidatelist.adapter.CandidateListAdapter
import com.example.fetchrewardscodingexercise.presentation.utils.makeDialog
import kotlinx.android.synthetic.main.view_candidate_list.candidate_results
import javax.inject.Inject

class CandidateListView @Inject constructor() : AppCompatActivity(), CandidateListContract.View {

   private lateinit var presenter: CandidateListContract.Presenter

   private lateinit var candidateListAdapter: CandidateListAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      initPresenter()

      setContentView(R.layout.view_candidate_list)

      initAdapter()
   }

   private fun initPresenter() {
      presenter = CandidateListContract.Injector.create(application).presenter()
      presenter.attachView(this)
   }

   private fun initAdapter() {
      candidate_results.layoutManager =
         LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
      candidateListAdapter = CandidateListAdapter()
      candidate_results.adapter = candidateListAdapter
   }

   override fun onDestroy() {
      presenter.dispose()
      super.onDestroy()
   }

   override fun showCandidateList(candidateList: List<CandidateListViewModel>) {
      candidateListAdapter.setCandidateList(candidateList)
   }

   override fun showErrorRetrievingCandidates() {
      makeDialog(this)
         .setMessage(R.string.error_retrieving_candidates)
         .show()
   }
}