package com.example.dipeshlamapersistentstorageassignment1.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.DatabaseConstants

@Dao
interface EmployeeDao {
    @Insert
    fun insert(employee:Employee)

    @Query("SELECT * FROM ${DatabaseConstants.employeeTable}")
    fun getAllEmployees () : List<Employee>

    @Update(onConflict = REPLACE)
    fun updateEmployee (employee: Employee)

    @Query("DELETE FROM ${DatabaseConstants.employeeTable} WHERE id = :employeeId")
    fun deleteEmployee (employeeId : Int)
}