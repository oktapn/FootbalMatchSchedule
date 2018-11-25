package com.example.okta.footballmatchschedule.ui.teams

import com.example.okta.applicationkade.model.TeamResponse
import com.example.okta.footballmatchschedule.model.teamsearch.TeamSearchResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class TeamsPresenter(private val service : Service, private val view: TeamsView) {

    private val subscriptions= CompositeSubscription()

    fun getTeam(league: String){
        view.showWait()

        val subscription = service.getTeamData(league, object : Service.GetTeamDataCallback {
            override fun onSuccess(teamResponse: TeamResponse) {
                view.removeWait()
                view.getResponse(teamResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

    fun getTeamSearch(team: String){
        view.showWait()

        val subscription = service.getTeamSearch(team, object : Service.GetTeamSearchCallback {
            override fun onSuccess(teamSearchResponse: TeamSearchResponse) {
                view.removeWait()
                view.getResponseSearch(teamSearchResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }
}