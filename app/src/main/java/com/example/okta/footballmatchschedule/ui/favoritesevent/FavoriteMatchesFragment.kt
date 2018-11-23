package com.example.okta.footballmatchschedule.ui.favoritesevent

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.okta.applicationkade.database.FavoriteMatches
import com.example.okta.applicationkade.database.database
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.adapter.RVAdapterFavoritesEvents
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesFragment : Fragment(), AnkoComponent<Context> {

    private var favoriteMatches: MutableList<FavoriteMatches> = mutableListOf()
    private lateinit var adapter: RVAdapterFavoritesEvents
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RVAdapterFavoritesEvents(context, favoriteMatches) {
            context?.startActivity<DetailMatchActivity>("idevent" to "${it?.matchId}")
        }

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favoriteMatches.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatches.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatches>())
            favoriteMatches.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                listTeam = recyclerView {
                    id = R.id.list_team
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}