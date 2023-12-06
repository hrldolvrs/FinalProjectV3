package com.example.finalprojectv3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //firebase
        val db = Firebase.firestore
        val instructors = db.collection("Instructors")
        val students = db.collection("Students")

        var instructorLoggedIn = false
        var studentLoggedIn = false

        //make a variable for username and password
        var txtUsername: EditText = findViewById(R.id.loginUNE)
        var txtPassword: EditText = findViewById(R.id.loginPWD)
        var txtSignUp: TextView = findViewById(R.id.signupText)

        //make a variable for login button
        var btnLogin: Button = findViewById(R.id.loginBttn)

        btnLogin.setOnClickListener {
            try {
                //Instructor Validation email
                instructors.whereEqualTo("email", txtUsername.text.toString())
                    .whereEqualTo("password", txtPassword.text.toString())
                    .get().addOnSuccessListener { result ->
                        if (result != null) {
                            instructorLoggedIn = true
                        }
                    }
                //Instructor Validation ID
                instructors.whereEqualTo("id", txtUsername.text.toString())
                    .whereEqualTo("password", txtPassword.text.toString())
                    .get().addOnSuccessListener { result ->
                        if (result != null) {
                            instructorLoggedIn = true
                        }
                    }

                //Student Validation email
                students.whereEqualTo("studentEmail", txtUsername.text.toString())
                    .whereEqualTo("password", txtPassword.text.toString())
                    .get().addOnSuccessListener { result ->
                        if (result != null) {
                            studentLoggedIn = true
                        }
                    }
                //Student Validation ID
                students.whereEqualTo("studentId", txtUsername.text.toString())
                    .whereEqualTo("password", txtPassword.text.toString())
                    .get().addOnSuccessListener { result ->
                        if (result != null) {
                            studentLoggedIn = true
                        }
                    }

                if (instructorLoggedIn == true) {
                    Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainInstructorActivity::class.java)
                    startActivity(intent)

                } else if (studentLoggedIn == true) {
                    Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
                    val intent2 = Intent(this, MainActivity::class.java)
                    startActivity(intent2)
                } else {
                    Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT)
                    .show();
            }
        }
            txtSignUp.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }

    }
}