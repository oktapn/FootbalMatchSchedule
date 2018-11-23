package com.example.okta.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.okta.footballmatchschedule.ui.favoritesevent.FavoriteMatchesFragment
import com.example.okta.footballmatchschedule.ui.favoritesteam.FavoriteTeamsFragment
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMatchesFragment()
            }
            else -> {
                FavoriteTeamsFragment()
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