package com.example.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class WorkDisplayPageActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var workTitle : TextView
    lateinit var workDescription : TextView
    lateinit var workSalary : TextView
    lateinit var workLocation : TextView
    lateinit var employerName : TextView
    lateinit var employerPhoneNumber : TextView
    lateinit var employerEmail : TextView
    lateinit var applayButton: Button
    var workType: String = ""
    //val value = intent.getParcelableExtra<Work>("WORK_KEY")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_display_page)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        db.collection("Users").document(user!!.uid).get().addOnSuccessListener { document ->
            if (document != null) {
                workType = document.data?.get("workType").toString()
                typOfUser()
            }
        }

        supportActionBar?.title = ""



        applayButton = findViewById<Button>(R.id.applay_Work)
        applayButton.setOnClickListener {
            downloadUserInformation()
        }


        employerName = findViewById<TextView>(R.id.displayEmployerName)
        employerPhoneNumber = findViewById<TextView>(R.id.displayPhoneNumber)
        employerEmail = findViewById<TextView>(R.id.displayEmail)
        workLocation = findViewById<TextView>(R.id.displayLocation)
        workTitle = findViewById<TextView>(R.id.displayTitleText)
        workDescription = findViewById<TextView>(R.id.displayDiscriptionText)
        workSalary = findViewById<TextView>(R.id.displaySalaryText)

        val value = intent.getParcelableExtra<Work>("WORK_KEY")
        if (value != null) {
            employerName.text = value.employerName
            employerPhoneNumber.text = value.employerPhoneNumber
            employerEmail.text = value.employerEmail
            workLocation.text = value.workLocation
            workTitle.text = value.title
            workDescription.text = value.description
            workSalary.text = value.salary + " Kronor"
        }
    }

    private fun typOfUser() {
        if (workType == "worker"){

        }
        if(workType == "employer"){
            applayButton.visibility = View.INVISIBLE
        }

    }

    private fun downloadUserInformation() {
        val user = auth.currentUser ?: return
        val shoppingItems = mutableListOf<Profil>()
        val itemsRef = db.collection("Users").document(user.uid)

        itemsRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                shoppingItems.clear()
                val newItem = snapshot.toObject(Profil::class.java)
                if (newItem != null) {
                    sendApplayRequest(newItem)

                }
            }
        }
    }

    private fun sendApplayRequest(newitem: Profil){
        val value = intent.getParcelableExtra<Work>("WORK_KEY")
        val profilInformation = Profil(
            newitem.userImageUri,
            newitem.profilName,
            newitem.editLanguage1,
            newitem.editLanguage2,
            newitem.editLanguage3,
            newitem.profilOtherInfo1,
            newitem.profilOtherInfo2,
            newitem.profilOtherInfo3,
            newitem.profilOtherInfo4,
            newitem.profilDescription,
            newitem.workType
        )

        db.collection("applications").document(value.userUid!!)
            .collection("users").add(profilInformation).addOnCompleteListener {

                Toast.makeText(baseContext, "Ansökan skickad",
                    Toast.LENGTH_LONG).show()
                finish()
            }
    }
}
