package com.sinx.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.sinx.task.databinding.InnerTaskLayoutBinding



class InnerTaskFragment : Fragment() {
    private var _binding: InnerTaskLayoutBinding? = null
    private val binding: InnerTaskLayoutBinding
        get() = checkNotNull(_binding)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InnerTaskLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setFragmentResultListener(Constants.TASK_REQUEST_KEY) { _, bundle ->
            val result = bundle.getString(Constants.TASK_BUNDLE_KEY)
            binding.title.setText(result.toString())
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}