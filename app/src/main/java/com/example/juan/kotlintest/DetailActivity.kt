package com.example.juan.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
/**
 * Created by Juan on 12/11/2017.
 */

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        if (AppStatus.getInstance(this).isOnline) {
            game_item_name_detail.text = intent.getStringExtra(Settings.INTENT_GAME_NAME)
            Picasso.with(this).load(intent.getStringExtra(Settings.INTENT_GAME_BOX).toString()).into(game_item_image_detail)
            game_item_popularity_detail.text = intent.getStringExtra(Settings.INTENT_GAME_POPULARITY)
            game_item_localized_name_detail.text = intent.getStringExtra(Settings.INTENT_GAME_LOCALIZED_NAME)
            //Toast.makeText(this, "You are online!!!!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You are not online!!!!", Toast.LENGTH_LONG).show()
            Log.v("Home", "############################You are not online!!!!")
        }
    }
}