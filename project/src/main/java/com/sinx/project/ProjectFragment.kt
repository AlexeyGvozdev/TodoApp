package com.sinx.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinx.core.databinding.AddButtonBinding
import com.sinx.project.adapter.ProjectListAdapter
import com.sinx.project.data.ProjectListModel
import com.sinx.project.databinding.ProjectLayoutBinding
import com.sinx.project.decoration.DividerItemDecoration
import com.sinx.project.presentation.ProjectViewModel
import com.sinx.project.presentation.ProjectViewModelFactory
import com.sinx.core.R as core_R

class ProjectFragment : Fragment(R.layout.project_layout) {

    lateinit var binding: ProjectLayoutBinding
    lateinit var addButtonBinding: AddButtonBinding

    private val viewModel by lazy {
        ViewModelProvider(this, ProjectViewModelFactory())[ProjectViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.initialize()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectLayoutBinding.inflate(layoutInflater, container, false)
        addButtonBinding = AddButtonBinding.bind(binding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProjectList.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launchWhenStarted {
            viewModel.projectList.collect {
                binding.rvProjectList.adapter = ProjectListAdapter(it)
            }
        }

        binding.rvProjectList.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(requireContext(), core_R.drawable.divider)
            )
        )

        addButtonBinding.buttonAddNew.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("app://project.BottomSheetAddProjectFragment".toUri())
                .build()
            findNavController().navigate(request)
        }

        setFragmentResultListener(Constans.REQUEST_KEY) { requestKey, bundle ->
            val result = bundle.getString(Constans.BUNDLE_KEY)
            val newProject = ProjectListModel(result.toString(), "12.03.2023")
            viewModel.addNewProject(newProject)
        }
    }
}