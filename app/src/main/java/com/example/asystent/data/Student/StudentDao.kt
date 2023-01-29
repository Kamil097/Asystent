package com.example.asystent.data.Student

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.asystent.data.Student.Student
import com.example.asystent.data.StudentClasses.StudentClasses
import com.example.asystent.data.StudentGrades.StudentGrades

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT id_student FROM student_classes_table) ORDER BY id ASC")
    fun readClassStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id IN (SELECT id_student FROM student_classes_table) ORDER BY id ASC")
    fun readCurrentClass(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id IN (SELECT id_student FROM student_classes_table WHERE id_classes = :x)")
    fun studentsInClass(x:Int?): List<Student>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT id_student FROM student_classes_table WHERE id_classes = :x)")
    fun studentsNotInClass(x:Int?): List<Student>

    @Query("SELECT * FROM student_grades WHERE id_class LIKE :x and id_student LIKE :y")
    fun runAllGrades(x: Int?,y:Int?): List<StudentGrades>

    @Query("DELETE FROM student_table")
    suspend fun deleteStudents()
}