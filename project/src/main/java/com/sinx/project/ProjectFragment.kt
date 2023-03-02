package com.sinx.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinx.core.databinding.AddButtonBinding
import com.sinx.project.adapter.ProjectListAdapter
import com.sinx.project.data.ProjectListModel
import com.sinx.project.data.ProjectRepositoryImpl
import com.sinx.project.databinding.ProjectLayoutBinding
import com.sinx.project.decoration.DividerItemDecoration
import com.sinx.project.presentation.ProjectViewModel
import com.sinx.project.presentation.ProjectViewModelFactory
import com.sinx.core.R as core_R

class ProjectFragment : Fragment(R.layout.project_layout) {

    lateinit var binding: ProjectLayoutBinding
    lateinit var addButtonBinding: AddButtonBinding

    lateinit var viewModel: ProjectViewModel

    private val projectRepository = ProjectRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectLayoutBinding.inflate(layoutInflater, container, false)
        addButtonBinding = AddButtonBinding.bind(binding.root)

        binding.rvProjectList.layoutManager = LinearLayoutManager(context)
        binding.rvProjectList.adapter = ProjectListAdapter(dataListProject())
        binding.rvProjectList.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(requireContext(), core_R.drawable.divider)
            )
        )

        viewModel = ViewModelProvider(this, ProjectViewModelFactory())[ProjectViewModel::class.java]

        addButtonBinding.buttonAddNew.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("app://project.BottomSheetAddProjectFragment".toUri())
                .build()
            findNavController().navigate(request)
        }
        return binding.root
    }

    @Suppress("MagicNumber")
    private fun dataListProject() = (0..20).map { i ->
        ProjectListModel("Project Main ${i + 1}", "07 Jan 23")
    }
}