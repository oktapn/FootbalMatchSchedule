package com.example.okta.footballschedulematch.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment
import com.example.okta.footballmatchschedule.ui.lastleague.LastMatchFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastMatchFragment()
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
            0 -> "Last Match"
            else -> {
                return "Next Match"
            }
        }
    }
}