package com.example.dipeshlamapersistentstorageassignment1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dipeshlamapersistentstorageassignment1.R
import com.example.dipeshlamapersistentstorageassignment1.database.AppDatabase
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.BundleConstants
import kotlinx.android.synthetic.main.activity_add_employee.*
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var employeeToUpdate : Employee
    private var employeeId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getEmployeeById()
        clickListeners()
    }

    private fun clickListeners(){
        btnUpdate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v){
            btnUpdate ->{
                updateEmployee()
            }
        }
    }

    private fun updateEmployee(){
        val fullName = edtUpdateFullname.text.toString()
        val email = edtUpdateEmail.text.toString()
        val contact = edtUpdateContact.text.toString()
        val domain = edtUpdateDomain.text.toString()
        val salary = edtUpdateSalary.text.toString()
        if(isValid()){
            val employee = Employee(
                id = employeeId,
                fullName=fullName,
                email= email,
                contact=contact,
                salary=salary,
                domain = domain
            )
            getAppDatabase()?.getEmployeeInfo()?.updateEmployee(employee)
            super.onBackPressed()
        }
    }

    private fun getEmployeeById(){
        val employeeId= intent.getIntExtra(BundleConstants.employeeId,1)
        val employeeList = getAppDatabase()?.getEmployeeInfo()?.getAllEmployees()
        if(!employeeList.isNullOrEmpty()){
            for (i in employeeList.indices){
                if(employeeList[i].id == employeeId){
                    setEmployeeData(employeeList[i])
                }
            }
        }
    }

    private fun setEmployeeData(employee:Employee){
        employeeToUpdate = employee
        employeeId = employee.id
        edtUpdateFullname.setText(employee.fullName)
        edtUpdateEmail.setText(employee.email)
        edtUpdateContact.setText(employee.contact)
        edtUpdateDomain.setText(employee.domain)
        edtUpdateSalary.setText(employee.salary)
    }

    private fun isValid(): Boolean{
        when{
            edtUpdateFullname.text.isEmpty() == true ->{
                edtUpdateFullname.error = "Please enter the full name"
                edtUpdateFullname.requestFocus()
                return false
            }

            edtUpdateEmail.text.isEmpty() == true ->{
                edtUpdateEmail.error = "Please enter the email"
                edtUpdateEmail.requestFocus()
                return false
            }

            edtUpdateContact.text.isEmpty() == true ->{
                edtUpdateContact.error = "Please enter the phone number"
                edtUpdateContact.requestFocus()
                return false
            }

            edtUpdateDomain.text.isEmpty() == true ->{
                edtUpdateDomain.error = "Please enter the domain"
                edtUpdateDomain.requestFocus()
                return false
            }

            edtUpdateSalary.text.isEmpty() == true ->{
                edtUpdateSalary.error = "Please enter the salary"
                edtUpdateSalary.requestFocus()
                return false
            }
        }
        return true
    }


    private fun getAppDatabase() = AppDatabase.getAppDatabase(applicationContext)

}
