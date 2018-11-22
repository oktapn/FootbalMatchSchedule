package com.example.okta.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.okta.footballmatchschedule.ui.favoritesevent.FavoriteEventsFragment
import com.example.okta.footballmatchschedule.ui.lastleague.LastMatchFragment
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteEventsFragment()
            }
            else -> {
                NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Matches"
            else -> {
                return "Teams"
            }
        }
    }
}