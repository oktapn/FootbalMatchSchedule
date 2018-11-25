package com.example.okta.footballmatchschedule.deps


import com.example.okta.footballmatchschedule.networking.NetworkModule
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchActivity
import com.example.okta.footballmatchschedule.ui.detailteam.TeamDetailActivity
import com.example.okta.footballmatchschedule.ui.main.MainActivity
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment
import com.example.okta.footballmatchschedule.ui.lastleague.LastMatchFragment
import com.example.okta.footballmatchschedule.ui.playerdetail.PlayerDetailActivity
import com.example.okta.footballmatchschedule.ui.searchmatch.SearchMatchActivity
import com.example.okta.footballmatchschedule.ui.teamoverview.TeamOverviewFragment
import com.example.okta.footballmatchschedule.ui.teamplayer.TeamPlayerFragment
import com.example.okta.footballmatchschedule.ui.teams.TeamsFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Created by okta on 11/13/2018.
 */
@Singleton
@Component(modules =  arrayOf(NetworkModule::class))
interface Deps {

    fun inject(mainActivity: MainActivity)

    fun inject(detailmatchActivity: DetailMatchActivity)

    fun inject(nextmatchFragment: NextMatchFragment)

    fun inject(lastmatchFragment: LastMatchFragment)

    fun inject(teamsFragment: TeamsFragment)

    fun inject(teamDetailActivity: TeamDetailActivity)

    fun inject(teamOverviewFragment: TeamOverviewFragment)

    fun inject(teamPlayerFragment: TeamPlayerFragment)

    fun inject(playerDetailActivity: PlayerDetailActivity)

    fun inject(searchMatchActivity: SearchMatchActivity)
}
