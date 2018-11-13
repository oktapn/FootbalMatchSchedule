package com.example.okta.footballmatchschedule.ui.nextleague

import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class NextMatchFragmentPresenter(private val service : Service, private val view: NextMatchFragmentView) {

    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    fun getid(id: String){
        view.showWait()

        val subscription = service.getNextMatch(id, object : Service.GetNextEventCallback {
            override fun onSuccess(responseLogin: EventNextLeagueResponse) {
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