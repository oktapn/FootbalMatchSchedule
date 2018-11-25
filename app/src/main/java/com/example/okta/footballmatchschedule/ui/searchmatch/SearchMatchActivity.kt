package com.example.okta.footballmatchschedule.ui.searchmatch

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.okta.footballmatchschedule.BaseApp
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.R.menu.main_menu
import com.example.okta.footballmatchschedule.model.searchmatch.SearchMatchResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchActivity
import com.example.okta.footballschedulematch.adapter.RVAdapterLastMatch
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class SearchMatchActivity : BaseApp(),SearchMatchView {

    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: RVAdapterLastMatch
    @Inject
    lateinit var service: Service
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        deps.inject(this)
        presenter = SearchMatchPresenter(service,this)
        query = intent.getStringExtra("query")
        presenter.getMatchSearch(query)
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showWait() {
        mainProgressBar.visibility = View.VISIBLE
    }

    override fun removeWait() {
        mainProgressBar.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {
        toast(appErrorMessage)
    }

    override fun getResponse(searchMatchResponse: SearchMatchResponse) {
        rvFootball.layoutManager = LinearLayoutManager(applicationContext)
        adapter = RVAdapterLastMatch(applicationContext, searchMatchResponse.event) {
            val intent = Intent(applicationContext, DetailMatchActivity::class.java)
            intent.putExtra("idevent", it?.idEvent)
            startActivity(intent)
        }
        rvFootball.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(main_menu, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getMatchSearch(newText)
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
