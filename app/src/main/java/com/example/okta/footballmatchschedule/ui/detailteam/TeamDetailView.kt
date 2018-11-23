package com.example.okta.footballmatchschedule.ui.detailteam

import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse

interface TeamDetailView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(detailTeamResponse: DetailTeamResponse)

}