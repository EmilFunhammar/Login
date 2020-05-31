package com.example.login

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WorkRecycleAdapter(
    private val context: Context, private val workList: MutableList<Work>) :
    RecyclerView.Adapter<WorkRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.head_listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val work = workList[position]
        holder.textViewTitle.text = work.title
        holder.textViewSalary.text = work.salary.toString()
        holder.textViewDecription.text = work.description
        if (work.userUid!!.equals(auth.currentUser?.uid)){
            holder.deleteButton.visibility = View.VISIBLE
        }else{
            holder.deleteButton.visibility = View.GONE
        }
        holder.deleteButton.setOnClickListener {
            if (work.userUid!!.equals(auth.currentUser?.uid)) {
                work.documentId?.let { it1 ->
                    db.collection("work").document(it1).delete().addOnSuccessListener {
                    }
                }
            }
        }
        holder.workPosition = position
        if (work.userUid!!.equals(auth.currentUser?.uid)) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.userWorkColor))
        }else{
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.notUserWorkColor))
        }


    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.titleText)
        val textViewDecription = itemView.findViewById<TextView>(R.id.decriptionText)
        val textViewSalary = itemView.findViewById<TextView>(R.id.salaryText)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete_imageview)
        var workPosition = 0


        init {

            itemView.setOnClickListener {
                val value = workList[workPosition]
                    val intent = Intent(context, WorkDisplayPageActivity::class.java)
                    intent.putExtra("WORK_KEY", value)
                    context.startActivity(intent)
                }
            }
        }
    }





/*if (work.userUid.equals(auth.uid)){
    holder.textViewTitle.setBackgroundColor(
        ContextCompat.getColor(
            context,
            R.color.colorPrimaryDark
        )
    )
}*/



