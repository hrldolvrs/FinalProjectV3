package com.example.finalprojectv3
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainInstructorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    //private lateinit var binding: ActivityMainInstructorBinding
    //private lateinit var databaseReference: DatabaseReference
    //private lateinit var binding: ActivityMainInstructorBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**binding = ActivityMainInstructorBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)**/

        setContentView(R.layout.activity_main_instructor)


        //Fire Database Declaration
        database = Firebase.database.reference
        //val db = Firebase.firestore
        //val instructors = db.collection("Instructors")



        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val header = navigationView?.getHeaderView(0)

        val textFullname = header?.findViewById<TextView>(R.id.txtSName)
        val textEmail = header?.findViewById<TextView>(R.id.txtPEmail)

        if (textFullname != null && textEmail != null) {

            

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

    fun writeUsername(first: String, last: String){

        database.child("first").setValue(first)
        database.child("last").setValue(last)

    }

    val postListener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val post = snapshot.getValue()
            Log.d("TAG", post.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w("TAG", "loadPost:onCancelled", error.toException())
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
