package com.example.login

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder.textViewTitle.text = workList[position].title
        holder.textViewSalary.text = workList[position].salary.toString()
        holder.textViewDecription.text = workList[position].description
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
    }

    }



