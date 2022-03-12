package com.example.dipeshlamapersistentstorageassignment1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dipeshlamapersistentstorageassignment1.utils.DatabaseConstants

@Entity (tableName = DatabaseConstants.employeeTable)
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "fullName")
    var fullName: String? = null,
    @ColumnInfo(name = "email")
    var email: String? = null,
    @ColumnInfo(name = "salary")
    var salary : String? = null,
    @ColumnInfo(name = "domain")
    var domain: String? = null,
    @ColumnInfo(name = "contact")
    var contact : String?= null
)
