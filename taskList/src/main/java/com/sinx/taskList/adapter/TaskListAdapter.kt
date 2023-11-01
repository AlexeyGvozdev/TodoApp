package com.sinx.taskList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.sinx.taskList.TaskItem
import com.sinx.taskList.databinding.ItemTaskManagerBinding
import com.sinx.taskList.itemtouchhelper.ItemMoveCallback

class TaskListAdapter(
    private val clickListener: OnTaskClickListener,
    private val dragListener: StartDragListener,
    private val moveListener: OnMoveListener
) :
    ListAdapter<TaskItem, TaskItemViewHolder>(TaskItemDiffCallback()),
    ItemMoveCallback.ItemTouchHelperContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder(
            ItemTaskManagerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener,
            dragListener
        )
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    interface OnMoveListener {
        fun onRowMoved(fromPosition: Int, toPosition: Int)
    }

    interface OnTaskClickListener {
        fun onCheckBoxItemClickListener(item: TaskItem, isChecked: Boolean)
        fun onTaskTitleClickListener(task: TaskItem)
    }

    interface StartDragListener {
        fun requestDrag(viewHolder: TaskItemViewHolder)
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        moveListener.onRowMoved(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(myViewHolder: TaskItemViewHolder) {
        myViewHolder
            .itemView
            .setBackgroundColor(
                ContextCompat.getColor(myViewHolder.itemView.context, com.sinx.core.R.color.white)
            )
    }

    override fun onRowClear(myViewHolder: TaskItemViewHolder) {
        myViewHolder
            .itemView
            .setBackgroundColor(
                ContextCompat.getColor(myViewHolder.itemView.context, com.sinx.core.R.color.white)
            )
    }
}