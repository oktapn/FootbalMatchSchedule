package com.example.okta.footballmatchschedule.ui.nextleague

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
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchActivity
import com.example.okta.footballschedulematch.adapter.RVAdapterNextMatch
import kotlinx.android.synthetic.main.next_match_fragment.*

import javax.inject.Inject

class NextMatchFragment : BaseAppFragment(), NextMatchFragmentView {

    private lateinit var adapter: RVAdapterNextMatch
    private lateinit var presenter: NextMatchFragmentPresenter
    private lateinit var progress: ProgressDialog
    lateinit var leagueName : String
    @Inject
    lateinit var service: Service

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.next_match_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deps.inject(this)
        progress = ProgressDialog(context, R.style.MyTheme)
        presenter = NextMatchFragmentPresenter(service, this)
        presenter.getNextMatch("4328")
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        item_spinner_next_match.adapter = spinnerAdapter
        item_spinner_next_match.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = item_spinner_next_match.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> presenter.getNextMatch("4328")
                    "German Bundesliga" -> presenter.getNextMatch("4331")
                    "Italian Serie A" -> presenter.getNextMatch("4332")
                    "French Ligue 1" -> presenter.getNextMatch("4334")
                    "Spanish La Liga" -> presenter.getNextMatch("4335")
                    "Netherlands Eredivisie" -> presenter.getNextMatch("4337")
                    else -> presenter.getNextMatch("4328")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        presenter.getNextMatch("4328")
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

    override fun getResponse(eventNextLeagueResponse: EventNextLeagueResponse) {
        RVnext.layoutManager = LinearLayoutManager(context)
        adapter = RVAdapterNextMatch(context, eventNextLeagueResponse.events) {
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("idevent", it?.idEvent)
            startActivity(intent)
        }
        RVnext.adapter = adapter
    }
}