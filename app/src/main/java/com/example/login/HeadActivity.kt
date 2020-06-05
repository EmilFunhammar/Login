package com.example.login

//import DataManger

import android.R
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R.id
import com.example.login.R.layout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class HeadActivity : AppCompatActivity() {
    lateinit var recycleView: RecyclerView
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var value: String
    var workType: String = ""
    var workList = mutableListOf<Work>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_head)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // hämtar vilken typ av användare som är inloggad
        db.collection("Users").document(user!!.uid).get().addOnSuccessListener { document ->
            if (document != null) {
                workType = document.data?.get("workType").toString()
                println("!!! : $workType")
                typOfUser()
            }
        }

        recycleView = findViewById<RecyclerView>(id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = WorkRecycleAdapter(this, workList)

            // läger till i den lokala listan från firebase
            val itemsRef = db.collection("work")
            itemsRef.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    workList.clear()
                    for (document in snapshot.documents){
                    val newItem = document.toObject(Work::class.java)
                    if (newItem != null) {
                        workList.add(newItem)
                        }
                        recycleView.adapter?.notifyDataSetChanged()
                    }
                }
            }

        val fab = findViewById<FloatingActionButton>(id.floatingActionButton)
        val workItem = findViewById<View>(id.bussniesItem)
        val messageItem = findViewById<View>(id.messageItem)
        val mapsItem = findViewById<View>(id.locationItem)
        val accountItem = findViewById<View>(id.accountItem)

        fab.setOnClickListener {
            intent = Intent(this, WorkAnnouncementActivity::class.java)
            startActivity(intent)
        }
        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
        messageItem.setOnClickListener {
            intent = Intent(this, EmployesApplicationActivity::class.java)
            startActivity(intent)
        }
        workItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }
        mapsItem.setOnClickListener {
            intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    // ändrar layouten för olika användare
    fun typOfUser() {
        if (workType.equals("worker")){
            val fab = findViewById<FloatingActionButton>(id.floatingActionButton)
            val signOutItem = findViewById<View>(id.sign_Out_Item)
            val messageItem = findViewById<View>(id.messageItem)
            messageItem.visibility = View.GONE
            signOutItem.visibility = View.GONE
            fab.hide()
        }
        if (workType.equals("employer")){
            val fab = findViewById<FloatingActionButton>(id.floatingActionButton)
            val alert = AlertDialog.Builder(this)
            val signOutItem = findViewById<View>(id.sign_Out_Item)
            val mapsItem = findViewById<View>(id.locationItem)
            val accountItem = findViewById<View>(id.accountItem)
            mapsItem.visibility = View.GONE
            accountItem.visibility = View.GONE
            invalidateOptionsMenu()
            signOutItem.setOnClickListener {
                println("!!! : signout pressd")
                alert.setTitle("Är du säker?")
                alert.setMessage("Vill du logga ut?")
                alert.setPositiveButton("Ja") { dialogInterface: DialogInterface, i: Int ->
                    auth.signOut()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                alert.setNegativeButton("Nej") { dialogInterface: DialogInterface, i: Int -> }
                alert.show()
            }
        }
    }

    //overridar och säger till adapter att uopdatera ändringarna
    override fun onResume() {
        super.onResume()
        recycleView.adapter?.notifyDataSetChanged()
    }
}




