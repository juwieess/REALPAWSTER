package com.example.realpawsterbookingsystem

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity9 : AppCompatActivity() {

    private lateinit var home : ImageView
    private lateinit var about : ImageView
    private lateinit var faq : ImageView
    private lateinit var acc : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        home = findViewById(R.id.btnHome)
        about = findViewById(R.id.btnAbout)
        faq = findViewById(R.id.btnFaq)
        acc = findViewById(R.id.btnAcc)

        // Set click listeners
        home.setOnClickListener {
            val i = Intent(this@MainActivity9, MainActivity4::class.java)
            startActivity(i)
        }

        about.setOnClickListener {
            val i = Intent(this@MainActivity9, MainActivity6::class.java)
            startActivity(i)
        }

        faq.setOnClickListener {
            val i = Intent(this@MainActivity9, MainActivity9::class.java)
            startActivity(i)
        }

        acc.setOnClickListener {
            val i = Intent(this@MainActivity9, MainActivity8::class.java)
            startActivity(i)
        }

    }
}