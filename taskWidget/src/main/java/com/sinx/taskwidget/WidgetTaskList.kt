package com.sinx.taskwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.sinx.taskwidget.WidgetTaskListAdapter.Companion.taskList
import com.sinx.widgetTask.R

class WidgetTaskList : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_task_list)

        setList(views, context)
        setPaddingIntent(views, context)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == Constants.ACTION_CLICKED) {
            val position = intent.getIntExtra(Constants.ACTION_ITEM, -1)
            if (position in taskList.indices) {
                taskList[position] = taskList[position].copy(enabled = !taskList[position].enabled)

                val appWidgetManager = AppWidgetManager.getInstance(context)
                val componentName = ComponentName(context, WidgetTaskList::class.java)
                val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.task_list)
            }
        }
    }

    private fun setPaddingIntent(views: RemoteViews, context: Context) {
        val clickIntent = Intent(context, WidgetTaskList::class.java)
        val clickPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            clickIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        views.setPendingIntentTemplate(R.id.task_list, clickPendingIntent)
    }

    private fun setList(views: RemoteViews, context: Context) {
        val serviceIntent = Intent(context, WidgetTaskListRemoteViewsService::class.java)
        views.setRemoteAdapter(R.id.task_list, serviceIntent)
    }
}