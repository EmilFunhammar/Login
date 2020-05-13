package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profil_editor.*
import kotlinx.android.synthetic.main.activity_work_editor.*

class WorkAnnouncementActivity : AppCompatActivity() {


    lateinit var salaryText : EditText
    lateinit var titleText : EditText
    lateinit var descriptionText : EditText
    lateinit var locationText : EditText
    lateinit var nameText : EditText
    lateinit var phoneNUmber : EditText
    lateinit var emailAdressText : EditText
    lateinit var db : FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_editor)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        salaryText = findViewById<EditText>(R.id.salaryNumber)
        titleText = findViewById<EditText>(R.id.workTitle)
        descriptionText = findViewById<EditText>(R.id.work_Dicription)
        locationText = findViewById<EditText>(R.id.addLocation)
        nameText = findViewById<EditText>(R.id.editTextName)
        phoneNUmber = findViewById<EditText>(R.id.editTextPhoneNummber)
        emailAdressText = findViewById<EditText>(R.id.editEmailText)

        val saveButton = findViewById<Button>(R.id.save_Button)
         saveButton.setOnClickListener {
             addNewJob()
         }
    }

   /* fun addNewJob(){

        val workTitle = titleText.text.toString()
        val workSalary = salaryText.text.toString()
        val workDescription = descriptionText.text.toString()
        val workLocation = locationText.text.toString()
        val employerName = nameText.text.toString()
        val employerPhoneNumber = phoneNUmber.text.toString()
        val employerEmail = emailAdressText.text.toString()


        val addWork = Work(workTitle, workSalary, workDescription, workLocation,
            employerName, employerPhoneNumber, employerEmail)

        DataManger.workList.add(addWork)
            finish()
    }*/

    fun addNewJob(){

            val workInfrormation = Work(/*profilPicture,*/
                titleText.text.toString(),
                salaryText.text.toString(),
                descriptionText.text.toString(),
                locationText.text.toString(),
                nameText.text.toString(),
                phoneNUmber.text.toString(),
                emailAdressText.text.toString())
            val user = auth.currentUser
            if (user == null)
                return

            db.collection("work").document(user!!.uid).collection("workInformation").add(workInfrormation)
                .addOnSuccessListener { println("!!!: complete")}
                .addOnCanceledListener { println("!!!: cancel")}
                .addOnSuccessListener { println("!!!: write")
                    DataManger.workList.add(workInfrormation)
                    finish()
                }
                .addOnFailureListener{ println("!!!: did not write")}


    }

}
