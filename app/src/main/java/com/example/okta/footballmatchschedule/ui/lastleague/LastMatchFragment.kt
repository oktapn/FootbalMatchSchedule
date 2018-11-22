package com.example.okta.footballmatchschedule.ui.lastleague

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.okta.footballmatchschedule.BaseAppFragment
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.eventpastleague.EventPastLeagueResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchActivity
import com.example.okta.footballmatchschedule.ui.nextleague.LastMatchFragmentPresenter
import com.example.okta.footballschedulematch.adapter.RVAdapterLastMatch
import kotlinx.android.synthetic.main.last_match_fragment.*
import javax.inject.Inject

class LastMatchFragment : BaseAppFragment(), LastMatchFragmentView {

    private lateinit var adapter: RVAdapterLastMatch
    private lateinit var presenter: LastMatchFragmentPresenter
    private lateinit var progress: ProgressDialog
    private lateinit var leagueName: String
    @Inject
    lateinit var service: Service

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.last_match_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deps.inject(this)
        progress = ProgressDialog(context, R.style.MyTheme)
        presenter = LastMatchFragmentPresenter(service, this)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        item_spinner_last_match.adapter = spinnerAdapter
        item_spinner_last_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = item_spinner_last_match.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> presenter.getLastMatch("4328")
                    "German Bundesliga" -> presenter.getLastMatch("4331")
                    "Italian Serie A" -> presenter.getLastMatch("4332")
                    "French Ligue 1" -> presenter.getLastMatch("4334")
                    "Spanish La Liga" -> presenter.getLastMatch("4335")
                    "Netherlands Eredivisie" -> presenter.getLastMatch("4337")
                    else -> presenter.getLastMatch("4328")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        presenter.getLastMatch("4328")
    }

    override fun showWait() {
        progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
    }

    override fun removeWait() {
        progress.dismiss()
    }

    override fun onFailure(appErrorMessage: String) {
        Toast.makeText(context, appErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun getResponse(eventPastLeagueResponse: EventPastLeagueResponse) {
        RVlast.layoutManager = LinearLayoutManager(context)
        adapter = RVAdapterLastMatch(context, eventPastLeagueResponse.events) {
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("idevent", it?.idEvent)
            startActivity(intent)
        }
        RVlast.adapter = adapter
    }

}