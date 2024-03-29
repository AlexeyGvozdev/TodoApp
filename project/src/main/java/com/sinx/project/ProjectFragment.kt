package com.sinx.project

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinx.core.databinding.AddButtonBinding
import com.sinx.project.adapter.ProjectListAdapter
import com.sinx.project.data.ProjectListModel
import com.sinx.project.databinding.ProjectLayoutBinding
import com.sinx.project.decoration.DividerItemDecoration
import com.sinx.project.presentation.ProjectViewModel
import com.sinx.project.presentation.ProjectViewModelFactory
import com.sinx.core.R as core_R

internal class ProjectFragment : Fragment(R.layout.project_layout) {

    private lateinit var binding: ProjectLayoutBinding
    private lateinit var addButtonBinding: AddButtonBinding
    private lateinit var projectListAdapter: ProjectListAdapter

    private val viewModel: ProjectViewModel by viewModels {
        ProjectViewModelFactory()
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

        val navController =
            Navigation.findNavController(requireActivity(), core_R.id.buttonAddNew)

        lifecycleScope.launchWhenStarted {
            viewModel.navDeepLinkRequest.collect {
                navController.navigate(it)
            }
        }

        projectListAdapter = ProjectListAdapter()

        binding.rvProjectList.layoutManager = LinearLayoutManager(context)
        binding.rvProjectList.adapter = projectListAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.projectList.collect {
                projectListAdapter.setProjectList(it)
            }
        }

        binding.rvProjectList.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(requireContext(), core_R.drawable.divider)
            )
        )

        addButtonBinding.buttonAddNew.setOnClickListener {
            viewModel.onClickListenerBottomSheet()
        }

        setFragmentResultListener(Constans.ADD_PROJECT_REQUEST_KEY) { requestKey, bundle ->
            val result = bundle.getString(Constans.ADD_PROJECT_BUNDLE_KEY)
            val newProject = ProjectListModel(result.toString(), viewModel.dateCreateProject())
            viewModel.addNewProject(newProject)
        }
    }
}