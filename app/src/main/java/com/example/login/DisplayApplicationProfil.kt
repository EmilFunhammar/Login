package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.ParsePosition

const val  POSETION_NOT_SET = -1
const val PERSON_POSETION_KEY = "STUDENT_POSITION"

class DisplayApplicationProfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_application_profil)

        var personPosition = intent.getIntExtra(PERSON_POSETION_KEY, POSETION_NOT_SET)
        personPosition = -1
        if (personPosition != POSITION_NOT_SET) {
        }

        if (personPosition != POSITION_NOT_SET){
            displayProfil(personPosition)
        }
    }

    fun displayProfil(position: Int){
    }
}
