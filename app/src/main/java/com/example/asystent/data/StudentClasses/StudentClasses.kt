package com.example.asystent.data.StudentClasses

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_classes_table")
data class StudentClasses (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var id_student: Int,
    var id_classes: Int?
):Parcelable