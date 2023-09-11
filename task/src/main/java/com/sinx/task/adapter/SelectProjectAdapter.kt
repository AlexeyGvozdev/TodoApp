package com.sinx.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinx.task.databinding.ItemChoiceProjectBinding
import com.sinx.task.domain.ProjectModel

class SelectProjectAdapter (
    private val onClick: (ProjectModel)-> Unit )
    : RecyclerView.Adapter<SelectProjectViewHolder>() {

    private var projectList = listOf<ProjectModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectProjectViewHolder {
        val binding = ItemChoiceProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectProjectViewHolder(binding) {
            onClick(it)
        }
    }

    override fun onBindViewHolder(holder: SelectProjectViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    override fun getItemCount() = projectList.size

    fun setList(list: List<ProjectModel>) {
        projectList = list
        notifyDataSetChanged()
    }
}