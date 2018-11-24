package com.example.okta.footballmatchschedule.ui.teamplayer

import com.example.okta.footballmatchschedule.model.player.PlayerResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class TeamPlayerPresenter(private val service : Service, private val view: TeamPlayerView) {

    private val subscriptions= CompositeSubscription()

    fun getDetailTeamPlayer(id: String?){
        view.showWait()

        val subscription = service.getPlayers(id, object : Service.GetPlayersDataByIDCallback {
            override fun onSuccess(playerResponse: PlayerResponse) {
                view.removeWait()
                view.getResponse(playerResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

}