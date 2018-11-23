package com.example.okta.footballmatchschedule.ui.teamoverview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.okta.footballmatchschedule.BaseAppFragment
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.Service
import kotlinx.android.synthetic.main.team_overview_fragment.*
import javax.inject.Inject

class TeamOverviewFragment : BaseAppFragment(), TeamOverviewView {

    @Inject
    lateinit var service: Service
    lateinit var presenter: TeamOverviewPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.team_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deps.inject(this)
        presenter = TeamOverviewPresenter(service, this)
        val id: String? = arguments?.getString("id")
        presenter.getDetailTeamOverview(id)
    }

    override fun showWait() {
        PG.visibility = View.VISIBLE
    }

    override fun removeWait() {
        PG.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {
        Toast.makeText(context,appErrorMessage,Toast.LENGTH_LONG).show()
    }

    override fun getResponse(detailTeamResponse: DetailTeamResponse) {
        teamOverview.text = detailTeamResponse.teams?.get(0)?.strDescriptionEN
    }
}