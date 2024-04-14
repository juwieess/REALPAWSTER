package com.example.realpawsterbookingsystem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity12 : AppCompatActivity() {

    private lateinit var ic : TextView
    private lateinit var pQuantity : TextView
    private lateinit var pType : TextView
    private lateinit var pStay : TextView
    private lateinit var pOpt : TextView
    private lateinit var home : Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)

        // Initialize Firebase components
        dbRef = FirebaseDatabase.getInstance().getReference("Hotel")

        ic = findViewById(R.id.icNumber)
        pQuantity = findViewById(R.id.petQuantity)
        pType = findViewById(R.id.petType2)
        pStay = findViewById(R.id.petStay)
        pOpt = findViewById(R.id.petOption)
        home = findViewById(R.id.btnHome3)

        dbRef.orderByChild("Hotel").limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val bookingDataSnapshot = dataSnapshot.children.last()

                    val icNumber = bookingDataSnapshot.child("icNumber").getValue(String::class.java)
                    val petQ = bookingDataSnapshot.child("petQ").getValue(String::class.java)
                    val petT = bookingDataSnapshot.child("petT").getValue(String::class.java)
                    val petD = bookingDataSnapshot.child("petD").getValue(String::class.java)
                    val petO = bookingDataSnapshot.child("petO").getValue(String::class.java)

                    // Display user data in the profile page UI
                    ic.text = icNumber
                    pQuantity.text = petQ
                    pType.text = petT
                    pStay.text = petD
                    pOpt.text = petO
                } else {
                    Toast.makeText(this@MainActivity12, "User data not found", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("MainActivity12", "User data not found")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                Toast.makeText(
                    this@MainActivity12,
                    "Failed to retrieve user data: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("MainActivity12", "Failed to retrieve user data: ${databaseError.message}")
            }
        })

        home.setOnClickListener {
            val i = Intent(this@MainActivity12, MainActivity4::class.java)
            startActivity(i)
        }
    }
}

