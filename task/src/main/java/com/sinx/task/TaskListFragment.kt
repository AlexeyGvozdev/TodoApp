package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.sinx.core.di.findComponentDependencies
import com.sinx.task.databinding.TaskListLayoutBinding
import com.sinx.task.di.DaggerTaskComponent
import com.sinx.task.presentation.TaskViewModel
import com.sinx.taskList.TaskItem
import com.sinx.taskList.adapter.TaskListAdapter
import com.sinx.taskList.decoration.DividerItemDecorationTask
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskListFragment : Fragment(R.layout.task_list_layout) {

    private lateinit var taskListAdapter: TaskListAdapter

    @Inject
    internal lateinit var taskViewModelFactory: Lazy<TaskViewModel.Factory>

    private val viewModel: TaskViewModel by viewModels {
        taskViewModelFactory.get()
    }

    private var _binding: TaskListLayoutBinding? = null
    private val binding: TaskListLayoutBinding
        get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerTaskComponent.builder().deps(findComponentDependencies())
            .build()
            .inject(this)
        if (savedInstanceState == null) {
            viewModel.initialize()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TaskListLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        taskListAdapter = TaskListAdapter(object : TaskListAdapter.OnTaskClickListener {

            override fun onCheckBoxItemClickListener(item: TaskItem, isChecked: Boolean) {
                lifecycleScope.launch {
                    viewModel.taskIsDone(item)
                }
            }
        })
        binding.rvTaskList.adapter = taskListAdapter
        binding.rvTaskList.addItemDecoration(
            DividerItemDecorationTask()
        )
        lifecycleScope.launchWhenStarted {
            viewModel.taskList.collect { item ->
                val empty = item.isEmpty()
                binding.rvTaskList.isGone = empty
                binding.ivNoTasks.isVisible = empty
                binding.tvNoTasks.isVisible = empty
                taskListAdapter.submitList(item)
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            addTask.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(ADD_TASK_URI.toUri())
                    .build()
                findNavController().navigate(request)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ADD_TASK_URI = "app://task/addTaskFragment"
    }
}