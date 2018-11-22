package com.example.okta.footballmatchschedule.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballschedulematch.adapter.MatchesPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentAdapter = MatchesPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}
