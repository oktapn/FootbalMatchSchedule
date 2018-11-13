package com.example.okta.footballmatchschedule.ui.detail

import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse

interface DetailMatchView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponseHome(detailTeamResponse: DetailTeamResponse)

    fun getResponseAway(detailTeamResponse: DetailTeamResponse)

    fun getResponseEvent(detailEventResponse: DetailEventResponse)

}