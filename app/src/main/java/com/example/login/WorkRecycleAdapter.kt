package com.example.login

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WorkRecycleAdapter(private val context: Context, private val work : List<Work>) :
    RecyclerView.Adapter<WorkRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    lateinit var db : FirebaseFirestore
    lateinit var auth: FirebaseAuth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val itemView = layoutInflater.inflate(R.layout.head_listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return work.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val advertisement = work[position]

      /*  val shoppingItems = mutableListOf<Work>()

        val itemsRef = db.collection("work").document(user!!.uid).collection("workInforamtion")

        itemsRef.addSnapshotListener { snapshot, e ->
            if( snapshot != null ) {
                println("emil : Inne")
                shoppingItems.clear()
                for(document in snapshot.documents) {
                    val newItem = document.toObject(Work::class.java)
                    if (newItem != null)
                        shoppingItems.add(newItem)
                    // println("!!! : ${newItem}")
                    println("emil1 : ${newItem!!.description}")

                    // langText2.text = newItem.profilName

                }
            }
        }*/
        holder.textViewTitle.text = advertisement.title
        holder.textViewDecription.text = advertisement.description
        holder.textViewSalary.text = advertisement.salary
        holder.workPosition = position
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle = itemView.findViewById<TextView>(R.id.titleText)
        val textViewDecription = itemView.findViewById<TextView>(R.id.decriptionText)
        val textViewSalary = itemView.findViewById<TextView>(R.id.salaryText)
        var workPosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, WorkDisplayPageActivity::class.java)
                intent.putExtra(Work_POSETION_KEY, workPosition)
                context.startActivity(intent)
            }
        }
           // itemView.setOnClickListener{
            //    val intent = Intent(context, AddAndCreateStudentActivity::class.java)
             //   intent.putExtra(STUDENT_POSETION_KEY, studentPosition)
              //  context.startActivity(intent)

        }
    }



