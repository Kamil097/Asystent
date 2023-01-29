package com.example.asystent.data.Classes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent.data.StudentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassesViewModel(application: Application): AndroidViewModel(application) {

    val readAllclassData: LiveData<List<Classes>>
    private val repository: ClassesRepository

    init{
        val classesDao = StudentDatabase.getDatabase(application).classesDao()
        repository = ClassesRepository(classesDao)
        readAllclassData = repository.readAllclassData
    }

    fun addClass(classes: Classes){
        viewModelScope.launch(Dispatchers.IO){
            repository.addClass(classes)
        }
    }
}