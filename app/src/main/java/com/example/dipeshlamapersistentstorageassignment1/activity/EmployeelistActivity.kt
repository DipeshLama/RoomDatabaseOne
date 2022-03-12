package com.example.dipeshlamapersistentstorageassignment1.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dipeshlamapersistentstorageassignment1.R
import com.example.dipeshlamapersistentstorageassignment1.adapter.Adapter
import com.example.dipeshlamapersistentstorageassignment1.database.AppDatabase
import com.example.dipeshlamapersistentstorageassignment1.listeners.EditDeleteClickListener
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.BundleConstants
import kotlinx.android.synthetic.main.activity_employeelist.*

class EmployeelistActivity : AppCompatActivity(),View.OnClickListener,EditDeleteClickListener {
    var employeeList = ArrayList<Employee>()
    private lateinit var empAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeelist)
        populateRecyclerView()
        clickListener()
    }

    override fun onResume() {
        super.onResume()
        getEmployeeFromDB()
    }

    private fun clickListener(){
        fabAddEmployee.setOnClickListener(this)
    }

    private fun populateRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        rycEmployeesList.layoutManager = layoutManager
        empAdapter= Adapter(employeeList, this)
        rycEmployeesList.adapter = empAdapter
    }

    private fun getEmployeeFromDB(){
        val employeeListFromDb = getAppDatabase()?.getEmployeeInfo()?.getAllEmployees()
        employeeList.clear()
        employeeList.addAll(employeeListFromDb as ArrayList<Employee>)
        empAdapter.notifyDataSetChanged()
    }


    private fun deleteEmployee(empId : Int){
        getAppDatabase()?.getEmployeeInfo()?.deleteEmployee(empId)
        getEmployeeFromDB()
    }

    private fun getAppDatabase() = AppDatabase.getAppDatabase(applicationContext)


    // Override functions
    override fun onClick(v: View?) {
        when (v){
            fabAddEmployee -> {
                startActivity(Intent(this,AddEmployeeActivity::class.java))
            }
        }
    }

    override fun onEdit(position: Int) {
        val employeeId=employeeList[position].id
        val intent = Intent (this@EmployeelistActivity, UpdateActivity::class.java)
        intent.putExtra(BundleConstants.employeeId, employeeId)
        startActivity(intent)
    }

    override fun onDelete(position: Int) {
        val employeeId = employeeList[position].id
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete User")
        builder.setMessage("Are you sure you want to delete this employee?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            deleteEmployee(employeeId)
            Toast.makeText(this, "Employee has been removed", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Cancel"){dialogInterface,which ->
            Toast.makeText(this, "Operation cancelled", Toast.LENGTH_LONG).show()
        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}