package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sinx.core.databinding.PriorityButtonBinding
import com.sinx.task.databinding.AddTaskLayoutBinding
import com.sinx.task.presentation.TaskViewModel
import com.sinx.task.presentation.TaskViewModelFactory

class AddTaskFragment : Fragment(R.layout.add_task_layout) {
    private var _binding: AddTaskLayoutBinding? = null
    private val binding: AddTaskLayoutBinding
        get() = checkNotNull(_binding)

    private var _priorityBinding: PriorityButtonBinding? = null
    private val priorityBinding: PriorityButtonBinding
    get() = checkNotNull(_priorityBinding)

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AddTaskLayoutBinding.inflate(inflater, container, false)
        _priorityBinding = PriorityButtonBinding.bind(binding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        initMockValues()

        val navContorller = Navigation.findNavController(requireActivity(), )
    }

    private fun setupListeners() {
        with(binding) {
            back.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    private fun initMockValues() {
        with(binding) {
            selectedProject.text = "No project"
            selectedPriority.setBackgroundColor(
                getColor(requireContext(), com.sinx.core.R.color.light_grey)
            )
            selectedRepeat.text = "No"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _priorityBinding = null
    }
}