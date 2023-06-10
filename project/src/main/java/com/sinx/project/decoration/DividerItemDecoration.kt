package com.sinx.project.decoration

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration : RecyclerView.ItemDecoration() {

    val paint = Paint().apply {
        color = Color.parseColor("#F0F0F0")
        style = Paint.Style.FILL
    }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        parent.children.forEach { view ->
            val childAdapterPosition = parent.getChildAdapterPosition(view)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            if (childAdapterPosition in 0 until itemCount - 1) {
                val dividerLeft = view.left + view.paddingLeft
                val dividerRight: Int = view.right + view.paddingRight
                val dividerTop: Int = view.bottom
                val dividerBottom: Int = view.bottom + 10.dp.toInt()

                c.drawRect(
                    dividerLeft.toFloat(),
                    dividerTop.toFloat(),
                    dividerRight.toFloat(),
                    dividerBottom.toFloat(),
                    paint
                )
            }
        }
    }

    val Int.dp get() = this / Resources.getSystem().displayMetrics.density
}