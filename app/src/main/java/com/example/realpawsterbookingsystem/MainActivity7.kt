package com.example.realpawsterbookingsystem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity7 : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var submitButton: Button
    private lateinit var resetButton: Button
    private lateinit var feedbackEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        submitButton = findViewById(R.id.btnSubmit)
        resetButton = findViewById(R.id.btnReset3)
        feedbackEditText = findViewById(R.id.eTFeedback)

        dbRef = FirebaseDatabase.getInstance().getReference("Feedback")

        submitButton.setOnClickListener {
            val feedbackText = feedbackEditText.text.toString()

            // Save feedback directly to Firebase without generating a unique key
            val feedback = Feedback(feedbackText)

            dbRef.push().setValue(feedback)
                .addOnCompleteListener {
                    Toast.makeText(this, "Feedback submitted!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity7::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show()
                }
        }

        resetButton.setOnClickListener {
            feedbackEditText.setText("")
        }
    }
}
