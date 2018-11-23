package com.example.okta.footballmatchschedule.ui.detailteam

import android.app.ProgressDialog
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.okta.applicationkade.database.FavoriteMatches
import com.example.okta.applicationkade.database.database
import com.example.okta.applicationkade.model.Team
import com.example.okta.footballmatchschedule.BaseApp
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.adapter.DetailTeamPagerAdapter
import com.example.okta.footballmatchschedule.adapter.DetailTeamViewPagerAdapter
import com.example.okta.footballmatchschedule.adapter.FavoritesPagerAdapter
import com.example.okta.footballmatchschedule.database.FavoriteTeam
import com.example.okta.footballmatchschedule.model.detailteam.DetailTeamResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailmatch.DetailMatchPresenter
import com.example.okta.footballmatchschedule.ui.teamoverview.TeamOverviewFragment
import com.example.okta.footballmatchschedule.ui.teamplayer.TeamPlayerFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class TeamDetailActivity : BaseApp(),TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var progress: ProgressDialog
    @Inject
    lateinit var service: Service
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String
    private lateinit var teams: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)
        deps.inject(this)
        presenter = TeamDetailPresenter(service,this)
        progress = ProgressDialog(this, R.style.MyTheme)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        id = intent.getStringExtra("id")

        id = if(savedInstanceState != null){
            savedInstanceState.getString("id")
        }else{
            intent.getStringExtra("id")
        }
        val bundle = Bundle()
        bundle.putString("id", id)


        favoriteState()
        val fragmentAdapter = DetailTeamViewPagerAdapter(supportFragmentManager)
        val teamFragment = TeamOverviewFragment()
        val playersFragment = TeamPlayerFragment()
        teamFragment.arguments = bundle
        playersFragment.arguments = bundle
        fragmentAdapter.populateFragment(teamFragment, "Team Overview")
        fragmentAdapter.populateFragment(playersFragment, "Players")
        viewpagerTeam.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpagerTeam)
        presenter.getDetailTeamHome(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteMatches>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (this::teams.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                }
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
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge
                )
            }
//            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
//            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatches.TABLE_FAVORITE_MATCH, "(TEAM_ID = {id})", "id" to id)
            }
//            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
//            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
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

    override fun getResponse(detailTeamResponse: DetailTeamResponse) {
        Glide.with(applicationContext)
            .load(detailTeamResponse.teams?.get(0)?.strTeamBadge).into(ICTeam)
        TVTeam1.text = detailTeamResponse.teams?.get(0)?.strTeam
        TVTeam2.text = detailTeamResponse.teams?.get(0)?.intFormedYear.toString()
        TVTeam3.text = detailTeamResponse.teams?.get(0)?.strStadium
    }

}
