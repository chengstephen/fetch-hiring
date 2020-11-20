package com.example.fetchrewardscodingexercise.presentation.candidatelist

import com.example.fetchrewardscodingexercise.service.CandidateService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class CandidateListPresenter @Inject constructor(
   private val candidateService: CandidateService
) :  CandidateListContract.Presenter {

   companion object {
      private const val HIRING_JSON_FILE_NAME = "hiring.json"
   }

   private lateinit var view: CandidateListContract.View

   private val compositeDisposable = CompositeDisposable()

   override fun attachView(view: CandidateListContract.View) {
      this.view = view
      onViewShowing()
   }

   private fun onViewShowing() {
      val disposable =
         candidateService.getCandidateList(HIRING_JSON_FILE_NAME)
            .flatMapIterable { list -> list }
            .filter { !it.name.isNullOrEmpty() }
            .groupBy { it.listId }
            .flatMapSingle {
               it.collectInto(
                  CandidateListViewModel(it.key),
                  { viewModel, candidate -> viewModel.candidates.add(candidate) }
               )
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { view.showErrorRetrievingCandidates() }
            .subscribe(Consumer { view.showCandidateList(it) })
      compositeDisposable.add(disposable)
   }

   override fun dispose() {
      compositeDisposable.dispose()
   }
}