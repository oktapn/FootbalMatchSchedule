package com.example.okta.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.okta.applicationkade.database.FavoriteMatches
import com.example.okta.applicationkade.model.Team
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.database.FavoriteTeam
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.*

class RVAdapterFavoritesTeams(
    private val context: Context?,
    private val items: List<FavoriteTeam>?,
    private val listener: (FavoriteTeam?) -> Unit
) : RecyclerView.Adapter<RVAdapterFavoritesTeams.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(items?.get(position), listener)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)
        fun bindItem(teams: FavoriteTeam?, listener: (FavoriteTeam) -> Unit) {
            Glide.with(itemView.context).load(teams?.teamBadge).into(teamBadge)
            teamName.text = teams?.teamName ?: ""
            itemView.setOnClickListener {
                if (teams != null) {
                    listener(teams)
                }
            }
        }
    }


    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }

                }
            }
        }

    }
}