package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.sinx.task.databinding.AddTaskLayoutBinding
import com.sinx.task.presentation.AddTaskViewModel
import kotlinx.coroutines.launch

class AddTaskFragment : Fragment() {
    private var _binding: AddTaskLayoutBinding? = null
    private val binding: AddTaskLayoutBinding
        get() = checkNotNull(_binding)

    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AddTaskLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        initMockValues()

        navigateToBottomSheetAddProjectFragment()
    }

    private fun navigateToBottomSheetAddProjectFragment() {
        binding.selectedProject.setOnClickListener {
            viewModel.onClickListenerBottomSheet()
//            val request = NavDeepLinkRequest.Builder
//                .fromUri("app://task/choiceProjectForTaskBottomSheetFragment".toUri())
//                .build()
//            findNavController().navigate(request)
        }
        lifecycleScope.launch {
            viewModel.navDeepLinkRequest.collect {
                findNavController().navigate(it)
            }
        }


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
    }
}