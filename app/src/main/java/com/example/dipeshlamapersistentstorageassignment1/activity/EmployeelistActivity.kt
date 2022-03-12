package com.example.dipeshlamapersistentstorageassignment1.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dipeshlamapersistentstorageassignment1.R
import com.example.dipeshlamapersistentstorageassignment1.adapter.Adapter
import com.example.dipeshlamapersistentstorageassignment1.database.AppDatabase
import com.example.dipeshlamapersistentstorageassignment1.listeners.EditDeleteClickListener
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.BundleConstants
import kotlinx.android.synthetic.main.activity_employeelist.*

class EmployeelistActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var employeeList : MutableList<Employee>
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
        employeeList = ArrayList()
       empAdapter= Adapter(employeeList, object: EditDeleteClickListener{

            override fun onEdit(position: Int) {
                val employeeId=employeeList[position].id
                val intent = Intent (this@EmployeelistActivity, AddEmployeeActivity::class.java)
                intent.putExtra(BundleConstants.isEdit, true)
                intent.putExtra(BundleConstants.employeeId, employeeId)
                startActivity(intent)
            }
            override fun onDelete(position: Int) {
                val employeeId = employeeList[position].id
                deleteEmployee(employeeId)
            }
        })
        rycEmployeesList.adapter = empAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getEmployeeFromDB(){
        val employeeListFromDb = getAppDatabase()?.getEmployeeInfo()?.getAllEmployees()
        employeeList.clear()
        employeeList.addAll(employeeListFromDb as MutableList<Employee>)
        empAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v){
            fabAddEmployee -> {
                startActivity(Intent(this,AddEmployeeActivity::class.java))
            }
        }
    }

    private fun deleteEmployee(empId : Int){
        getAppDatabase()?.getEmployeeInfo()?.deleteEmployee(empId)
        getEmployeeFromDB()
    }

    private fun getAppDatabase() = AppDatabase.getAppDatabase(applicationContext)
}