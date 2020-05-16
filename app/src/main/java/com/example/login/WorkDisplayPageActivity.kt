package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
        val work =  DataManger.work[position]
        workTitle.text = work.title
        workDescription.text = work.description
        workSalary.text = work.salary
        workLocation.text = work.workLocation
        employerName.text = work.employerName
        employerPhoneNumber.text = work.employerPhoneNumber
        employerEmail.text = work.employerEmail
    }

   /* fun displayWork2(studentPosition: Int) {

        val shoppingItems = mutableListOf<Work>()

        val user = auth.currentUser
        val itemsRef = db.collection("work").document(user!!.uid)
            .collection("workInfo")

        itemsRef.addSnapshotListener { snapshot, e ->
            if( snapshot != null ) {
                shoppingItems.clear()
                for(document in snapshot.documents) {
                    val newItem = document.toObject(Work::class.java)
                    if (newItem != null)
                        shoppingItems.add(newItem)
                }
            }
        }
    }*/

   /* fun set(){
        val user = auth.currentUser ?: return
        val workList = mutableListOf<Work>()
        val workRef = db.collection("work").document(user.uid)

        workRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                workList.clear()
                val newItem = snapshot.toObject(Work::class.java)
                if (newItem != null) {
                    workList.add(newItem)
                    setProfilInfo(newItem)
                }
            }
        }
    }


    fun setProfilInfo(newItem: Work) {
        //val work = DataManger.workList[position]
        workTitle.text = newItem.employerName
        /*workDescription.text = work.description
        workSalary.text = work.salary
        workLocation.text = work.workLocation
        employerName.text = work.employerName
        employerPhoneNumber.text = work.employerPhoneNumber
        employerEmail.text = work.employerEmail

    }*/
    */

}
