package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class WorkAnnouncementActivity : AppCompatActivity() {

    lateinit var salaryText : EditText
    lateinit var titleText : EditText
    lateinit var descriptionText : EditText
    lateinit var locationText : EditText
    lateinit var nameText : EditText
    lateinit var phoneNUmber : EditText
    lateinit var emailAdressText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_editor)

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

    fun addNewJob(){

        val workTitle = titleText.text.toString()
        val workSalary = salaryText.text.toString()
        val workDescription = descriptionText.text.toString()
        val workLocation = locationText.text.toString()
        val employerName = nameText.text.toString()
        val employerPhoneNumber = phoneNUmber.text.toString()
        val employerEmail = emailAdressText.text.toString()


        val addWork = Work(workTitle, workSalary, workDescription, workLocation,
            employerName, employerPhoneNumber, employerEmail)

        /*val x = listOf(addWork.toString())
        var i : String
        for (i in x) {
            Log.d("!!!", "loppar")
        }*/
        DataManger.workList.add(addWork)
            finish()
    }

}
