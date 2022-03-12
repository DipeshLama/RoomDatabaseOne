package com.example.dipeshlamapersistentstorageassignment1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dipeshlamapersistentstorageassignment1.R
import com.example.dipeshlamapersistentstorageassignment1.listeners.EditDeleteClickListener
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.viewholder.ViewHolder

class Adapter (var list : ArrayList<Employee>, var listener: EditDeleteClickListener) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_list_layout,parent,false)
        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = list[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}