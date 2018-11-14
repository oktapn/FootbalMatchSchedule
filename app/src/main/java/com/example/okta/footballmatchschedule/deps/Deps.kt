package com.example.okta.footballmatchschedule.deps


import com.example.okta.footballmatchschedule.networking.NetworkModule
import com.example.okta.footballmatchschedule.ui.detail.DetailMatchActivity
import com.example.okta.footballmatchschedule.ui.main.MainActivity
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment
import com.example.okta.footballmatchschedule.ui.lastleague.LastMatchFragment
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
}
