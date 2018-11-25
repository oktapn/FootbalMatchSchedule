package com.example.okta.footballmatchschedule.ui.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.R.menu.main_menu
import com.example.okta.footballmatchschedule.ui.searchmatch.SearchMatchActivity
import com.example.okta.footballschedulematch.adapter.MatchesPagerAdapter
import kotlinx.android.synthetic.main.matches_fragment.*
import org.jetbrains.anko.startActivity

class MatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.matches_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val fragmentAdapter = MatchesPagerAdapter(childFragmentManager)
        viewpager_matches.adapter = fragmentAdapter
        tabs_matches.setupWithViewPager(viewpager_matches)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(main_menu, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchMatchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })


    }

}
