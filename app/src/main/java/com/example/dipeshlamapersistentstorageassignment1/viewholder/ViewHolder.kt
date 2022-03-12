package com.example.dipeshlamapersistentstorageassignment1.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dipeshlamapersistentstorageassignment1.listeners.EditDeleteClickListener
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import kotlinx.android.synthetic.main.employee_list_layout.view.*

class ViewHolder (view : View, listener: EditDeleteClickListener) : RecyclerView.ViewHolder(view) {
    val empName = view.txtEmployeeName
    val empEmail = view.txtEmail
    val empContact = view.txtContact
    val empDomain = view.txtDomain
    val deleteEmployee = view.btnDelete
    val editEmployee = view.btnEdit
    init {
        deleteEmployee.setOnClickListener {
            listener.onDelete(adapterPosition)
        }
        editEmployee.setOnClickListener{
            listener.onEdit(adapterPosition)
        }
    }

    fun bind (employee : Employee){
        empName.text = employee.fullName
        empEmail.text = employee.email
        empContact.text = employee.contact
        empDomain.text = employee.domain
    }
}