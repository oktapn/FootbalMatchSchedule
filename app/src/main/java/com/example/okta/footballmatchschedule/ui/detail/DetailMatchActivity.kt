package com.example.okta.footballmatchschedule.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide

import com.example.okta.footballmatchschedule.BaseApp
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.Service
import kotlinx.android.synthetic.main.activity_detail_match.*
import javax.inject.Inject

class DetailMatchActivity : BaseApp(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var progress: ProgressDialog
    @Inject
    lateinit var service: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        deps.inject(this)
        presenter = DetailMatchPresenter(service, this)
        progress = ProgressDialog(this, R.style.MyTheme)
        var event = intent.getStringExtra("idevent")
        presenter.getDetailEvent(event)
    }

    override fun getResponseEvent(detailEventResponse: DetailEventResponse) {
        TVtgl.text = detailEventResponse.events?.get(0)?.dateEvent ?:""
        Dscore1.text = detailEventResponse.events?.get(0)?.intHomeScore?.toString()?:""
        Dscore2.text = detailEventResponse.events?.get(0)?.intAwayScore?.toString()?:""
        nameHome.text = detailEventResponse.events?.get(0)?.strHomeTeam?:""
        nameAway.text = detailEventResponse.events?.get(0)?.strAwayTeam?:""
        goalHome.text = detailEventResponse.events?.get(0)?.strHomeGoalDetails?.toString()?:""
        goalAway.text = detailEventResponse.events?.get(0)?.strAwayGoalDetails?.toString()?:""
        shotsHome.text = detailEventResponse.events?.get(0)?.intHomeShots?.toString()?:""
        shotsAway.text = detailEventResponse.events?.get(0)?.intAwayShots?.toString()?:""
        goalkeeperHome.text = detailEventResponse.events?.get(0)?.strHomeLineupGoalkeeper?.toString()?:""
        goalKeeperAway.text = detailEventResponse.events?.get(0)?.strAwayLineupGoalkeeper?.toString()?:""
        defenseHome.text = detailEventResponse.events?.get(0)?.strHomeLineupDefense?.toString()?:""
        defenseAway.text = detailEventResponse.events?.get(0)?.strAwayLineupDefense?.toString()?:""
        midfieldHome.text = detailEventResponse.events?.get(0)?.strHomeLineupMidfield?.toString()?:""
        midfieldAway.text = detailEventResponse.events?.get(0)?.strAwayLineupMidfield?.toString()?:""
        forwardHome.text = detailEventResponse.events?.get(0)?.strHomeLineupForward?.toString()?:""
        forwardAway.text = detailEventResponse.events?.get(0)?.strAwayLineupForward?.toString()?:""
        subtitutesHome.text = detailEventResponse.events?.get(0)?.strHomeLineupSubstitutes?.toString()?:""
        subtitutesAway.text = detailEventResponse.events?.get(0)?.strAwayLineupSubstitutes?.toString()?:""
        presenter.getDetailTeamHome(detailEventResponse.events?.get(0)?.strHomeTeam?:"")
        presenter.getDetailTeamAway(detailEventResponse.events?.get(0)?.strAwayTeam?:"")
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
        Toast.makeText(applicationContext, appErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun getResponseHome(detailTeamResponse: DetailTeamResponse) {
        Glide.with(applicationContext).load(detailTeamResponse.teams?.get(0)?.strTeamBadge).into(Logo1)
    }

    override fun getResponseAway(detailTeamResponse: DetailTeamResponse) {
        Glide.with(applicationContext).load(detailTeamResponse.teams?.get(0)?.strTeamBadge).into(Logo2)
    }

}
