package com.example.okta.footballmatchschedule.networking

import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.model.eventpastleague.EventPastLeagueResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface NetworkService {
    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String): Observable<EventPastLeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Observable<EventNextLeagueResponse>

    @GET("searchteams.php")
    fun getDetailTeam(@Query("t") team: String): Observable<DetailTeamResponse>

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id: String): Observable<DetailEventResponse>

}
