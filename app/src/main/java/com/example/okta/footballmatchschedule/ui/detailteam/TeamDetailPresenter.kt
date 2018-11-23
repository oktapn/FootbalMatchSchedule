package com.example.okta.footballmatchschedule.ui.detailteam

import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.NetworkError
import com.example.okta.footballmatchschedule.networking.Service
import rx.subscriptions.CompositeSubscription

class TeamDetailPresenter(private val service : Service, private val view: TeamDetailView) {

    private val subscriptions= CompositeSubscription()

    fun getDetailTeamHome(id: String){
        view.showWait()

        val subscription = service.getTeamDatabyID(id, object : Service.GetTeamDataByIDCallback {
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