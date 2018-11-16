package com.example.okta.footballmatchschedule.ui.detail

import android.app.ProgressDialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.okta.applicationkade.database.Favorite
import com.example.okta.applicationkade.database.database
import org.jetbrains.anko.db.insert

import com.example.okta.footballmatchschedule.BaseApp
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.okta.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.okta.footballmatchschedule.R.id.add_to_favorite
import com.example.okta.footballmatchschedule.R.menu.detail_menu
import com.example.okta.footballmatchschedule.model.detailevent.DetailEventResponse
import com.example.okta.footballmatchschedule.model.detailevent.Event
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.Service
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import javax.inject.Inject

class DetailMatchActivity : BaseApp(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var progress: ProgressDialog
    @Inject
    lateinit var service: Service
    private lateinit var teams: Event

    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        val intent = intent
        id = intent.getStringExtra("idevent")
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        swipeRefresh = SRLayout
        favoriteState()
        deps.inject(this)
        presenter = DetailMatchPresenter(service, this)
        progress = ProgressDialog(this, R.style.MyTheme)
        presenter.getDetailEvent(id)
        swipeRefresh.onRefresh {
            presenter.getDetailEvent(id)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to teams.idEvent,
                    Favorite.MATCH_DATE to teams.dateEvent,
                    Favorite.TEAM_SCORE_HOME to teams.intHomeScore,
                    Favorite.TEAM_NAME_HOME to teams.strHomeTeam,
                    Favorite.TEAM_SCORE_AWAY to teams.intAwayScore,
                    Favorite.TEAM_NAME_AWAY to teams.strAwayTeam
                )
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to id)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    override fun getResponseEvent(detailEventResponse: DetailEventResponse) {
        TVtgl.text = detailEventResponse.events[0].dateEvent ?: ""
        Dscore1.text = detailEventResponse.events[0].intHomeScore?.toString() ?: ""
        Dscore2.text = detailEventResponse.events[0].intAwayScore?.toString() ?: ""
        nameHome.text = detailEventResponse.events[0].strHomeTeam ?: ""
        nameAway.text = detailEventResponse.events[0].strAwayTeam ?: ""
        goalHome.text = detailEventResponse.events[0].strHomeGoalDetails?.toString() ?: ""
        goalAway.text = detailEventResponse.events[0].strAwayGoalDetails?.toString() ?: ""
        shotsHome.text = detailEventResponse.events[0].intHomeShots?.toString() ?: ""
        shotsAway.text = detailEventResponse.events[0].intAwayShots?.toString() ?: ""
        goalkeeperHome.text = detailEventResponse.events[0].strHomeLineupGoalkeeper?.toString() ?: ""
        goalKeeperAway.text = detailEventResponse.events[0].strAwayLineupGoalkeeper?.toString() ?: ""
        defenseHome.text = detailEventResponse.events[0].strHomeLineupDefense?.toString() ?: ""
        defenseAway.text = detailEventResponse.events[0].strAwayLineupDefense?.toString() ?: ""
        midfieldHome.text = detailEventResponse.events[0].strHomeLineupMidfield?.toString() ?: ""
        midfieldAway.text = detailEventResponse.events[0].strAwayLineupMidfield?.toString() ?: ""
        forwardHome.text = detailEventResponse.events[0].strHomeLineupForward?.toString() ?: ""
        forwardAway.text = detailEventResponse.events[0].strAwayLineupForward?.toString() ?: ""
        subtitutesHome.text = detailEventResponse.events[0].strHomeLineupSubstitutes?.toString() ?: ""
        subtitutesAway.text = detailEventResponse.events[0].strAwayLineupSubstitutes?.toString() ?: ""
        presenter.getDetailTeamHome(detailEventResponse.events?.get(0)?.strHomeTeam ?: "")
        presenter.getDetailTeamAway(detailEventResponse.events?.get(0)?.strAwayTeam ?: "")
        teams = Event(
            detailEventResponse.events[0].dateEvent,
            detailEventResponse.events[0].idAwayTeam,
            detailEventResponse.events[0].idEvent,
            detailEventResponse.events[0].idHomeTeam,
            detailEventResponse.events[0].idLeague,
            detailEventResponse.events[0].idSoccerXML,
            detailEventResponse.events[0].intAwayScore,
            detailEventResponse.events[0].intAwayShots,
            detailEventResponse.events[0].intHomeScore,
            detailEventResponse.events[0].intHomeShots.toString(),
            detailEventResponse.events[0].intRound,
            detailEventResponse.events[0].intSpectators,
            detailEventResponse.events[0].strAwayFormation,
            detailEventResponse.events[0].strAwayGoalDetails,
            detailEventResponse.events[0].strAwayLineupDefense,
            detailEventResponse.events[0].strAwayLineupForward,
            detailEventResponse.events[0].strAwayLineupGoalkeeper,
            detailEventResponse.events[0].strAwayLineupMidfield,
            detailEventResponse.events[0].strAwayLineupSubstitutes,
            detailEventResponse.events[0].strAwayRedCards,
            detailEventResponse.events[0].strAwayTeam,
            detailEventResponse.events[0].strAwayYellowCards.toString(),
            detailEventResponse.events[0].strBanner,
            detailEventResponse.events[0].strCircuit,
            detailEventResponse.events[0].strCity,
            detailEventResponse.events[0].strCountry,
            detailEventResponse.events[0].strDate,
            detailEventResponse.events[0].strDescriptionEN.toString(),
            detailEventResponse.events[0].strEvent,
            detailEventResponse.events[0].strFilename,
            detailEventResponse.events[0].strHomeFormation.toString(),
            detailEventResponse.events[0].strHomeGoalDetails,
            detailEventResponse.events[0].strHomeLineupDefense,
            detailEventResponse.events[0].strHomeLineupForward,
            detailEventResponse.events[0].strHomeLineupGoalkeeper,
            detailEventResponse.events[0].strHomeLineupMidfield,
            detailEventResponse.events[0].strHomeLineupSubstitutes,
            detailEventResponse.events[0].strHomeRedCards,
            detailEventResponse.events[0].strHomeYellowCards.toString(),
            detailEventResponse.events[0].strHomeTeam,
            detailEventResponse.events[0].strLeague,
            detailEventResponse.events[0].strLocked,
            detailEventResponse.events[0].strMap.toString(),
            detailEventResponse.events[0].strPoster.toString(),
            detailEventResponse.events[0].strResult,
            detailEventResponse.events[0].strSeason,
            detailEventResponse.events[0].strSport,
            detailEventResponse.events[0].strTVStation.toString(),
            detailEventResponse.events[0].strThumb.toString(),
            detailEventResponse.events[0].strTime
        )
        swipeRefresh.isRefreshing = false
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
