package com.example.fetchrewardscodingexercise.presentation.candidatelist

import android.app.Application
import com.example.fetchrewardscodingexercise.dagger.ActivityScope
import com.example.fetchrewardscodingexercise.dagger.ServicesComponent
import com.example.fetchrewardscodingexercise.model.Candidate
import dagger.Component

interface CandidateListContract {
   interface View {
      fun showCandidateList(candidateList: List<CandidateListViewModel>)
      fun showErrorRetrievingCandidates()
   }

   interface Presenter {
      fun attachView(view: View)
      fun dispose()
   }

   @ActivityScope
   @Component(dependencies = [ServicesComponent::class])
   interface Injector {
      fun presenter(): CandidateListPresenter

      companion object {
         fun create(application: Application): Injector {
            return DaggerCandidateListContract_Injector
               .builder()
               .servicesComponent(ServicesComponent.get(application))
               .build()
         }
      }
   }
}

class CandidateListViewModel(
   val listId: Int?,
   val candidates: MutableList<Candidate> = mutableListOf()
)