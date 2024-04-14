package com.example.realpawsterbookingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity3 : AppCompatActivity() {
    //declare to connect with database
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    //initial all component
    private lateinit var btnReg: Button
    private lateinit var btnRes: Button
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //declare all component
        btnReg = findViewById(R.id. btnRegister)
        btnRes = findViewById(R.id. btnReset)
        name = findViewById(R.id. name)
        phone = findViewById(R.id. phoneNumber)
        email = findViewById(R.id. email)
        password = findViewById(R.id. password)

        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Customer")


        // Set OnClickListener for signup
        btnReg.setOnClickListener {
            val email = email.text.toString()
            val name = name.text.toString()
            val password = password.text.toString()
            val phone = phone.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = mAuth.currentUser?.uid ?: ""
                        val customer = Model(email, name, password, phone)
                        dbRef.child(userId).setValue(customer)
                            .addOnCompleteListener {
                                Toast.makeText(this, "User registration successful", Toast.LENGTH_SHORT).show()
                                // Optional: You can call saveData function here if you want to execute it separately.
                                // saveData(nameStr, phoneStr, emailStr, passwordStr, confirmPasswordStr)
                                val intent = Intent(this, MainActivity2::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Failed to register user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }


        btnRes.setOnClickListener {
            name.setText(" ")
            phone.setText(" ")
            email.setText(" ")
            password.setText(" ")
        }

    }
    //create the function saveData
    //this function send data to firebase
    //n = name , ph = phone and etc
    private fun saveData(email:String,name:String,password:String,phone:String) {
        //getInstance = get object
        //customer refer to table
        //customer can change to other name

        dbRef = FirebaseDatabase.getInstance().getReference("Customer")
        //produce auto generate customer id
        //refer must had record or id cannot null
        val customerId = dbRef.push().key!!

        //customer is a object
        //push the data to database
        //customerId will auto generate
        //data will output by user
        //input name, phone, email, password, confirm password
        val em = Model(email , name , password , phone )

        dbRef.child(customerId).setValue(em)

            //success record, it will popup success
            .addOnCompleteListener {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                //fail to record, it will be popup failure
            }.addOnFailureListener{
                Toast.makeText(this, "Failure",Toast.LENGTH_LONG).show()
            }
        //declare variable i to connect to the next pages/activity
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}