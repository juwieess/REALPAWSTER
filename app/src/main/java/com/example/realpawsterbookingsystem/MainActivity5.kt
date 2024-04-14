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

class MainActivity5 : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    private lateinit var bookButton: Button
    private lateinit var resetButton: Button
    private lateinit var ic: EditText
    private lateinit var petNameEditText: EditText
    private lateinit var petTypeEditText: EditText
    private lateinit var petDateEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        bookButton = findViewById(R.id.btnBooking)
        resetButton = findViewById(R.id.btnReset1)
        ic = findViewById(R.id.eTIdentification)
        petNameEditText = findViewById(R.id.eTPet)
        petTypeEditText = findViewById(R.id.eTType)
        petDateEditText = findViewById(R.id.eTDate)

        dbRef = FirebaseDatabase.getInstance().getReference("Book")

        Toast.makeText(this, "Submit", Toast.LENGTH_LONG).show()

        bookButton.setOnClickListener {
            val icNumber = ic.text.toString()
            val petName = petNameEditText.text.toString()
            val petType = petTypeEditText.text.toString()
            val petDate = petDateEditText.text.toString()

            val book = Book(icNumber, petName, petType, petDate)

            dbRef.push().setValue(book)
                .addOnCompleteListener {
                    Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity11::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to book", Toast.LENGTH_SHORT).show()
                }
        }

        resetButton.setOnClickListener {
            ic.setText("")
            petNameEditText.setText("")
            petTypeEditText.setText("")
            petDateEditText.setText("")
        }
    }
}
