package com.sinx.taskwidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.sinx.taskList.TaskItem
import com.sinx.widgetTask.R

class WidgetTaskListAdapter(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    companion object {
        var taskList = mutableListOf<TaskItem>()
    }

    // Инициализация необходимых переменных и загрузка данных.
    override fun onCreate() {
        taskList = getTaskList() as MutableList<TaskItem>
    }

    override fun getLoadingView(): RemoteViews =
        RemoteViews(context.packageName, R.layout.item_task_list)

    override fun getItemId(position: Int): Long = position.toLong()

    /* Метод вызывается, когда данные изменились, и RemoteViewsFactory должен обновить свой список
       элементов пользовательского интерфейса.
       Это может произойти, например, если были добавлены или удалены элементы.
       В этом методе обычно происходит обновление данных. */

    override fun onDataSetChanged() {
        taskList
    }

    override fun onDestroy() {
        // todo
    }

    override fun getViewAt(position: Int): RemoteViews {
        val task = taskList[position]
        val remoteViews = RemoteViews(context.packageName, R.layout.item_task_list)

        remoteViews.setTextViewText(R.id.task_name, task.name)
        remoteViews.setTextViewText(R.id.task_date, task.date)
        remoteViews.setImageViewResource(
            R.id.task_imageview,
            if (task.enabled) {
                R.drawable.check_box_checked
            } else {
                R.drawable.check_box_unchecked
            }
        )

        val intent = Intent(context, WidgetTaskList::class.java)
        intent.action = Constants.ACTION_CLICKED
        intent.putExtra(Constants.ACTION_ITEM, position)

        remoteViews.setOnClickFillInIntent(R.id.task_imageview, intent)
        return remoteViews
    }

    override fun getCount(): Int = taskList.size

    override fun getViewTypeCount(): Int = 1

    override fun hasStableIds(): Boolean = true

    @Suppress("MagicNumber")
    private fun getTaskList() = (0..20).map { i ->
        TaskItem("Task Manager ${i + 1}", "01 Jan 23", false, 1)
    }
}