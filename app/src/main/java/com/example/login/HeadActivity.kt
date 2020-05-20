package com.example.login

import DataManger
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
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_head.*
import kotlinx.android.synthetic.main.activity_profil.*


class HeadActivity : AppCompatActivity() {
    // initaliserar var recyclerView
    lateinit var recycleView: RecyclerView
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var value: String
    lateinit var fab: FloatingActionButton
    var workType: String = ""
   // lateinit var appBar: BottomAppBar
    lateinit var accountItem: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_head)

         try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recycleView = findViewById<RecyclerView>(id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        //
        //
        recycleView.adapter = WorkRecycleAdapter(this, DataManger.work)
        //
        //

        fab = findViewById<FloatingActionButton>(id.floatingActionButton)
        val workItem = findViewById<View>(id.bussniesItem)
        val messageItem = findViewById<View>(id.messageItem)
        accountItem = findViewById<BottomAppBar>(id.accountItem)
        val alert = AlertDialog.Builder(this)
        val signOutItem = findViewById<View>(id.sign_Out_Item)

        val user = auth.currentUser
        val docRef = db.collection("Users").document(user!!.uid)
        docRef.get().addOnSuccessListener{ document ->
            if (document != null) {
                workType = document.data?.get("workType").toString()
                println("!!! : $workType")
                typOfUser()

            }
        }

        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }
       fab.setOnClickListener {
            intent = Intent(this, WorkAnnouncementActivity::class.java)
           startActivity(intent)
        }
        messageItem.setOnClickListener {
            println("!!! : message clickt")
        }
        workItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }
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


   fun typOfUser() {
        println("!!! : typeOfUser equals worker")
        if (workType.equals("worker")){
            //fab.hide()
            accountItem.setOnClickListener {
                intent = Intent(this, ProfilActivity::class.java)
                startActivity(intent)
            }*/
        }
        if (workType.equals("employer")){
           bottomAppBars.getMenu()
                .findItem(R.id.locationItem)
                .setVisible(false)
            bottomAppBars.invalidate()

            bottomAppBars.getMenu()
                .findItem(R.id.accountItem)
                .setVisible(false)
            bottomAppBars.invalidate()

        }
        println("!!! : typeOfUser lämngs ner")
        return
    }


    //overridar och säger till adapter att uopdatera ändringarna
    override fun onResume() {
        super.onResume()
        recycleView.adapter?.notifyDataSetChanged()
    }

}


