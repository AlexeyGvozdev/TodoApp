package com.sinx.taskList.decoration

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorationTask(
    private val mDivider: Drawable?
) : RecyclerView.ItemDecoration() {

    companion object {
        const val INDENT_LEFT_DP = 3.9
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: return

        parent.children.forEach { view ->
            parent.getChildAdapterPosition(view).let { position ->
                if (position != RecyclerView.NO_POSITION && position < itemCount - 1) {
                    drawDivider(canvas, view)
                }
            }
        }
    }

    private fun drawDivider(canvas: Canvas, view: View) {
        val dividerLeft = (view.left + view.paddingLeft * INDENT_LEFT_DP).toInt()
        val dividerRight: Int = view.right - view.paddingRight
        val dividerTop: Int = view.bottom
        val dividerBottom: Int =
            view.bottom + (mDivider?.intrinsicHeight ?: 0)

        mDivider?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
        mDivider?.draw(canvas)
    }
}