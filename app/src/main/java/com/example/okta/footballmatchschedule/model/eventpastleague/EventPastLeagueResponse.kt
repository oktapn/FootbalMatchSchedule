package com.example.okta.footballmatchschedule.model.eventpastleague

import com.google.gson.annotations.SerializedName

class EventPastLeagueResponse {

    @SerializedName("events")
    var events: List<Event>? = null

}
