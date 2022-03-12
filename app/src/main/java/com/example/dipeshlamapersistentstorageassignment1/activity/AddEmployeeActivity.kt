package com.example.dipeshlamapersistentstorageassignment1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dipeshlamapersistentstorageassignment1.R
import com.example.dipeshlamapersistentstorageassignment1.database.AppDatabase
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.BundleConstants
import kotlinx.android.synthetic.main.activity_add_employee.*

class AddEmployeeActivity : AppCompatActivity(),View.OnClickListener {
    private var isEdit = false
    private lateinit var employeeToUpdate : Employee
    private var employeeId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        checkIfAddOrEdit()
        clickListeners()
    }

    private fun clickListeners(){
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnSave ->{
                saveEmployee()
            }
        }
    }

    private fun saveEmployee (){
        val fullName = edtFullname.text.toString()
        val email = edtEmail.text.toString()
        val contact = edtContact.text.toString()
        val domain = edtContact.text.toString()
        val salary = edtSalary.text.toString()

        if(isValid()){
            if(isEdit){
                val employee = Employee(
                    id= employeeId,
                    fullName=fullName,
                    email=email,contact=contact,
                    salary = salary,
                    domain = domain)
                updateEmployee(employee)

            }else{
                val employee = Employee(
                    fullName = fullName,
                    email = email,
                    contact = contact,
                    salary = salary,
                    domain = domain)
                getAppDatabase()?.getEmployeeInfo()?.insert(employee)
                super.onBackPressed()
            }
        }
    }

    private fun checkIfAddOrEdit(){
        isEdit = intent.getBooleanExtra(BundleConstants.isEdit, false)
        if (isEdit) {
            val employeeId = intent.getIntExtra(BundleConstants.employeeId, 1)
            getEmployeeById(employeeId)
        }
    }

    private fun getEmployeeById(employeeId: Int) {
        val employeeList = getAppDatabase()?.getEmployeeInfo()?.getAllEmployees()

        if (!employeeList.isNullOrEmpty()){
            for (i in employeeList.indices){
                if (employeeList[i].id == employeeId){
                    showEmployeeDetails(employeeList[i])
                }
            }
        }
    }

    private fun showEmployeeDetails(employee: Employee) {
        employeeToUpdate = employee
        employeeId = employee.id
        edtFullname.setText(employee.fullName)
        edtEmail.setText(employee.email)
        edtContact.setText(employee.contact)
        edtDomain.setText(employee.domain)
        edtSalary.setText(employee.salary)
    }

    private fun updateEmployee(employee: Employee) {
        getAppDatabase()?.getEmployeeInfo()?.updateEmployee(employee)
        super.onBackPressed()
    }

    private fun isValid(): Boolean{
        when{
            edtFullname.text.isEmpty() == true ->{
                edtFullname.error = "Please enter the full name"
                edtFullname.requestFocus()
                return false
            }

            edtEmail.text.isEmpty() == true ->{
                edtEmail.error = "Please enter the email"
                edtEmail.requestFocus()
                return false
            }

            edtContact.text.isEmpty() == true ->{
                edtContact.error = "Please enter the phone number"
                edtContact.requestFocus()
                return false
            }

            edtDomain.text.isEmpty() == true ->{
                edtDomain.error = "Please enter the domain"
                edtDomain.requestFocus()
                return false
            }

            edtSalary.text.isEmpty() == true ->{
                edtSalary.error = "Please enter the salary"
                edtSalary.requestFocus()
                return false
            }
        }
        return true
    }

    private fun getAppDatabase() = AppDatabase.getAppDatabase(applicationContext)
}