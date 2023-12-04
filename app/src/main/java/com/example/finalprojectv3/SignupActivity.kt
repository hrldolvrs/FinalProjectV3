package com.example.finalprojectv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val spinnerRole = findViewById<Spinner>(R.id.roleSpinner)
        val roles = arrayOf("Student", "Instructor")
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