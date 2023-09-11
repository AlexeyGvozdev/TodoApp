package com.sinx.task.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sinx.task.databinding.ItemChoiceProjectBinding
import com.sinx.task.domain.ProjectModel

class SelectProjectViewHolder(
    private val binding: ItemChoiceProjectBinding,
    private val onClick: (ProjectModel)-> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProjectModel) {
        binding.root.setOnClickListener {
            onClick(item)
        }
        binding.tvChoiceProject.text = item.nameProject
    }
}