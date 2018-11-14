package com.example.okta.footballmatchschedule.ui.lastleague

import com.example.okta.footballmatchschedule.model.eventpastleague.EventPastLeagueResponse

interface LastMatchFragmentView {

    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(eventPastLeagueResponse: EventPastLeagueResponse)
}