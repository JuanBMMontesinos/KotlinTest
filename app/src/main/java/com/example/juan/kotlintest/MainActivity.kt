package com.example.juan.kotlintest

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.juan.kotlintest.adapter.TwitchAdapter
import com.example.juan.kotlintest.interfaces.RetrofitInitializer
import com.example.juan.kotlintest.model.Game
import com.example.juan.kotlintest.model.TopGames
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val srlContainer: SwipeRefreshLayout by lazy {
        findViewById<View>(R.id.srl_container) as SwipeRefreshLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        srlContainer.setOnRefreshListener { loadList(false) }

        if (AppStatus.getInstance(this).isOnline) {
            loadList(true)
            //Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show()
            Log.v("Home", "############################You are not online!!!!")
        }
    }

    private fun loadList(showDialog: Boolean ) {

        val progressBar = ProgressDialog(this)
        progressBar.setCancelable(false)
        progressBar.setTitle("Please, wait")
        progressBar.setMessage("Loading")
        if (showDialog)
            progressBar.show()

        RetrofitInitializer().twitchService().getTop(Settings.CLIENT_ID)
                .enqueue(object : Callback<TopGames?> {
                    override fun onResponse(call: Call<TopGames?>?, response: Response<TopGames?>?) {
                        response?.body()?.let {
                            val topGame: TopGames = it
                            configureList(topGame)
                            srlContainer.setRefreshing(false)
                            if (progressBar.isShowing)
                                progressBar.hide()
                        }
                    }

                    override fun onFailure(call: Call<TopGames?>?, t: Throwable?) {
                        srlContainer.setRefreshing(false)
                        if (progressBar.isShowing)
                            progressBar.hide()

                        Toast.makeText(this@MainActivity, ":| There was an error fetching the Games list. Try again later.", Toast.LENGTH_SHORT).show();
                    }
                })
    }

    private fun configureList(games: TopGames) {
        val recyclerView = game_list_recyclerview
        recyclerView.adapter = TwitchAdapter(
                this,
                games,
                this.applicationContext,
                object : TwitchAdapter.BtnClickListener {
                    override fun onBtnClick(position: Int) {
                        val intent = MainActivity.newIntent(this@MainActivity, games.top[position].game)
                        startActivity(intent)
                    }
                }
        )

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    companion object {

        fun newIntent(context: Context, game: Game): Intent {
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra(Settings.INTENT_GAME_NAME, game.name)
            intent.putExtra(Settings.INTENT_GAME_LOCALIZED_NAME, game.localizedName)
            intent.putExtra(Settings.INTENT_GAME_POPULARITY, game.popularity)
            intent.putExtra(Settings.INTENT_GAME_BOX, game.box["large"])

            return intent
        }
    }

}
