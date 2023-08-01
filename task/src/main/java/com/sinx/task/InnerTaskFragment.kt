package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sinx.task.Constants.TASK_BUNDLE_KEY
import com.sinx.task.Constants.TASK_DATE_BUNDLE_KEY
import com.sinx.task.databinding.InnerTaskLayoutBinding

class InnerTaskFragment : Fragment() {
    private var _binding: InnerTaskLayoutBinding? = null
    private val binding: InnerTaskLayoutBinding
        get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InnerTaskLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(TASK_BUNDLE_KEY)
        val date = arguments?.getString(TASK_DATE_BUNDLE_KEY)
        binding.title.setText(title.toString())
        binding.selectedDate.setText(date.toString())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}