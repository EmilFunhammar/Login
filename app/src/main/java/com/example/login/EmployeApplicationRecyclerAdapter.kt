package com.example.login

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class EmployeApplicationRecyclerAdapter(private val context: Context, private var persons: List<Profil>) : RecyclerView.Adapter<EmployeApplicationRecyclerAdapter.ViewHolder>() {


    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.employeapplication, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  persons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val auth = FirebaseAuth.getInstance()

        val db = FirebaseFirestore.getInstance()
    val person = persons[position]
        holder.textName.text = person.profilName
        holder.deleteButton.setOnClickListener {
                person.documentId?.let { it1 ->
                    db.collection("application").document(auth.currentUser?.uid!!)
                        .collection("users").document(it1).delete().addOnSuccessListener {
                    }
                }

        }
        /*holder.deleteButton.setOnClickListener {
            person.documentId?.let { it1 ->
                db.collection("application").document(auth.currentUser?.uid!!)
                    .collection("users").document(it1).delete().addOnSuccessListener {
                        println("!!! : värdet ${person.documentId}")
                        println("!!! : värdet på it1 $it1")
                    }

            }
        }*/

        holder.personPosition = position
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById<TextView>(R.id.card_View_Name)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete_Button)
        var personPosition = 0


        init {

            itemView.setOnClickListener {
                val value = persons[personPosition]

                println("!!!: persons $value")
            val intent = Intent(context, DisplayApplicationProfil::class.java)
                //intent.putExtra(PERSON_POSiTION_KEY, personPosition)
                intent.putExtra("AnyNameOrKey", value)
                context.startActivity(intent)

            }
        }

    }

}


