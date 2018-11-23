package com.example.okta.footballmatchschedule.ui.teamplayer

import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse

interface TeamPlayerView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(teamResponse: DetailTeamResponse)
}