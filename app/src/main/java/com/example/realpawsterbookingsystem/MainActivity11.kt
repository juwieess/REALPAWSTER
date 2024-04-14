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

class MainActivity11 : AppCompatActivity() {

    private lateinit var bIc : TextView
    private lateinit var bName : TextView
    private lateinit var bType : TextView
    private lateinit var bDate : TextView
    private lateinit var home : Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        // Initialize Firebase components
        dbRef = FirebaseDatabase.getInstance().getReference("Book")

        bIc = findViewById(R.id.icNum)
        bName = findViewById(R.id.petName)
        bType = findViewById(R.id.petType)
        bDate = findViewById(R.id.appDate)
        home = findViewById(R.id.btnHome2)

        dbRef.orderByChild("Book").limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val bookingDataSnapshot = dataSnapshot.children.last()

                    val icNumber = bookingDataSnapshot.child("icNumber").getValue(String::class.java)
                    val petN = bookingDataSnapshot.child("petN").getValue(String::class.java)
                    val petT = bookingDataSnapshot.child("petT").getValue(String::class.java)
                    val petD = bookingDataSnapshot.child("petD").getValue(String::class.java)

                    // Display user data in the profile page UI
                    bIc.text = icNumber
                    bName.text = petN
                    bType.text = petT
                    bDate.text = petD

                } else {
                    Toast.makeText(this@MainActivity11, "User data not found", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("MainActivity11", "User data not found")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                Toast.makeText(
                    this@MainActivity11,
                    "Failed to retrieve user data: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("MainActivity11", "Failed to retrieve user data: ${databaseError.message}")
            }
        })

        home.setOnClickListener {
            val i = Intent(this@MainActivity11, MainActivity4::class.java)
            startActivity(i)
        }
    }
}
