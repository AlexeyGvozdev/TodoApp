package com.sinx.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sinx.core.di.findComponentDependencies
import com.sinx.task.adapter.SelectProjectAdapter
import com.sinx.task.databinding.BottomSheetChoiceProjectForTaskBinding
import com.sinx.task.di.DaggerTaskComponent
import com.sinx.task.domain.ProjectModel
import com.sinx.task.presentation.SelectProjectViewModel
import com.sinx.task.presentation.SelectProjectViewModelFactory
import dagger.Lazy
import javax.inject.Inject

class BottomSheetChoiceProjectForTask:
    BottomSheetDialogFragment(R.layout.bottom_sheet_choice_project_for_task) {

    private var _binding: BottomSheetChoiceProjectForTaskBinding? = null
    private val binding: BottomSheetChoiceProjectForTaskBinding
        get() = checkNotNull(_binding)

    @Inject
    lateinit var taskViewModelFactory: Lazy<SelectProjectViewModelFactory>

    private val viewModel: SelectProjectViewModel by viewModels {
        taskViewModelFactory.get()
    }

    private val adapter = SelectProjectAdapter(onProjectClick())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerTaskComponent.builder().deps(findComponentDependencies())
            .build()
            .inject(this)
        if (savedInstanceState == null) {
            viewModel.initialize()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetChoiceProjectForTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }
    override fun getTheme(): Int {
        return com.sinx.core.R.style.BottomSheetDialogTheme
    }

    private fun initUi() {
        binding.rvChoiceProjectList.layoutManager = LinearLayoutManager(context)
        binding.rvChoiceProjectList.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.projectList.collect(adapter::setList)
        }

        val search = binding.editTextInput as SearchView

        lifecycleScope.launchWhenStarted {
            search.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.searchString.emit(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun onProjectClick(): (ProjectModel) -> Unit = {
        actionOnClick(it)
    }

    private fun actionOnClick(item: ProjectModel) {
        val nameProject = item.nameProject
        setFragmentResult(
            Constants.SELECT_PROJECT_REQUEST_KEY,
            bundleOf(Constants.SELECT_PROJECT_BUNDLE_KEY to nameProject)
        )
        dismiss()
    }
}