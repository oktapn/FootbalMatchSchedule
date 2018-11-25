package com.example.okta.footballmatchschedule.ui.teams

import com.example.okta.applicationkade.model.Team
import com.example.okta.applicationkade.model.TeamResponse
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.model.teamsearch.TeamSearchResponse


interface TeamsView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(teamResponse: TeamResponse)

    fun getResponseSearch(teamSearchResponse: TeamSearchResponse)
}