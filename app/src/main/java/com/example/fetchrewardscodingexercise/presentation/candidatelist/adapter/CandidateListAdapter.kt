package com.example.fetchrewardscodingexercise.presentation.candidatelist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardscodingexercise.R
import com.example.fetchrewardscodingexercise.presentation.candidatelist.CandidateListViewModel
import kotlinx.android.synthetic.main.list_item_candidate.view.candidate_list_id
import kotlinx.android.synthetic.main.list_item_candidate.view.candidate_list_names

class CandidateListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   private var viewModelList = mutableListOf<CandidateListViewModel>()

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val view =
         LayoutInflater.from(parent.context).inflate(R.layout.list_item_candidate, parent, false)
      return CandidateViewHolder(view)
   }

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      if (holder is CandidateViewHolder) {
         holder.bind(viewModelList[position])
      }
   }

   override fun getItemCount(): Int = viewModelList.size

   fun setCandidateList(viewModel: List<CandidateListViewModel>) {
      viewModelList.clear()
      viewModelList.addAll(viewModel)
      notifyDataSetChanged()
   }

   fun clearCandidates() {
      viewModelList.clear()
      notifyDataSetChanged()
   }
}

class CandidateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   fun bind(viewModel: CandidateListViewModel) {
      val context = itemView.context
      itemView.candidate_list_id.text = context.getString(R.string.list_id, viewModel.listId)
      itemView.candidate_list_names.text =
         context.getString(R.string.candidates, viewModel.candidates.sorted().joinToString())
   }
}