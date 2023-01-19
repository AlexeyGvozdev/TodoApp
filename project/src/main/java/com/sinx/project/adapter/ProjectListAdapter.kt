package com.sinx.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinx.project.R
import com.sinx.project.data.ProjectListModel
import com.sinx.project.databinding.ListProjectBinding

class ProjectListAdapter(private val projectListModels: ArrayList<ProjectListModel>) : RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>() {

    class ProjectListViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ListProjectBinding.bind(item)
        fun bind(projectListModel: ProjectListModel) {
            binding.nameProject.text = projectListModel.nameProject
            binding.dataProject.text = projectListModel.dataProject
            binding.movingProject.setImageResource(com.sinx.core.R.drawable.moving)
            binding.dividerProject.setImageResource(com.sinx.core.R.drawable.divider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_project, parent, false)
        return ProjectListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectListModels.size
    }

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        holder.bind(projectListModels[position])
    }
}