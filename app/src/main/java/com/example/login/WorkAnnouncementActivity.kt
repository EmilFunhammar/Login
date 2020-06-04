package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

class WorkAnnouncementActivity : AppCompatActivity() {


    lateinit var salaryText: EditText
    lateinit var titleText: EditText
    lateinit var descriptionText: EditText
    lateinit var locationText: EditText
    lateinit var nameText: EditText
    lateinit var phoneNUmber: EditText
    lateinit var emailAdressText: EditText
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    private lateinit var locationProviderClient: FusedLocationProviderClient
    lateinit var workList: MutableList<Work>
    private val REQUEST_LOCATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_editor)


        supportActionBar?.title = ""

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val getLocation = findViewById<ImageButton>(R.id.get_Location)
        salaryText = findViewById<EditText>(R.id.salaryNumber)
        titleText = findViewById<EditText>(R.id.workTitle)
        descriptionText = findViewById<EditText>(R.id.work_Dicription)
        locationText = findViewById<EditText>(R.id.addLocation)
        nameText = findViewById<EditText>(R.id.editTextName)
        phoneNUmber = findViewById<EditText>(R.id.editTextPhoneNummber)
        emailAdressText = findViewById<EditText>(R.id.editEmailText)

        val saveButton = findViewById<Button>(R.id.save_Button)
        saveButton.setOnClickListener {
            addNewWork()
        }

        getLocation.setOnClickListener {
            getCurrentLocation()
        }
    }

    fun getCurrentLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION
            )
        } else {
            locationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude
                    val lng = location.longitude
                }
            }
        }
    }

    private fun addNewWork() {
        val user = auth.currentUser
        if (user != null) {
            val workInfo = Work(
                titleText.text.toString(),
                salaryText.text.toString(),
                descriptionText.text.toString(),
                locationText.text.toString(),
                nameText.text.toString(),
                phoneNUmber.text.toString(),
                emailAdressText.text.toString(),
                user.uid
            )
            db.collection("work").add(workInfo).addOnCompleteListener {
                Toast.makeText(baseContext, "Jobb skapad",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                println("!!! : permission granted")
                locationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude
                        val lng = location.longitude
                    } else {
                        println("!!! : permission not granted")
                    }
                }
            }
        }
    }
}
