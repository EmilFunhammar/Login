package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HeadActivity : AppCompatActivity() {
    // initaliserar var recyclerView
    lateinit var recycleView : RecyclerView

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
            //DataManger.workList.add(Work("inköp", "555", "inte så mycket"))
            intent = Intent(this, WorkAnnouncementActivity::class.java)
            startActivity(intent)
        }
    }
//overridar och säger till adapter att uopdatera ändringarna
    override fun onResume() {
        super.onResume()
        recycleView.adapter?.notifyDataSetChanged()
    }

}
