package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sinx.task.databinding.PrioritySheetLayoutBinding
import com.sinx.task.databinding.TaskListLayoutBinding

private const val COLLAPSED_HEIGHT = 228

class PrioritySheetFragment : Fragment() {

    private var _binding: PrioritySheetLayoutBinding? = null
    private val binding: PrioritySheetLayoutBinding
        get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PrioritySheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}