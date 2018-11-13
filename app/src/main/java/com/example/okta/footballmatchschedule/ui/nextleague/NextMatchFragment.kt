package com.example.okta.footballmatchschedule.ui.nextleague

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.okta.footballmatchschedule.BaseAppFragment
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.eventnextleague.EventNextLeagueResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detail.DetailMatchActivity
import com.example.okta.footballschedulematch.adapter.RVAdapterNextMatch
import kotlinx.android.synthetic.main.next_match_fragment.*

import javax.inject.Inject

class NextMatchFragment : BaseAppFragment(), NextMatchFragmentView {

    private lateinit var adapter: RVAdapterNextMatch
    private lateinit var presenter: NextMatchFragmentPresenter
    internal lateinit var progress: ProgressDialog
    @Inject
    lateinit var service: Service

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.next_match_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deps.inject(this)
        progress = ProgressDialog(context, R.style.MyTheme);
        presenter = NextMatchFragmentPresenter(service, this)
        presenter.getid("4328")
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
        Toast.makeText(context, appErrorMessage, Toast.LENGTH_LONG)
    }

    override fun getResponse(eventNextLeagueResponse: EventNextLeagueResponse) {
        RVnext.layoutManager = LinearLayoutManager(context)
        adapter = RVAdapterNextMatch(context, eventNextLeagueResponse.events!!) {
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("idevent", it.idEvent)
            startActivity(intent)
        }
        RVnext.adapter = adapter
    }
}