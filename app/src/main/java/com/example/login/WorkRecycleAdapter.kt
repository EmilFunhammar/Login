package com.example.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkRecycleAdapter(private val context: Context, private val workAdvertisement : List<Work>) : RecyclerView.Adapter<WorkRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val itemView = layoutInflater.inflate(R.layout.head_listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return workAdvertisement.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val advertisment = workAdvertisement[position]
        holder.textViewTitle.text = advertisment.title
        holder.textViewDecription.text = advertisment.description
        holder.textViewSalary.text = advertisment.salary.toString()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle = itemView.findViewById<TextView>(R.id.titleText)
        val textViewDecription = itemView.findViewById<TextView>(R.id.decriptionText)
        val textViewSalary = itemView.findViewById<TextView>(R.id.salaryText)
    }


}