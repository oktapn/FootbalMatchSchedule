package com.example.okta.footballmatchschedule.ui.teamplayer

import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class TeamPlayerPresenter(private val service : Service, private val view: TeamPlayerView) {

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