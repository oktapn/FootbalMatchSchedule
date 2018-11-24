package com.example.okta.footballmatchschedule.ui.playerdetail

import com.example.okta.footballmatchschedule.model.playerdetail.PlayerDetailResponse

interface PlayerDetailView {
    fun showWait()
    fun removeWait()
    fun getResponse(playerDetailResponse: PlayerDetailResponse)
    fun onFailure(appErrorMessage: String)
}