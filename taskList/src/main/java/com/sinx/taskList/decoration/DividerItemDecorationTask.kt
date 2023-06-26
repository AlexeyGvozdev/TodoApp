package com.sinx.taskList.decoration

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorationTask(
    private val mDivider: Drawable?
) : RecyclerView.ItemDecoration() {

    companion object {
        const val INDENT_LEFT_DP = 3.9
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
                val dividerLeft = view.left + view.paddingLeft * INDENT_LEFT_DP
                val dividerRight: Int = view.right - view.paddingRight
                val dividerTop: Int = view.bottom
                val dividerBottom: Int =
                    view.bottom + (mDivider?.intrinsicHeight ?: 0)

                mDivider?.setBounds(dividerLeft.toInt(), dividerTop, dividerRight, dividerBottom)
                mDivider?.draw(c)
            }
        }
    }
}