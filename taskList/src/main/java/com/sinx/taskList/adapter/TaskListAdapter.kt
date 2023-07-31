package com.sinx.taskList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sinx.taskList.TaskItem
import com.sinx.taskList.databinding.ItemTaskManagerBinding

class TaskListAdapter(private var listener: OnTaskClickListener) :
    ListAdapter<TaskItem, TaskItemViewHolder>(TaskItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder(
            ItemTaskManagerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    interface OnTaskClickListener {
        fun onCheckBoxItemClickListener(item: TaskItem, isChecked: Boolean)
        fun onTaskTitleClickListener(task: TaskItem){

        }
    }
}