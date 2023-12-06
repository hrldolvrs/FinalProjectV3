package com.example.finalprojectv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //database
        val db = Firebase.firestore

        //Edit Text Variables
        var StudentEmail : EditText = findViewById(R.id.signupSchoolEmail)
        var StudentNumber : EditText = findViewById(R.id.signupPhoneNum)
        var StudentFirst : EditText = findViewById(R.id.signupFirstName)
        var StudentLast : EditText = findViewById(R.id.signupLastName)
        var StudentID : EditText = findViewById(R.id.signupID)
        var StudentPassword : EditText = findViewById(R.id.signupPWD)
        var roleSpinner : Spinner = findViewById(R.id.roleSpinner)

        //Button Variable
        var btnSignup : View = findViewById(R.id.signupBttn)

        //SignupButton Function
        btnSignup.setOnClickListener {

            //get the values from the edit text fields
            val email = StudentEmail.text.toString()
            val number = StudentNumber.text.toString()
            val first = StudentFirst.text.toString()
            val last = StudentLast.text.toString()
            val id = StudentID.text.toString()
            val password = StudentPassword.text.toString()
            val role = roleSpinner.selectedItem.toString()

            //create a new user object
            val user = hashMapOf(
                "studentEmail" to email,
                "studentNumber" to number,
                "studentFirst" to first,
                "studentLast" to last,
                "studentId" to id,
                "password" to password,
                "role" to role
            )

            //add the Student user to the database
            db.collection("Students")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding Student", Toast.LENGTH_SHORT).show()
                }

        }

        //Spinner Variables
        val spinnerRole = findViewById<Spinner>(R.id.roleSpinner)
        val roles = arrayOf("Student", "Student")
        val arrayAdap = ArrayAdapter(this@SignupActivity, android.R.layout.simple_spinner_dropdown_item, roles)
        spinnerRole.adapter = arrayAdap

        spinnerRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@SignupActivity, "WOAH BRO YOU PICKED ${roles[p2]} THATS COOL", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@SignupActivity, "bro u picked none wtf", Toast.LENGTH_SHORT).show()
            }
        }
    }

}