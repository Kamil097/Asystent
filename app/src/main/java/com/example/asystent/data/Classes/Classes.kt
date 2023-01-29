package com.example.asystent.data.Classes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "classes_table")
class Classes (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nazwa: String,
    var dzien: String,
    var godzina: String
    ): Parcelable