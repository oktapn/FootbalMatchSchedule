package com.example.okta.footballschedulematch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.eventpastleague.Event
import kotlinx.android.synthetic.main.item_list.view.*

class RVAdapterLastMatch(
    private val context: Context?,
    private val items: List<Event>?,
    private val listener: (Event?) -> Unit
) : RecyclerView.Adapter<RVAdapterLastMatch.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items?.get(position), listener)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindItem(items: Event?, listener: (Event?) -> Unit) = with(containerView) {
            itemView.TVvs.text = "VS"
            if (items != null) {
                itemView.TVtglitem.text = items.dateEvent
                itemView.name2.text = items.strAwayTeam
                itemView.score2.text = items.intAwayScore as CharSequence?
                itemView.name1.text = items.strHomeTeam
                itemView.score1.text = items.intHomeScore as CharSequence?
                setOnClickListener { listener(items) }
            }
        }
    }
}