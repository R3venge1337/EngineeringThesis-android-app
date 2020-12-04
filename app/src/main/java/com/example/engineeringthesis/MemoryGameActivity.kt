package com.example.engineeringthesis

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class MemoryGameActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)
    }
}