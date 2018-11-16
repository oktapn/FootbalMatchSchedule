package com.example.okta.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.okta.applicationkade.database.Favorite
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.detailevent.Event
import kotlinx.android.synthetic.main.item_list.view.*

class RVAdapterFavoritesEvents(
    private val context: Context?,
    private val items: List<Favorite>?,
    private val listener: (Favorite?) -> Unit
) : RecyclerView.Adapter<RVAdapterFavoritesEvents.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items?.get(position), listener)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindItem(items: Favorite?, listener: (Favorite?) -> Unit) = with(containerView) {
            itemView.TVvs.text = "VS"
            if (items != null) {
                itemView.TVtglitem.text = items.matchDate
                itemView.name2.text = items.teamnameAway
                itemView.score2.text = items.teamscoreAway?:"" as CharSequence
                itemView.name1.text = items.teamnameHome
                itemView.score1.text = items.teamscoreHome?:"" as CharSequence
                setOnClickListener { listener(items) }
            }
        }
    }
}