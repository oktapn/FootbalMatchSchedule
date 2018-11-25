package com.example.okta.footballmatchschedule.model.teamsearch

import com.example.okta.applicationkade.model.Team
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class TeamSearchResponse(

	@field:SerializedName("teams")
	val teams: List<Team>
)