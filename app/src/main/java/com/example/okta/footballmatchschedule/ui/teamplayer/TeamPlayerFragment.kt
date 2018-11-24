package com.example.okta.footballmatchschedule.ui.teamplayer

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.okta.footballmatchschedule.BaseAppFragment
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.adapter.RVPlayerAdapter
import com.example.okta.footballmatchschedule.model.player.PlayerItem
import com.example.okta.footballmatchschedule.model.player.PlayerResponse
import com.example.okta.footballmatchschedule.networking.Service
import com.example.okta.footballmatchschedule.ui.playerdetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.last_match_fragment.*
import kotlinx.android.synthetic.main.team_players_fragment.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class TeamPlayerFragment : BaseAppFragment(), TeamPlayerView {

    @Inject
    lateinit var service: Service
    lateinit var presenter: TeamPlayerPresenter
    private var playerItem: MutableList<PlayerItem> = mutableListOf()
    private lateinit var adapter: RVPlayerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.team_players_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deps.inject(this)
        presenter = TeamPlayerPresenter(service, this)
        val id: String? = arguments?.getString("id")
        presenter.getDetailTeamPlayer(id)
    }

    override fun showWait() {
        PG_player.visibility = View.VISIBLE
    }

    override fun removeWait() {
        PG_player.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {
        Toast.makeText(context,appErrorMessage, Toast.LENGTH_LONG).show()
    }

    override fun getResponse(detailTeamResponse: PlayerResponse) {
        RVPlayer.layoutManager = LinearLayoutManager(context)
        adapter = RVPlayerAdapter(detailTeamResponse.player) {
            context?.startActivity<PlayerDetailActivity>("id" to "${it?.idPlayer}")
        }
        RVPlayer.adapter = adapter
    }

}