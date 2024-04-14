package com.example.realpawsterbookingsystem

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity4 : AppCompatActivity() {

    private lateinit var book: Button
    private lateinit var hotel : Button
    private lateinit var home : ImageView
    private lateinit var about : ImageView
    private lateinit var faq : ImageView
    private lateinit var acc : ImageView
    private lateinit var shop : ImageView
    private lateinit var feedback : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        book = findViewById(R.id.btnBook)
        hotel = findViewById(R.id.btnHotel)
        home = findViewById(R.id.btnHome)
        about = findViewById(R.id.btnAbout)
        faq = findViewById(R.id.btnFaq)
        acc = findViewById(R.id.btnAcc)
        shop = findViewById(R.id.btnShop)
        feedback = findViewById(R.id.btnFeedback)


        // Set click listeners
        book.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity5::class.java)
            startActivity(i)
        }

        hotel.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity10::class.java)
            startActivity(i)
        }

        home.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity4::class.java)
            startActivity(i)
        }

        about.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity6::class.java)
            startActivity(i)
        }

        faq.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity9::class.java)
            startActivity(i)
        }

        acc.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity8::class.java)
            startActivity(i)
        }

        shop.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity13::class.java)
            startActivity(i)
        }

        feedback.setOnClickListener {
            val i = Intent(this@MainActivity4, MainActivity7::class.java)
            startActivity(i)
        }


    }

}