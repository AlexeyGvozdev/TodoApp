package com.sinx.taskList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sinx.taskList.TaskItem

class TaskItemDiffCallback : DiffUtil.ItemCallback<TaskItem>() {

    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem == newItem
    }
}