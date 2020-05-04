package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.firebase.auth.FirebaseAuth

class HeadActivity : AppCompatActivity() {
    // initaliserar var recyclerView
    lateinit var recycleView : RecyclerView
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head)

        recycleView = findViewById<RecyclerView>(R.id.recyclerView)
        //gör att layoutManger läger layouten under varandra

        recycleView.layoutManager = LinearLayoutManager(this)
        //sätter adaptern till min WorkList och skickar med this
        recycleView.adapter = WorkRecycleAdapter(this, DataManger.workList)

        val fab = findViewById<View>(R.id.floatingActionButton2)
        fab.setOnClickListener { view ->
            intent = Intent(this, WorkAnnouncementActivity::class.java)
            startActivity(intent)
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

//overridar och säger till adapter att uopdatera ändringarna
    override fun onResume() {
        super.onResume()
        recycleView.adapter?.notifyDataSetChanged()
    }

}
