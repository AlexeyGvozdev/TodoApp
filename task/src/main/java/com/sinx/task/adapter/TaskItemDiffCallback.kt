package com.sinx.task.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sinx.task.model.TaskItem

class TaskItemDiffCallback : DiffUtil.ItemCallback<TaskItem>() {

    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem == newItem
    }
}