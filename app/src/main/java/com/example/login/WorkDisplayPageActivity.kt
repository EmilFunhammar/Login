package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_work_editor.*
import java.text.ParsePosition

const val POSITION_NOT_SET = -1
const val Work_POSETION_KEY = "WORK"

class WorkDisplayPageActivity : AppCompatActivity() {

    lateinit var workTitle : TextView
    lateinit var workDescription : TextView
    lateinit var workSalary : TextView
    lateinit var workLocation : TextView
    lateinit var employerName : TextView
    lateinit var employerPhoneNumber : TextView
    lateinit var employerEmail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_display_page)

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
        val work = DataManger.workList[position]
        workTitle.text = work.title
        workDescription.text = work.description
        workSalary.text = work.salary
        workLocation.text = work.workLocation
        employerName.text = work.employerName
        employerPhoneNumber.text = work.employerPhoneNumber.toString()
        employerEmail.text = work.employerEmail





    }
}
