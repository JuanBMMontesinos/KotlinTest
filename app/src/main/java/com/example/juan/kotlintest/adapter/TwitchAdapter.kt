package com.example.juan.kotlintest.adapter

/**
 * Created by jubam on 09/11/2017.
 */

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.example.juan.kotlintest.model.Game
import com.example.juan.kotlintest.model.TopGame
import com.example.juan.kotlintest.model.TopGames
import com.example.juan.kotlintest.R
import kotlinx.android.synthetic.main.item_games.view.*

class TwitchAdapter(private val activity: Activity,
                      val games: TopGames,
                      private val context: Context,
                      val btnlistener: BtnClickListener) : RecyclerView.Adapter<TwitchAdapter.ViewHolder>() {

    open interface BtnClickListener {
        fun onBtnClick(position: Int)
    }

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    override fun getItemCount(): Int {
        return games.top.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun getItem(position: Int) : Game {
        return games.top[position].game
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        mClickListener = btnlistener

        val game = games.top[position]
        holder?.bindView(game)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_games, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(item: TopGame) {

            val title = itemView.game_item_name
            val channel_label = itemView.game_item_channel_label
            val channel = itemView.game_item_channel
            val viewers_label = itemView.game_item_viewers_label
            val viewers = itemView.game_item_viewers
            val boxImage = itemView.game_item_image

            channel_label.text = "Channel: "
            viewers_label.text = "Viewers: "

            title.text = item.game.name
            channel.text = item.channels
            viewers.text = item.viewers
            Picasso.with(itemView.context).load(item.game.box.get("medium").toString()).into(boxImage)

            itemView.setOnClickListener {
                if (mClickListener != null)
                    mClickListener?.onBtnClick(position)
            }
        }
    }
}