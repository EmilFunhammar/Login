package com.example.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


lateinit var db :FirebaseFirestore
lateinit var auth: FirebaseAuth

class ProfilActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val profilTitelText = findViewById<TextView>(R.id.rubrik)
        val ratingBar = findViewById<RatingBar>(R.id.rating)
        val tasksPreformdText = findViewById<TextView>(R.id.tasks)
        val langText1 = findViewById<TextView>(R.id.lang)
        val langText2 = findViewById<TextView>(R.id.lang1)
        val langText3 = findViewById<TextView>(R.id.lang2)
        val langText4 = findViewById<TextView>(R.id.lang3)
        val generalInfoText1 = findViewById<TextView>(R.id.genarall1)
        val generalInfoText2 = findViewById<TextView>(R.id.generall2)
        val generalInfoText3 = findViewById<TextView>(R.id.genarall3)
        val generalInfoText4 = findViewById<TextView>(R.id.generall4)
        val descriptionText = findViewById<TextView>(R.id.decriptionText)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser ?: return

        val shoppingItems = mutableListOf<Profil>()

        val itemsRef = db.collection("Users").document(user.uid).collection("userInformation")

        itemsRef.addSnapshotListener { snapshot, e ->
            if( snapshot != null ) {
                println("emil : Inne")
                shoppingItems.clear()
                for(document in snapshot.documents) {
                    val newItem = document.toObject(Profil::class.java)
                    if (newItem != null)
                        shoppingItems.add(newItem)
                    // println("!!! : ${newItem}")
                    println("emil : ${newItem!!.profilName}")

                    // langText2.text = newItem.profilName

                }
            }
        }

       /* println("!!! : Emil")
        itemsRef.document(user.uid).collection("userInformation")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("!!! : Emil2")
                    for (document in task.result!!) {
                        println("!!! : Emil3")
                        Log.d("!!!",
                        document.id + " => " + document.data)
                    }
                } else {println("!!! : Emil4")
                    Log.w(
                       "!!!",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }*/


        /*  itemsRef.addSnapshotListener { snapshot, e ->
              if( snapshot != null ) {
                  for(document in snapshot.documents) {
                      val newItem = document.toObject(Profil::class.java)
                      if (newItem != null)
                          shoppingItems.add(newItem!!)
                      if (newItem != null) {
                          println("David : ${newItem}")
                      }
                  }
              }
          }*/


        val fab = findViewById<View>(R.id.floatingActionButtonProfilPage)
        fab.setOnClickListener { view ->
            intent = Intent(this, ProfilEditorActivity::class.java)
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
    }

    fun displayUserInfo(){

    }
}
