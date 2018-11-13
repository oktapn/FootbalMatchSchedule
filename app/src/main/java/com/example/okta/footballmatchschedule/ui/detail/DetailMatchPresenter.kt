package com.example.okta.footballmatchschedule.ui.detail

import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.pastleague.LastMatchFragmentView
import rx.subscriptions.CompositeSubscription

class DetailMatchPresenter(private val service : Service, private val view: DetailMatchView) {

    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    fun getDetailTeamHome(team: String){
        view.showWait()

        val subscription = service.getTeamDetail(team, object : Service.GetDetailTeamCallback {
            override fun onSuccess(detailTeamResponse: DetailTeamResponse) {
                view.removeWait()
                view.getResponseHome(detailTeamResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

    fun getDetailTeamAway(team: String){
        view.showWait()

        val subscription = service.getTeamDetail(team, object : Service.GetDetailTeamCallback {
            override fun onSuccess(detailTeamResponse: DetailTeamResponse) {
                view.removeWait()
                view.getResponseAway(detailTeamResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

    fun getDetailEvent(id: String){
        view.showWait()

        val subscription = service.getTeamEvent(id, object : Service.GetDetailEventCallback {
            override fun onSuccess(detailEventResponse: DetailEventResponse) {
                view.removeWait()
                view.getResponseEvent(detailEventResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

}