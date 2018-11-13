package com.example.okta.footballmatchschedule.ui.nextleague

import com.example.okta.footballmatchschedule.model.eventpastleague.EventPastLeagueResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.pastleague.LastMatchFragmentView
import rx.subscriptions.CompositeSubscription

class LastMatchFragmentPresenter(private val service : Service, private val view: LastMatchFragmentView) {

    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    fun getid(id: String){
        view.showWait()

        val subscription = service.getLastMatch(id, object : Service.GetPastEventCallback {
            override fun onSuccess(responseLogin: EventPastLeagueResponse) {
                view.removeWait()
                view.getResponse(responseLogin)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }
}