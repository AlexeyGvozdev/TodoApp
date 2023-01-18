package com.sinx.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinx.project.adapter.ProjectListAdapter
import com.sinx.project.data.ProjectListModel

class ProjectFragment : Fragment(R.layout.project_layout) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.project_layout, container, false)

        view.findViewById<TextView>(R.id.titleProject)

        val recyclerView = view.findViewById(R.id.rvProjectList) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProjectListAdapter(dataListProject())

        return view
    }

    private fun dataListProject(): ArrayList<ProjectListModel> {
        val projectListModels = ArrayList<ProjectListModel>()
        for (i in 0..100 )
            projectListModels.add(ProjectListModel("Project Main", "01.02.2023"))
        return projectListModels
    }
}