package com.example.okta.footballmatchschedule.model.player

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PlayerResponse(

	@field:SerializedName("player")
	val player: List<PlayerItem>
)