package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class EmployesApplicationActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var recyclerView : RecyclerView
    var persons = mutableListOf<Profil>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employes_application)

        supportActionBar?.title = ""

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEmployer)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = EmployeApplicationRecyclerAdapter(this, persons)

       recyclerView.adapter = adapter

        db.collection("applications").document(auth.currentUser?.uid!!).collection("users")
            .addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                persons.clear()
                for (document in snapshot.documents) {
                    val newperson = document.toObject(Profil::class.java)
                    if (newperson != null) {
                        persons.add(newperson)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }




