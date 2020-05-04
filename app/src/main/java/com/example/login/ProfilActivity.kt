package com.example.login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class ProfilActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val editProfilButton = findViewById<Button>(R.id.edit_Button)
        editProfilButton.setOnClickListener {
            editProfil()
        }

        val accountItem = findViewById<View>(R.id.accountItem)
        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        //val locationItem = findViewById<View>(R.id.locationItem)
        //locationItem.setOnClickListener {
        //     intent = Intent(this, )
        // }

        val bussinesItem = findViewById<View>(R.id.bussniesItem)
        bussinesItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }

        // val messageItem = findViewById<View>(R.id.messageItem)
        // messageItem.setOnClickListener {
        // }

    }

    fun editProfil(){
        intent = Intent(this, ProfilEditorActivity::class.java)
        startActivity(intent)
    }

}
