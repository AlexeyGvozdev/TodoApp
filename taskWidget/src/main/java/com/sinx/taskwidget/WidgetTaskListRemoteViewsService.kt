package com.sinx.taskwidget

import android.content.Intent
import android.widget.RemoteViewsService

class WidgetTaskListRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return WidgetTaskListAdapter(applicationContext)
    }
}