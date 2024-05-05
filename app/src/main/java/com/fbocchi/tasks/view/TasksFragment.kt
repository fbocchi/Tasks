package com.fbocchi.tasks.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fbocchi.tasks.databinding.FragmentTasksBinding
import com.fbocchi.tasks.model.database.TasksDatabase
import com.fbocchi.tasks.viewmodel.TaskViewModel
import com.fbocchi.tasks.viewmodel.TaskViewModelFactory

class TasksFragment : Fragment() {

    // View and data binding
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    // ...

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // ...
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        // Database and Dao
        val application = requireNotNull(this.activity).application
        val taskDao = TasksDatabase.getInstance(application).taskDao

        // ViewModel
        val taskViewModelFactory = TaskViewModelFactory(taskDao)
        val taskViewModel = ViewModelProvider(this, taskViewModelFactory)
            .get(TaskViewModel::class.java)

        // Data binding
        binding.taskViewModel = taskViewModel

        // ...
        binding.lifecycleOwner = viewLifecycleOwner // IMPORTANTE: altrimenti data binding non funziona!

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}