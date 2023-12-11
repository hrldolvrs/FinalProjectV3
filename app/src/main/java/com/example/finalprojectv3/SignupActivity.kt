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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    private lateinit var StudentEmail: EditText
    private lateinit var StudentNumber: EditText
    private lateinit var StudentFirst: EditText
    private lateinit var StudentLast: EditText
    private lateinit var StudentID: EditText
    private lateinit var StudentPassword: EditText
    private lateinit var StudentRole: Spinner


    //Edit Text Variables
    /**val studentEmail = findViewById<EditText>(R.id.signupSchoolEmail).text.toString()
    val studentNumber = findViewById<EditText>(R.id.signupPhoneNum).text.toString()
    val studentFirst = findViewById<EditText>(R.id.signupFirstName).text.toString()
    val studentLast = findViewById<EditText>(R.id.signupLastName).text.toString()
    val studentID = findViewById<EditText>(R.id.signupID).text.toString()
    val studentPassword = findViewById<EditText>(R.id.signupPWD).text.toString()
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        //database

        //Edit Text Variables

        StudentEmail = findViewById(R.id.signupSchoolEmail)
        StudentNumber = findViewById(R.id.signupPhoneNum)
        StudentFirst = findViewById(R.id.signupFirstName)
        StudentLast = findViewById(R.id.signupLastName)
        StudentID = findViewById(R.id.signupID)
        StudentPassword = findViewById(R.id.signupPWD)
        StudentRole = findViewById(R.id.roleSpinner)

        /**var StudentEmail: EditText = findViewById(R.id.signupSchoolEmail)
        var StudentNumber: EditText = findViewById(R.id.signupPhoneNum)
        var StudentFirst: EditText = findViewById(R.id.signupFirstName)
        var StudentLast: EditText = findViewById(R.id.signupLastName)
        var StudentID: EditText = findViewById(R.id.signupID)
        var StudentPassword: EditText = findViewById(R.id.signupPWD)
        **/

        //val db = Firebase.firestore


        //Button Variable
        var btnSignup: View = findViewById(R.id.signupBttn)

        //database variable
        dbRef = FirebaseDatabase.getInstance().getReference("Students")

        //SignupButton Function
        btnSignup.setOnClickListener {
            /**
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
            }**/

            /**if (StudentEmail.text.toString().isEmpty() ||
                StudentNumber.text.toString().isEmpty() ||
                StudentFirst.text.toString().isEmpty() ||
                StudentLast.text.toString().isEmpty() ||
                StudentID.text.toString().isEmpty() ||
                StudentPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()

            } else {**/

                auth.createUserWithEmailAndPassword(
                    StudentEmail.text.toString(),
                    StudentPassword.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        /**get the values from the edit text fields
                        val email = StudentEmail.text.toString()
                        val number = StudentNumber.text.toString()
                        val first = StudentFirst.text.toString()
                        val last = StudentLast.text.toString()
                        val id = StudentID.text.toString()
                        val password = StudentPassword.text.toString()
                        val role = roleSpinner.selectedItem.toString()
                        val isStudent = "1"**/
                        saveStudentData()
                        intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        /**val user = hashMapOf(
                            "studentEmail" to email,
                            "studentNumber" to number,
                            "studentFirst" to first,
                            "studentLast" to last,
                            "studentId" to id,
                            "password" to password,
                            "role" to role,
                            "isStudent" to isStudent
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

                        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT)
                            .show()
                        intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)**/

                    }

                }

            //}

        }

            //Spinner Variables
            val spinnerRole = findViewById<Spinner>(R.id.roleSpinner)
            val roles = arrayOf("Student", "Student")
            val arrayAdap = ArrayAdapter(
                this@SignupActivity,
                android.R.layout.simple_spinner_dropdown_item,
                roles
            )
            spinnerRole.adapter = arrayAdap

            spinnerRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(
                        this@SignupActivity,
                        "WOAH BRO YOU PICKED ${roles[p2]} THATS COOL",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@SignupActivity, "bro u picked none wtf", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    private fun saveStudentData() {
        val empEmail = StudentEmail.text.toString()
        val empNumber = StudentNumber.text.toString()
        val empFirst = StudentFirst.text.toString()
        val empLast = StudentLast.text.toString()
        val empID = StudentID.text.toString()
        val empPassword = StudentPassword.text.toString()
        val empRole = StudentRole.selectedItem.toString()

        if (empEmail.isEmpty()) {
            StudentEmail.error = "Please enter a name"
        }
        if (empNumber.isEmpty()) {
            StudentNumber.error = "Please enter a number"
        }
        if (empFirst.isEmpty()) {
            StudentFirst.error = "Please enter a first name"
        }
        if (empLast.isEmpty()) {
            StudentLast.error = "Please enter a last name"
        }
        if (empID.isEmpty()) {
            StudentID.error = "Please enter an ID"
        }
        if (empPassword.isEmpty()) {
            StudentPassword.error = "Please enter a password"
        }
        if (empRole.isEmpty()) {
            Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show()
        }

        val studentId = dbRef.push().key!!

        val empDetails = StudentModel(studentId, empEmail, empNumber, empFirst, empLast, empID, empPassword, empRole)
        dbRef.child(studentId).setValue(empDetails).addOnCompleteListener{
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
        }

    }

}