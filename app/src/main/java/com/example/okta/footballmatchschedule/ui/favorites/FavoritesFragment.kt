package com.example.okta.footballmatchschedule.ui.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.adapter.FavoritesPagerAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.matches_fragment.*

class FavoritesFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter = FavoritesPagerAdapter(childFragmentManager)
        viewpager_favorites.adapter = fragmentAdapter
        tabs_favorites.setupWithViewPager(viewpager_favorites)
    }
}