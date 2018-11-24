package com.example.okta.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.okta.footballmatchschedule.R
import com.example.okta.footballmatchschedule.model.player.PlayerItem
import org.jetbrains.anko.*

class RVPlayerAdapter(private val players: List<PlayerItem>, private val listener: (PlayerItem) -> Unit) : RecyclerView.Adapter<RVPlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position],listener)
    }

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val playerImage: ImageView = view.find(R.id.player_image)
        private val playerName: TextView = view.find(R.id.player_name)
        private val playerPosition: TextView = view.find(R.id.player_position)
        fun bindItem(playerItem: PlayerItem, listener: (PlayerItem) -> Unit) {
            Glide.with(itemView.context).load(playerItem.strCutout).into(playerImage)
            playerName.text = playerItem.strPlayer
            playerPosition.text = playerItem.strPosition
            itemView.setOnClickListener { listener(playerItem) }
        }
    }

    class PlayerUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.player_image
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.player_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                    textView {
                        id = R.id.player_position
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                }
            }
        }

    }
}