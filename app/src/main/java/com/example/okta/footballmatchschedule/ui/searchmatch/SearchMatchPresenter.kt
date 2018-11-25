package com.example.okta.footballmatchschedule.ui.searchmatch

import com.example.okta.footballmatchschedule.model.playerdetail.PlayerDetailResponse
import com.example.okta.footballmatchschedule.model.searchmatch.SearchMatchResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class SearchMatchPresenter(private val service : Service, private val view: SearchMatchView) {

    private val subscriptions= CompositeSubscription()

    fun getMatchSearch(e: String?){
        view.showWait()

        val subscription = service.getMatchSearch(e, object : Service.GetMatchSearchCallback {
            override fun onSuccess(searchMatchResponse: SearchMatchResponse) {
                view.removeWait()
                view.getResponse(searchMatchResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

}