package com.example.asystent.data.Student

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.asystent.data.Student.Student
import com.example.asystent.data.Student.StudentDao
import com.example.asystent.data.StudentGrades.StudentGrades

class StudentRepository(private val studentDao: StudentDao) {

    val readAllData: LiveData<List<Student>> = studentDao.readAllData()
    val readClassStudents: LiveData<List<Student>> = studentDao.readClassStudents()
    fun readCurrentClass(): LiveData<List<Student>> = studentDao.readCurrentClass()

    suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    fun runAllGrades(x: Int?,y:Int?) {
        studentDao.runAllGrades(x,y)
    }
}