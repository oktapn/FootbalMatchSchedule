package com.example.okta.footballmatchschedule.ui.teams

import android.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.okta.applicationkade.model.Team
import com.example.okta.applicationkade.model.TeamResponse
import com.example.okta.footballmatchschedule.BaseAppFragment
import com.example.okta.footballmatchschedule.R.array.league
import com.example.okta.footballmatchschedule.R.id.actionSearch
import com.example.okta.footballmatchschedule.R.id.list_team
import com.example.okta.footballmatchschedule.R.menu.main_menu
import com.example.okta.footballmatchschedule.adapter.TeamsAdapter
import com.example.okta.footballmatchschedule.model.teamsearch.TeamSearchResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.detailteam.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TeamsFragment : BaseAppFragment(), AnkoComponent<Context>, TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String
    @Inject
    lateinit var service: Service

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deps.inject(this)
        setHasOptionsMenu(true)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter as SpinnerAdapter?
        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter
        presenter = TeamsPresenter(service, this)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                teams.clear()
                presenter.getTeam(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeam(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(main_menu, menu)
        val searchView = menu?.findItem(actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.isNotEmpty()){
                    adapter = TeamsAdapter(teams) {
                        context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
                    }
                    listTeam.adapter = adapter
                    teams.clear()
                    presenter.getTeamSearch(newText)
                }
                return false
            }
        })

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                adapter = TeamsAdapter(teams) {
                    context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
                }
                listTeam.adapter = adapter
                teams.clear()
                presenter.getTeam("English Premier League")
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                //                    id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_green_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = list_team
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showWait() {
        progressBar.visibility = View.VISIBLE
    }

    override fun removeWait() {
        progressBar.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {
        toast(appErrorMessage)
    }

    override fun getResponse(teamResponse: TeamResponse) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(teamResponse.teams)
        adapter.notifyDataSetChanged()
        adapter = TeamsAdapter(teams) {
            context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
    }

    override fun getResponseSearch(teamSearchResponse: TeamSearchResponse) {
        if (teamSearchResponse.teams!=null){
            swipeRefresh.isRefreshing = false
            teams.clear()
            teams.addAll(teamSearchResponse.teams)
            adapter.notifyDataSetChanged()
            adapter = TeamsAdapter(teams) {
                context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
            }
        }
    }
}