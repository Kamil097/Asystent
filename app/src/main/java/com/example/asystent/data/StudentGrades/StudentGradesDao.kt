package com.example.asystent.data.StudentGrades

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Student.Student

@Dao
interface StudentGradesDao {
    @Insert
    suspend fun insert(grades: StudentGrades)

    @Query("SELECT * FROM student_table WHERE id LIKE :x")
    fun returnIdStudent(x: Int?): Student

    @Query("SELECT * FROM classes_table WHERE id LIKE :x")
    fun returnIdClasses(x: Int?): Classes

    @Query("SELECT * FROM student_grades WHERE id_class LIKE :x and id_student LIKE :y")
    fun runAllGrades(x: Int?,y:Int?): List<StudentGrades>

    @Query("DELETE FROM student_grades")
    suspend fun deleteGrades()
}