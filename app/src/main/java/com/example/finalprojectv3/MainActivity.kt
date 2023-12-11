package com.example.finalprojectv3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.finalprojectv3.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth :FirebaseAuth
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        databaseReference = Firebase.database.reference

        val currentUser = Firebase.auth.currentUser
        var studentId = auth.currentUser?.uid.toString()


        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val header = navigationView?.getHeaderView(0)
        //NAV HEADER
        val textFullname = header?.findViewById<TextView>(R.id.txtSName)
        val textEmail = header?.findViewById<TextView>(R.id.txtSEmail)

        if (textFullname != null && textEmail != null) {

            textFullname.text = auth.currentUser?.displayName.toString()
            textEmail.text = auth.currentUser?.email.toString()

        }

        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeStudentFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home_student)
        }

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home_student -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeStudentFragment()).commit()
            R.id.nav_profile_student -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileStudentFragment()).commit()
            R.id.nav_courses_student -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CoursesStudentFragment()).commit()
            R.id.nav_requests_student -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RequestsStudentFragment()).commit()
            R.id.nav_logout_student -> { Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun readData(empId: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        databaseReference.child(empId).get().addOnSuccessListener{
            if(it.exists()){

                val studentName = it.child("empFirst").value.toString()
                val studentEmail = it.child("empEmail").value.toString()

            }
        }

    }

}
