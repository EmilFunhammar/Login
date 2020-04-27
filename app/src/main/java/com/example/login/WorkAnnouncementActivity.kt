package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class WorkAnnouncementActivity : AppCompatActivity() {

    lateinit var salary : EditText
    lateinit var title : EditText
    lateinit var  diskription : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_announcement)

        val saveButton = findViewById<Button>(R.id.save_Button)
        salary = findViewById<EditText>(R.id.salaryNumber)
        title = findViewById<EditText>(R.id.workTitle)
        diskription = findViewById<EditText>(R.id.work_Dicription)
         saveButton.setOnClickListener { view ->
             addNewJob()
         }
    }

    fun addNewJob(){
        val sal = salary.text.toString()
        val ti =title.text.toString()
        val des = diskription.text.toString()

        val work = Work(ti, sal, des)
        DataManger.workList.add(work)
        finish()
    }


}
