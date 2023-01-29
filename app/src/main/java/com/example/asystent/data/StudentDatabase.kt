package com.example.asystent.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Classes.ClassesDao
import com.example.asystent.data.Student.Student
import com.example.asystent.data.Student.StudentDao
import com.example.asystent.data.StudentClasses.StudentClasses
import com.example.asystent.data.StudentGrades.StudentGrades
import com.example.asystent.data.StudentGrades.StudentGradesDao

@Database(entities = [Student::class, Classes::class,StudentClasses::class,StudentGrades::class],version=11, exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun classesDao(): ClassesDao
    abstract fun studentClassesDao(): StudentClassesDao
    abstract fun studentGradesDao(): StudentGradesDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null)
            {return tempInstance}
            synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}