package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.sinx.task.databinding.AddTaskLayoutBinding

class AddTaskFragment : Fragment() {
    private var _binding: AddTaskLayoutBinding? = null
    private val binding: AddTaskLayoutBinding
        get() = checkNotNull(_binding)

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
        with(binding) {
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