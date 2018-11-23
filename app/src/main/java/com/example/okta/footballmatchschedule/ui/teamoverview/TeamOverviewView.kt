package com.example.okta.footballmatchschedule.ui.teamoverview

import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse

interface TeamOverviewView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(detailTeamResponse: DetailTeamResponse)
}