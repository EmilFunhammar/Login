package com.example.login

//import DataManger
import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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


class HeadActivity : AppCompatActivity() {
    // initaliserar var recyclerView
    lateinit var recycleView: RecyclerView
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var value: String
    lateinit var fab: FloatingActionButton
    var workType: String = ""
    lateinit var appBar: BottomAppBar
    lateinit var accountItem: View
    //lateinit var workList: MutableList<Work>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_head)

        /*try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }*/

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recycleView = findViewById<RecyclerView>(id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        //
        //
        //recycleView.adapter = WorkRecycleAdapter(this, workList)
        //
        //


            val user = auth.currentUser
            /*val itemsRef = db.collection("work")

            itemsRef.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    workList.clear()
                    for (document in documents){
                    val newItem = document.toObject(Work::class.java)
                    if (newItem != null) {
                        workList.add(newItem)

                        }
                        recycleView.adapter?.notifyDataSetChanged()
                    }
                }
            }*/

        // snapshotLisentet




       /* David Svensson:balloon:  1:43 PM
        db.collection("work").snapshotListener().on complet {
            work.clear()
            for (doc in documts) {
                val a =doc.toObject(work::class.java)
                work.add(a)
            }
            recycleView.adapter.notifyDactachanged()
        }*/
        fab = findViewById<FloatingActionButton>(id.floatingActionButton)
        val workItem = findViewById<View>(id.bussniesItem)
        val messageItem = findViewById<View>(id.messageItem)
        accountItem = findViewById<BottomAppBar>(id.accountItem)
        //val alert = AlertDialog.Builder(this)
        val signOutItem = findViewById<View>(id.sign_Out_Item)

//       signOutItem.invalidate()


        val docRef = db.collection("Users").document(user!!.uid)
        docRef.get().addOnSuccessListener{ document ->
            if (document != null) {
                workType = document.data?.get("workType").toString()
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
            intent = Intent(this, EmployesApplicationActivity::class.java)
            startActivity(intent)
        }
        workItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }
        /*signOutItem.setOnClickListener {
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
        }*/



    }


   fun typOfUser() {
        if (workType.equals("worker")){
            fab.hide()
        }
        if (workType.equals("employer")){
           /* println("!!! : employer")
           bottomAppBars.getMenu()
                .findItem(id.locationItem)
                .setVisible(false)
            bottomAppBars.invalidate()
            bottomAppBars.getMenu()
                .findItem(id.accountItem)
                .setVisible(false)
            bottomAppBars.invalidate()*/

        }
        //return
    }


    //overridar och säger till adapter att uopdatera ändringarna
    override fun onResume() {
        super.onResume()
        recycleView.adapter?.notifyDataSetChanged()
    }

}


