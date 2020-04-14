package com.example.login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    lateinit var rubrik: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)


        val resivd = intent.getStringExtra("currentUser")


        rubrik = findViewById(R.id.rubrik)


        rubrik.text = resivd
    }

}
