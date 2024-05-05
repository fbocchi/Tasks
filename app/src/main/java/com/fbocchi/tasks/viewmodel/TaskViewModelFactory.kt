package com.fbocchi.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fbocchi.tasks.model.dao.TaskDao
import java.lang.IllegalArgumentException

class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(taskDao) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}