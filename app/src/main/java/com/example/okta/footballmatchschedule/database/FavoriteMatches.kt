package com.example.okta.applicationkade.database

data class FavoriteMatches(
    val id: Long?,
    val matchId: String?,
    val matchDate: String?,
    val teamnameAway: String?,
    val teamscoreAway: String?,
    val teamnameHome: String?,
    val teamscoreHome: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val TEAM_NAME_AWAY: String = "TEAM_NAME_AWAY"
        const val TEAM_SCORE_AWAY: String = "TEAM_SCORE_AWAY"
        const val TEAM_NAME_HOME: String = "TEAM_NAME_HOME"
        const val TEAM_SCORE_HOME: String = "TEAM_SCORE_HOME"
    }
}