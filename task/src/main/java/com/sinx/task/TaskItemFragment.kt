package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.sinx.task.Constants.TASK_BUNDLE_KEY
import com.sinx.task.Constants.TASK_REQUEST_KEY
import com.sinx.taskList.databinding.ItemTaskManagerBinding


class TaskItemFragment : Fragment() {
    private var _binding: ItemTaskManagerBinding? = null
    private val binding: ItemTaskManagerBinding
        get() = checkNotNull(_binding)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemTaskManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            textViewTask.setOnClickListener{
                val request = NavDeepLinkRequest.Builder
                    .fromUri(INNER_TASK_URI.toUri())
                    .build()
                findNavController().navigate(request)

                val nameTask = binding.textViewTask.text.toString()
                setFragmentResult(
                    TASK_REQUEST_KEY,
                    bundleOf(TASK_BUNDLE_KEY to nameTask))

            }
            textViewTaskDate.setOnClickListener{
                val request = NavDeepLinkRequest.Builder
                    .fromUri(INNER_TASK_URI.toUri())
                    .build()
                findNavController().navigate(request)
            }
        }
        }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val INNER_TASK_URI = "app://task.innerTaskFragment"
    }
}



