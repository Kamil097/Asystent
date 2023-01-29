package com.example.asystent.data.Student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent.data.StudentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Student>>
    val readClassStudents: LiveData<List<Student>>
    val readCurrentClass: LiveData<List<Student>>
    private val repository: StudentRepository

    init{
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        readAllData = repository.readAllData
        readClassStudents = repository.readClassStudents
        readCurrentClass = repository.readCurrentClass()

    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            repository.addStudent(student)
        }
    }

    fun runAllGrades(x: Int?,y:Int?){
        viewModelScope.launch(Dispatchers.IO){
            repository.runAllGrades(x,y)
        }
    }
}