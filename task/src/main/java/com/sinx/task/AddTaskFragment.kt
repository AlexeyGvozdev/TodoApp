package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sinx.core.extensions.navigateTo
import com.sinx.task.databinding.AddTaskLayoutBinding

class AddTaskFragment : Fragment() {

    private var _binding: AddTaskLayoutBinding? = null
    private val binding get() = checkNotNull(_binding)

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
        with(binding) {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            repeat.setOnClickListener {
                findNavController().navigateTo("app://task/BottomSheetRepeatFragment")
            }
            selectedRepeat.setOnClickListener {
                findNavController().navigateTo("app://task/BottomSheetRepeatFragment")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}