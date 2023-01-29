package com.example.asystent.data.Student

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName : String,
    val lastName : String,
    val album_number : Int,
    var isSelected: Boolean = false
):Parcelable