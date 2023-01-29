package com.example.asystent.data.Classes

import androidx.lifecycle.LiveData
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Classes.ClassesDao


class ClassesRepository(private val classesDao: ClassesDao) {
    val readAllclassData: LiveData<List<Classes>> = classesDao.readAllclassData()

    suspend fun addClass(classes: Classes){
        classesDao.addClass(classes)
    }
}