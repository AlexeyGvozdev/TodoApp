package com.sinx.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinx.project.data.ProjectListModel
import com.sinx.project.databinding.ListProjectBinding
import com.sinx.core.R as core_R

internal class ProjectListAdapter :
    RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>() {
    private var projectListModels = listOf<ProjectListModel>()

    class ProjectListViewHolder(private val binding: ListProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(projectListModel: ProjectListModel) {
            binding.nameProject.text = projectListModel.nameProject
            binding.dataProject.text = projectListModel.dataProject
            binding.movingProject.setImageResource(core_R.drawable.ic_moving)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        val binding = ListProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectListViewHolder(binding)
    }

    override fun getItemCount() = projectListModels.size

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        holder.bind(projectListModels[position])
    }

    fun setProjectList(list: List<ProjectListModel>) {
        projectListModels = list
        notifyDataSetChanged()
    }
}