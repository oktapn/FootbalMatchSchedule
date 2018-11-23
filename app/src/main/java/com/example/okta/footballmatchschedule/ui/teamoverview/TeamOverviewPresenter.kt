package com.example.okta.footballmatchschedule.ui.teamoverview

import com.example.okta.applicationkade.model.TeamResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailteam.TeamDetailView
import com.example.okta.footballmatchschedule.ui.teams.TeamsView
import rx.subscriptions.CompositeSubscription

class TeamOverviewPresenter(private val service : Service, private val view: TeamOverviewView) {

    private val subscriptions= CompositeSubscription()

    fun getDetailTeamOverview(team: String?){
        view.showWait()

        val subscription = service.getTeamDatabyID(team, object : Service.GetTeamDataByIDCallback {
            override fun onSuccess(detailTeamResponse: DetailTeamResponse) {
                view.removeWait()
                view.getResponse(detailTeamResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

}