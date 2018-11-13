package com.example.okta.footballmatchschedule.model.eventnextleague

import com.example.okta.footballmatchschedule.model.eventpastleague.Event
import com.google.gson.annotations.SerializedName

class EventNextLeagueResponse {

    @SerializedName("events")
    var events: List<Event>? = null

}
