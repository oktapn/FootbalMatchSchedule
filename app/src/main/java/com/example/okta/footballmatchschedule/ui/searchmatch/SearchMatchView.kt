package com.example.okta.footballmatchschedule.ui.searchmatch

import com.example.okta.footballmatchschedule.model.searchmatch.SearchMatchResponse

interface SearchMatchView {

    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun getResponse(searchMatchResponse: SearchMatchResponse)

}