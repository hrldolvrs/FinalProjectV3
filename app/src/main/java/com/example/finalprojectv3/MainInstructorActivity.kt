package com.example.finalprojectv3
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.finalprojectv3.databinding.ActivityMainInstructorBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import org.w3c.dom.EntityReference
import org.w3c.dom.Text

class MainInstructorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    //private lateinit var binding: ActivityMainInstructorBinding
    //private lateinit var databaseReference: DatabaseReference
    //private lateinit var binding: ActivityMainInstructorBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**binding = ActivityMainInstructorBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)**/

        setContentView(R.layout.activity_main_instructor)


        //Fire Database Declaration
        val db = Firebase.firestore
        val instructors = db.collection("Instructors")



        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val header = navigationView?.getHeaderView(0)

        val textFullname = header?.findViewById<TextView>(R.id.txtName)
        val textEmail = header?.findViewById<TextView>(R.id.txtEmail)

        if (textFullname != null && textEmail != null) {
            //set text na here

        }

        /**val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.getHeaderView(0);

        var textName = navigationView.findViewById<TextView>(R.id.txtName)
        var textEmail = navigationView.findViewById<TextView>(R.id.txtEmail)

        textName.setText("This is a name").toString()**/

        //set the text views to the values from the database
        /**instructors.whereEqualTo("id", instructors.id)
        .get().addOnSuccessListener { result ->
                for (document in result) {
                    textName.text =
                        document.data["first"].toString() + " " + document.data["last"].toString()
                    textEmail.text = document.data["email"].toString()
                }
            }

        **/


        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeInstructorFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home_instructor)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home_instructor -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeInstructorFragment()).commit()
            R.id.nav_profile_instructor -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileInstructorFragment()).commit()
            R.id.nav_students_instructor -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentsInstructorFragment()).commit()
            R.id.nav_requests_instructor -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RequestsInstructorFragment()).commit()
            R.id.nav_logout_instructor -> { Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
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

    /**private fun readData(first: String, last: String){

        databaseReference = FirebaseDatabase.getInstance().getReference("first")
        databaseReference.child(first).get().addOnSuccessListener {

            if(it.exists()){

                val firstname = it.child("first").value

            }

        }

    }**/
}
