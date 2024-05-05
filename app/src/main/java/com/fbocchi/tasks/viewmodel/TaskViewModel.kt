package com.fbocchi.tasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.fbocchi.tasks.model.dao.TaskDao
import com.fbocchi.tasks.model.entity.Task
import kotlinx.coroutines.launch

class TaskViewModel(val taskDao: TaskDao) : ViewModel() {

    var savedTaskName = ""

    private val tasks = taskDao.getAll()
    val tasksString: LiveData<String> = tasks.map { taskList ->
        taskList.joinToString(separator = "\n") { task ->
            // Converti ogni Task in una stringa rappresentativa
            task.toString()
        }
    }

    /*val tasksString = tasks.switchMap { tasks ->
        MutableLiveData<String>().apply {
            value = formatTasks(tasks)
        }
    }

    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {
            str, item -> str + "\n" + formatTask(item)
        }
    }

    private fun formatTask(task: Task): String {
        var str = "ID: ${task.id}\n"
        str += "Name: ${task.name}\n"
        str += "Done: ${task.done}\n"
        return str
    }*/

    fun saveTask() {
        viewModelScope.launch {
            val task = Task()
            task.name = savedTaskName
            taskDao.insert(task)
        }
    }

}