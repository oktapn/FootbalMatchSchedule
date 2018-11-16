package com.example.okta.footballmatchschedule.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.R.id.*
import com.example.okta.footballmatchschedule.ui.favorites.FavoriteEventsFragment
import com.example.okta.footballmatchschedule.ui.lastleague.LastMatchFragment
import com.example.okta.footballmatchschedule.ui.nextleague.NextMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                pastleague -> {
                    loadPastLeagueFragment(savedInstanceState)
                }
                nextleague -> {
                    loadNextLeagueFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = pastleague
    }

    private fun loadPastLeagueFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextLeagueFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteEventsFragment(), FavoriteEventsFragment::class.java.simpleName)
                .commit()
        }
    }
}
