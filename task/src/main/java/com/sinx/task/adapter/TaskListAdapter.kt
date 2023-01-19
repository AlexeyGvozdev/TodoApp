package com.sinx.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sinx.task.databinding.ItemTaskManagerBinding
import com.sinx.task.model.TaskItem

class TaskListAdapter : ListAdapter<TaskItem, TaskListAdapter.TaskItemViewHolder>(TaskItemDiffCallback()){

    var onMoreItemClickListener:((TaskItem) -> Unit)? = null
    var onCheckBoxItemClickListener: ((TaskItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val binding = ItemTaskManagerBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
        return TaskItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = getItem(position)
        holder.binding.textViewTask.text = task.name
        holder.binding.textViewTaskDate.text = task.date
        holder.binding.imageViewChangePosition.setOnClickListener{
            onMoreItemClickListener?.invoke(task)
        }
        holder.binding.checkBoxTaskPriority.setOnCheckedChangeListener { button, b ->
            onCheckBoxItemClickListener?.invoke(task)
        }
    }

    class TaskItemViewHolder(
        val binding: ItemTaskManagerBinding
    ) : RecyclerView.ViewHolder(binding.root)
}