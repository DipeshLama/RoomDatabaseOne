package com.example.dipeshlamapersistentstorageassignment1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dipeshlamapersistentstorageassignment1.dao.EmployeeDao
import com.example.dipeshlamapersistentstorageassignment1.model.Employee
import com.example.dipeshlamapersistentstorageassignment1.utils.DatabaseConstants

@Database(
    entities =[Employee::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getEmployeeInfo () : EmployeeDao

    companion object{
        private var appDatabase:AppDatabase? = null

        fun getAppDatabase (context : Context?)=
            if(appDatabase !=null){
                appDatabase
            }else{
                if(context != null) {
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DatabaseConstants.databaseName
                    )
                        .allowMainThreadQueries()
                        .build()
                }else{
                    null
                }
            }
    }
}