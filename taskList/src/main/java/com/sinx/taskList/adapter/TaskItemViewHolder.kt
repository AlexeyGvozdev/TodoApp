package com.sinx.taskList.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sinx.taskList.TaskItem
import com.sinx.taskList.databinding.ItemTaskManagerBinding

class TaskItemViewHolder(
    private val binding: ItemTaskManagerBinding,
    private var listener: TaskListAdapter.OnTaskClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: TaskItem) = with(binding) {
        root.setOnClickListener {
            listener.onTaskTitleClickListener(task)
        }
        textViewTask.isEnabled = task.enabled
        textViewTask.text = task.name
        textViewTaskDate.text = task.date
        textViewTaskDate.isEnabled = task.enabled
        checkBoxTaskPriority.isEnabled = task.enabled
        imageViewChangePosition.isVisible = task.enabled
        checkBoxTaskPriority.setOnCheckedChangeListener { _, _ -> }
        checkBoxTaskPriority.isChecked = !task.enabled
        if (task.enabled) {
            checkBoxTaskPriority.setOnCheckedChangeListener { _, _ ->
                listener.onCheckBoxItemClickListener(task, binding.checkBoxTaskPriority.isChecked)
            }
        }
    }
}