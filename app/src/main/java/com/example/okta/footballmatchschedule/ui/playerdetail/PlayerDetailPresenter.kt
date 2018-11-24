package com.example.okta.footballmatchschedule.ui.playerdetail

import com.example.okta.footballmatchschedule.model.playerdetail.PlayerDetailResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class PlayerDetailPresenter(private val service : Service, private val view: PlayerDetailView) {

    private val subscriptions= CompositeSubscription()

    fun getDetailTeamHome(id: String){
        view.showWait()

        val subscription = service.getPlayerDetail(id, object : Service.GetPlayersDetailCallback {
            override fun onSuccess(playerDetailResponse: PlayerDetailResponse) {
                view.removeWait()
                view.getResponse(playerDetailResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }
        })
        subscriptions.add(subscription)
    }

}