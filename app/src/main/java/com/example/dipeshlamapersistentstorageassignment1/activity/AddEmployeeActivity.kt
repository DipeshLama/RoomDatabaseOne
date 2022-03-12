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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
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
        val domain = edtDomain.text.toString()
        val salary = edtSalary.text.toString()

        if(isValid()){
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