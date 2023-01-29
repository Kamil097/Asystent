package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.data.StudentClasses.StudentClasses

@Dao
interface StudentClassesDao {
    @Insert
    suspend fun insert(studentClasses: StudentClasses)

    @Query("DELETE FROM student_classes_table")
    suspend fun deleteStudentsClasses()
}