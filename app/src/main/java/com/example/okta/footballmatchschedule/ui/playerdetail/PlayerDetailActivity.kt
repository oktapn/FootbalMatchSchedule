package com.example.okta.footballmatchschedule.ui.playerdetail

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.okta.footballmatchschedule.BaseApp
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.playerdetail.PlayerDetailResponse
import com.example.okta.footballmatchschedule.networking.Service
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.layout_detail_player.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class PlayerDetailActivity : BaseApp(),PlayerDetailView {

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var progress: ProgressDialog
    @Inject
    lateinit var service: Service
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        deps.inject(this)
        presenter = PlayerDetailPresenter(service,this)
        progress = ProgressDialog(this, R.style.MyTheme)
        setSupportActionBar(toolbar_player_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        id = intent.getStringExtra("id")
        presenter.getDetailTeamHome(id)
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

    override fun showWait() {
        progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
    }

    override fun removeWait() {
        progress.dismiss()
    }

    override fun onFailure(appErrorMessage: String) {
        toast(appErrorMessage)
    }

    override fun getResponse(playerDetailResponse: PlayerDetailResponse) {
        Glide.with(applicationContext).load(playerDetailResponse.players?.get(0)?.strFanart1).apply(RequestOptions().placeholder(R.color.background_floating_material_dark)).into(imageBannerPlayer)
        WegPlayer.text = playerDetailResponse.players?.get(0)?.strWeight
        HeigPlayer.text = playerDetailResponse.players?.get(0)?.strHeight
        OverPlayer.text = playerDetailResponse.players?.get(0)?.strDescriptionEN
    }


}
