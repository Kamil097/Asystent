package com.example.asystent.data.Classes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.asystent.data.Classes.Classes

@Dao
interface ClassesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addClass(classes: Classes)

    @Query("SELECT * FROM classes_table ORDER BY id ASC")
    fun readAllclassData(): LiveData<List<Classes>>

    @Query("DELETE FROM classes_table;")
    suspend fun deleteClasses()
}