package com.sinx.task
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sinx.core.databinding.AddButtonBinding
import com.sinx.core.di.findComponentDependencies
import com.sinx.task.Constants.TASK_BUNDLE_KEY
import com.sinx.task.Constants.TASK_DATE_BUNDLE_KEY
import com.sinx.task.databinding.TaskListLayoutBinding
import com.sinx.task.di.DaggerTaskComponent
import com.sinx.task.presentation.TaskViewModel
import com.sinx.taskList.TaskItem
import com.sinx.taskList.adapter.TaskListAdapter
import com.sinx.taskList.decoration.DividerItemDecorationTask
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.sinx.core.R as core_R

class TaskListFragment : Fragment(R.layout.task_list_layout) {
    private lateinit var taskListAdapter: TaskListAdapter

    @Inject
    internal lateinit var taskViewModelFactory: Lazy<TaskViewModel.Factory>

    private val viewModel: TaskViewModel by viewModels {
        taskViewModelFactory.get()
    }
    private var _binding: TaskListLayoutBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var _addButtonBinding: AddButtonBinding? = null
    private val addButtonBinding get() = checkNotNull(_addButtonBinding)

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
        _addButtonBinding = AddButtonBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        taskListAdapter = TaskListAdapter(object : TaskListAdapter.OnTaskClickListener {

            override fun onCheckBoxItemClickListener(item: TaskItem, isChecked: Boolean) {
                viewLifecycleOwner.lifecycleScope.launch { viewModel.taskIsDone(item) }
            }

            override fun onTaskTitleClickListener(task: TaskItem) {
                val request = NavDeepLinkRequest.Builder.fromUri(
                    "$INNER_TASK_URI?$TASK_BUNDLE_KEY=${task.name}&$TASK_DATE_BUNDLE_KEY=${task.date}".toUri()
                    )
                    .build()
                findNavController().navigate(request)
            }
        })
        binding.rvTaskList.adapter = taskListAdapter
        binding.rvTaskList.addItemDecoration(
            DividerItemDecorationTask(
                ContextCompat.getDrawable(requireContext(), core_R.drawable.divider)
            )
        )

        val navController =
            Navigation.findNavController(requireActivity(), core_R.id.buttonAddNew)

        lifecycleScope.launchWhenStarted {
            viewModel.navDeepLinkRequest.collect(navController::navigate)
        }

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
        addButtonBinding.buttonAddNew.setOnClickListener {
            viewModel.onClickListenerBottomSheet()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _addButtonBinding = null
    }

    companion object {
        private const val INNER_TASK_URI = "app://task.innerTaskFragment"
    }
}
