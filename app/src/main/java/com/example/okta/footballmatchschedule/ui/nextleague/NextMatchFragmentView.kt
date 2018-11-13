package com.example.okta.footballmatchschedule.ui.nextleague

import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse

interface NextMatchFragmentView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(eventNextLeagueResponse: EventNextLeagueResponse)
}