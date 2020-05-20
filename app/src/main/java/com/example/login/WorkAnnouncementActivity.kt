package com.example.login

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
    //lateinit var workList : MutableList<Work>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_editor)

        val workItem = findViewById<View>(R.id.bussniesItem)
        val messageItem = findViewById<View>(R.id.messageItem)
        val accountItem = findViewById<View>(R.id.accountItem)
        val alert = AlertDialog.Builder(this)
        val signOutItem = findViewById<View>(R.id.sign_Out_Item)

        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
        messageItem.setOnClickListener {
            println("!!! : message clickt")
        }
        workItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }
        signOutItem.setOnClickListener {
            println("!!! : signout pressd")
            alert.setTitle("Är du säker?")
            alert.setMessage("Vill du logga ut?")
            alert.setPositiveButton("Ja") { dialogInterface: DialogInterface, i: Int ->
                auth.signOut()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            alert.setNegativeButton("Nej") { dialogInterface: DialogInterface, i: Int -> }
            alert.show()
        }


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
             val intent = Intent(this, HeadActivity::class.java)
             startActivity(intent)
         }
    }


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

            db.collection("work").document(user!!.uid).collection("workInfo").add(workInfrormation)
                .addOnSuccessListener {
                    DataManger.work.add(workInfrormation)
                }
                .addOnCanceledListener { }
                .addOnSuccessListener {}
                .addOnFailureListener{ }
    }

}
