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

class MainActivity10 : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    private lateinit var bookButton: Button
    private lateinit var resetButton: Button
    private lateinit var ic: EditText
    private lateinit var petQuantityEditText: EditText
    private lateinit var petTypeEditText: EditText
    private lateinit var petDescriptionEditText: EditText
    private lateinit var petOptionalEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        bookButton = findViewById(R.id.btnBook2)
        resetButton = findViewById(R.id.btnReset2)
        ic = findViewById(R.id.icNum2)
        petQuantityEditText = findViewById(R.id.eTQuantity)
        petTypeEditText = findViewById(R.id.eTType2)
        petDescriptionEditText = findViewById(R.id.eTDate2)
        petOptionalEditText = findViewById(R.id.eTOptional2)

        dbRef = FirebaseDatabase.getInstance().getReference("Hotel")

        Toast.makeText(this, "Submit", Toast.LENGTH_LONG).show()

        bookButton.setOnClickListener {
            val icNumber = ic.text.toString()
            val petQuantity = petQuantityEditText.text.toString()
            val petType = petTypeEditText.text.toString()
            val petDescription = petDescriptionEditText.text.toString()
            val petOptional = petOptionalEditText.text.toString()

            // Save hotel booking directly to Firebase without generating a unique key
            val hotel = Hotel(icNumber, petQuantity, petType, petDescription, petOptional)

            dbRef.push().setValue(hotel)
                .addOnCompleteListener {
                    Toast.makeText(this, "Hotel booking successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity12::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to book hotel", Toast.LENGTH_SHORT).show()
                }
        }

        resetButton.setOnClickListener {
            ic.setText("")
            petQuantityEditText.setText("")
            petTypeEditText.setText("")
            petDescriptionEditText.setText("")
            petOptionalEditText.setText("")
        }
    }
}
