package com.example.realpawsterbookingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        btnStart = findViewById(R.id.btnStart)

        // Set click listeners
        btnStart.setOnClickListener {
            val i = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(i)
        }
    }
}