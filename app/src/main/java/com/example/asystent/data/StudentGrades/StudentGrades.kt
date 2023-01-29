package com.example.asystent.data.StudentGrades

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "student_grades")
class StudentGrades (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var id_student: Int,
    var id_class: Int,
    var grade: Int
)