package com.sinx.task

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.sinx.task.Constants.GREEN_PRIORITY
import com.sinx.task.Constants.LIGHT_GREY_PRIORITY
import com.sinx.task.Constants.RED_PRIORITY
import com.sinx.task.Constants.SET_PRIORITY_BUNDLE_KEY
import com.sinx.task.Constants.SET_PRIORITY_REQUEST_KEY
import com.sinx.task.databinding.AddTaskLayoutBinding
import com.sinx.task.presentation.AddTaskViewModel
import com.sinx.core.R as core_R

class AddTaskFragment : Fragment() {

    private var _binding: AddTaskLayoutBinding? = null
    private val binding get() = checkNotNull(_binding)
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

        val navController =
            Navigation.findNavController(requireActivity(), R.id.selectedPriority)

        lifecycleScope.launchWhenStarted {
            viewModel.navDeepLinkRequest.collect(navController::navigate)
        }

        with(binding) {
            selectedPriority.setOnClickListener {
                viewModel.onClickSelectPriority()
            }
            repeat.setOnClickListener {
                viewModel.onClickSelectRepeat()
            }
            selectedRepeat.setOnClickListener {
                viewModel.onClickSelectRepeat()
            }
        }

        setFragmentResultListener(SET_PRIORITY_REQUEST_KEY) { _, bundle ->
            val colorStateList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        when (bundle.getString(SET_PRIORITY_BUNDLE_KEY)) {
                            GREEN_PRIORITY -> core_R.color.green
                            RED_PRIORITY -> core_R.color.red
                            LIGHT_GREY_PRIORITY -> core_R.color.light_grey
                            else -> core_R.color.light_grey
                        }
                    )
                )

            binding.selectedPriority.backgroundTintList = colorStateList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}