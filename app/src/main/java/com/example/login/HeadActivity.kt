package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HeadActivity : AppCompatActivity() {

    var workAdvertisement = listOf<Work>(
        Work("grävare", 1500, "gräva groppar"),
        Work("pilot", 5000, "flyga plan"),
        Work("zoo keeper", 450, "mata djuren"),
        Work("elektiker", 1200, "dra kabel"),
        Work("applikations utvecklare", 550, "programmera appar"),
        Work("grävare", 1500, "gräva groppar"),
        Work("pilot", 5000, "flyga plan"),
        Work("zoo keeper", 450, "mata djuren"),
        Work("elektiker", 1200, "dra kabel"),
        Work("applikations utvecklare", 550, "programmera appar"),
        Work("grävare", 1500, "gräva groppar"),
        Work("pilot", 5000, "flyga plan"),
        Work("zoo keeper", 450, "mata djuren"),
        Work("elektiker", 1200, "dra kabel"),
        Work("applikations utvecklare", 550, "programmera appar"),
        Work("grävare", 1500, "gräva groppar"),
        Work("pilot", 5000, "flyga plan"),
        Work("zoo keeper", 450, "mata djuren"),
        Work("elektiker", 1200, "dra kabel"),
        Work("applikations utvecklare", 550, "programmera appar"),
        Work("grävare", 1500, "gräva groppar"),
        Work("pilot", 5000, "flyga plan"),
        Work("zoo keeper", 450, "mata djuren"),
        Work("elektiker", 1200, "dra kabel"),
        Work("applikations utvecklare", 550, "programmera appar"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head)

        val recycleView = findViewById<RecyclerView>(R.id.recyclerView)

        recycleView.layoutManager = LinearLayoutManager(this)

        val adapter = WorkRecycleAdapter(this, workAdvertisement)

        recycleView.adapter = adapter



    }

}
