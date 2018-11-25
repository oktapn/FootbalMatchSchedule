package com.example.okta.footballmatchschedule.model.searchmatch

import com.example.okta.footballmatchschedule.model.eventpastleague.Event
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class SearchMatchResponse(

	@field:SerializedName("event")
	val event: List<Event>? = null
)