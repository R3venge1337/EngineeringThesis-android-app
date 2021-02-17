package com.example.engineeringthesis

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_child_butt_rank.*

class ChildButtRankActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_butt_rank)

        child_fop_rank_button.setOnClickListener()
        {
            val childRankGameActivity = Intent(this, ChildRankGameActivity::class.java)
            childRankGameActivity.putExtra("GAME_NAME",child_fop_rank_button.text)
            startActivity(childRankGameActivity)
        }
        child_fov_rank_button.setOnClickListener()
        {
            val childRankGameActivity = Intent(this, ChildRankGameActivity::class.java)
            childRankGameActivity.putExtra("GAME_NAME",child_fov_rank_button.text)
            startActivity(childRankGameActivity)
        }
        child_dad_rank_button.setOnClickListener()
        {
            val childRankGameActivity = Intent(this, ChildRankGameActivity::class.java)
            childRankGameActivity.putExtra("GAME_NAME",child_dad_rank_button.text)
            startActivity(childRankGameActivity)
        }
        child_mma_rank_button.setOnClickListener()
        {
            val childRankGameActivity = Intent(this, ChildRankGameActivity::class.java)
            childRankGameActivity.putExtra("GAME_NAME",child_mma_rank_button.text)
            startActivity(childRankGameActivity)
        }
        child_saa_rank_button.setOnClickListener()
        {
            val childRankGameActivity = Intent(this, ChildRankGameActivity::class.java)
            childRankGameActivity.putExtra("GAME_NAME",child_saa_rank_button.text)
            startActivity(childRankGameActivity)
        }
    }
}