package com.example.realpawsterbookingsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity8 : AppCompatActivity() {

    private lateinit var pEmail: TextView
    private lateinit var pName: TextView
    private lateinit var password : TextView
    private lateinit var phone: TextView
    private lateinit var home : ImageView
    private lateinit var about : ImageView
    private lateinit var faq : ImageView
    private lateinit var acc : ImageView
    private lateinit var contact : Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Customer")

        // Initialize TextViews
        pEmail = findViewById(R.id.profEmail)
        pName = findViewById(R.id.profName)
        password = findViewById(R.id.profPassword)
        phone = findViewById(R.id.profNumber)
        home = findViewById(R.id.btnHome)
        about = findViewById(R.id.btnAbout)
        faq = findViewById(R.id.btnFaq)
        acc = findViewById(R.id.btnAcc)
        contact = findViewById(R.id.btnContact)


        // Retrieve user data from Firebase
        val userId = mAuth.currentUser?.uid
        userId?.let { uid ->
            dbRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val customerId = dataSnapshot.child("customerId").getValue(String::class.java)
                        val email = dataSnapshot.child("email").getValue(String::class.java)
                        val name = dataSnapshot.child("name").getValue(String::class.java)
                        val customerPassword = dataSnapshot.child("customerPassword").getValue(String::class.java)
                        val phoneNumber = dataSnapshot.child("phoneNumber").getValue(String::class.java)

                        // Display user data in the profile page UI
                        pEmail.text = email
                        pName.text = name
                        password.text = customerPassword
                        phone.text = phoneNumber


                    } else {
                        Toast.makeText(this@MainActivity8, "User data not found", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("MainActivity8", "User data not found")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                    Toast.makeText(
                        this@MainActivity8,
                        "Failed to retrieve user data: ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("MainActivity8", "Failed to retrieve user data: ${databaseError.message}")
                }
            })
            }

        home.setOnClickListener {
            val i = Intent(this@MainActivity8, MainActivity4::class.java)
            startActivity(i)
        }

        about.setOnClickListener {
            val i = Intent(this@MainActivity8, MainActivity6::class.java)
            startActivity(i)
        }

        faq.setOnClickListener {
            val i = Intent(this@MainActivity8, MainActivity9::class.java)
            startActivity(i)
        }

        acc.setOnClickListener {
            val i = Intent(this@MainActivity8, MainActivity8::class.java)
            startActivity(i)
        }
        contact.setOnClickListener {
            val i = Intent(this@MainActivity8, MainActivity14::class.java)
            startActivity(i)
        }

        }
}