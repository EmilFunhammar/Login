package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R.*
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class HeadActivity : AppCompatActivity() {
    // initaliserar var recyclerView
    lateinit var recycleView: RecyclerView
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var value: String
    lateinit var fab1: FloatingActionButton
    lateinit var workType: String
    lateinit var appBar: BottomAppBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_head)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recycleView = findViewById<RecyclerView>(id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = WorkRecycleAdapter(this, DataManger.work)
        appBar = findViewById<BottomAppBar>(id.bottomAppBars)
        fab1 = findViewById<View>(id.floatingActionButton2) as FloatingActionButton
        val user = auth.currentUser
        val docRef = db.collection("Users").document(user!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    workType = document.data?.get("workType").toString()
                    }
                }

            fab1.setOnClickListener { view ->
                intent = Intent(this, WorkAnnouncementActivity::class.java)
                startActivity(intent)
            }
    }

        fun typOfUser() {
            println("QQQ : W")
            if (workType.equals("worker")){
                fab1.hide()
                val messageItem = findViewById<View>(id.messageItem)
                messageItem.setOnClickListener {
                }
                val bussinesItem = findViewById<View>(id.bussniesItem)
                bussinesItem.setOnClickListener {
                    intent = Intent(this, HeadActivity::class.java)
                    startActivity(intent)
                }
                val accountItem = findViewById<View>(id.accountItem)
                accountItem.setOnClickListener {
                    intent = Intent(this, ProfilActivity::class.java)
                    startActivity(intent)
                }
                val locationItem = findViewById<View>(id.locationItem)
                locationItem.setOnClickListener {
                }

            }
            if (workType.equals("employer")){
                appBar.getMenu()
                    .findItem(R.id.locationItem)
                    .setVisible(false)
                appBar.invalidate()
                appBar.getMenu()
                    .findItem(R.id.accountItem)
                    .setVisible(false)
                appBar.invalidate()
                val messageItem = findViewById<View>(id.messageItem)
                messageItem.setOnClickListener {
                    // meddelade intnet sida
                }
                val bussinesItem = findViewById<View>(id.bussniesItem)
                bussinesItem.setOnClickListener {
                    intent = Intent(this, HeadActivity::class.java)
                    startActivity(intent)
                }
                val accountItem = findViewById<View>(id.accountItem)
                accountItem.setOnClickListener {
                    // egen intent liten profil men mest föra tt radera jobb och vissa vad d har
                }
                val signOutItem = findViewById<View>(id.signOutItem)
                signOutItem.setOnClickListener {
                   // auth.signOut()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }


        //overridar och säger till adapter att uopdatera ändringarna
        override fun onResume() {
            super.onResume()
            recycleView.adapter?.notifyDataSetChanged()
        }

}
