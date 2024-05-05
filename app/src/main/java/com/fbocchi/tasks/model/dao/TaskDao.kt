package com.fbocchi.tasks.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fbocchi.tasks.model.entity.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Update
    suspend fun update(task: Task) // Update the task with the matching id (as 'task')

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    fun get(id: Long): LiveData<Task> // !!!

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): LiveData<List<Task>>

}