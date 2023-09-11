package com.sinx.taskList.adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sinx.taskList.TaskItem
import com.sinx.taskList.databinding.ItemTaskManagerBinding

class TaskItemViewHolder(
    private val binding: ItemTaskManagerBinding,
    private var clickListener: TaskListAdapter.OnTaskClickListener,
    private var dragListener: TaskListAdapter.StartDragListener
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ClickableViewAccessibility")
    fun bind(task: TaskItem) = with(binding) {
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
                clickListener.onCheckBoxItemClickListener(task, binding.checkBoxTaskPriority.isChecked)
            }
            imageViewChangePosition.setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    dragListener.requestDrag(this@TaskItemViewHolder)
                }
                false
            }
        }
    }
}