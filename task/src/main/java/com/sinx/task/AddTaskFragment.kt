package com.sinx.task

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
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

        setFragmentResultListener(Constants.SET_PRIORITY_REQUEST_KEY) { _, bundle ->
            val colorStateList =
                ColorStateList.valueOf(
                    resources.getColor(
                        when (bundle.getString(Constants.SET_PRIORITY_BUNDLE_KEY)) {
                            Constants.GREEN_PRIORITY -> core_R.color.green
                            Constants.RED_PRIORITY -> core_R.color.red
                            Constants.LIGHT_GREY_PRIORITY -> core_R.color.light_grey
                            else -> core_R.color.light_grey
                        }
                    )
                )

            binding.selectedPriority.backgroundTintList = colorStateList
        }

        setFragmentResultListener(Constants.GET_REPEAT_REQUEST_KEY) { _, bundle ->
            val selectRepeat = bundle.getStringArray(Constants.GET_REPEAT_BUNDLE_KEY)?.toList()
            binding.selectedRepeat.text = selectRepeat?.joinToString(" ")
        }

        with(binding) {
            selectedPriority.setOnClickListener {
                viewModel.onClickSelectPriority()
            }
            selectedRepeat.setOnClickListener {
                val repeat = viewModel.stringToWords(selectedRepeat.text.toString())
                setFragmentResult(
                    Constants.SET_REPEAT_REQUEST_KEY,
                    bundleOf(Constants.SET_REPEAT_BUNDLE_KEY to repeat)

                )
                viewModel.onClickSelectRepeat()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}