package com.example.login

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

const val POSITION_NOT_SET = -1
const val Work_POSETION_KEY = "WORK"

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
    lateinit var workList : MutableList<Work>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_display_page)

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

        employerName = findViewById<TextView>(R.id.displayEmployerName)
        employerPhoneNumber = findViewById<TextView>(R.id.displayPhoneNumber)
        employerEmail = findViewById<TextView>(R.id.displayEmail)
        workLocation = findViewById<TextView>(R.id.displayLocation)
        workTitle = findViewById<TextView>(R.id.displayTitleText)
        workDescription = findViewById<TextView>(R.id.displayDiscriptionText)
        workSalary = findViewById<TextView>(R.id.displaySalaryText)

        val studentPosition = intent.getIntExtra(Work_POSETION_KEY, POSITION_NOT_SET)
        displayWork(studentPosition)
    }

    fun displayWork(position: Int){
        val work =  workList[position]
        workTitle.text = work.title
        workDescription.text = work.description
        workSalary.text = work.salary
        workLocation.text = work.workLocation
        employerName.text = work.employerName
        employerPhoneNumber.text = work.employerPhoneNumber.toString()
        employerEmail.text = work.employerEmail
    }

}
