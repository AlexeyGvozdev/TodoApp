package com.sinx.task

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sinx.task.databinding.AddTaskLayoutBinding
import com.sinx.task.presentation.PrioritySheetViewModel
import com.sinx.core.R as core_R

class AddTaskFragment : Fragment() {

    private var _binding: AddTaskLayoutBinding? = null
    private val binding: AddTaskLayoutBinding
        get() = checkNotNull(_binding)

    private var _viewModel: PrioritySheetViewModel? = null
    private val viewModel: PrioritySheetViewModel
        get() = checkNotNull(_viewModel)

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
        _viewModel = ViewModelProvider(this).get(PrioritySheetViewModel::class.java)
        setupListeners()
        initMockValues()

        val navController =
            Navigation.findNavController(requireActivity(), R.id.selectedPriority)

        lifecycleScope.launchWhenStarted {
            viewModel.navDeepLinkRequest.collect {
                navController.navigate(it)
            }
        }

        with(binding) {
            selectedPriority.setOnClickListener {
                viewModel.onClickListenerBottomSheet()
            }
            repeat.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("app://task/BottomSheetRepeatFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
            selectedRepeat.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("app://task/BottomSheetRepeatFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
        }

        setFragmentResultListener(Constants.SET_PRIORITY_REQUEST_KEY) { requestKey, bundle ->
            val result = bundle.getString(Constants.SET_PRIORITY_BUNDLE_KEY)!!
            val colorStateList = when(result){
                "green" -> ColorStateList.valueOf(resources.getColor(core_R.color.green))
                "red" -> ColorStateList.valueOf(resources.getColor(core_R.color.red))
                "light-grey" -> ColorStateList.valueOf(resources.getColor(core_R.color.light_grey))

                else -> ColorStateList.valueOf(resources.getColor(core_R.color.light_grey))
            }
            binding.selectedPriority.backgroundTintList = colorStateList
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
        _viewModel = null
    }
}