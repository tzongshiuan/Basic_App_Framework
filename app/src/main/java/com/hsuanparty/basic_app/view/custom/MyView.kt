package com.hsuanparty.basic_app.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hsuanparty.basic_app.R

/**
 * Author: Tsung Hsuan, Lai
 * Created on: 2019/11/22
 * Description:
 */
class MyView(context: Context, attrs: AttributeSet): View(context, attrs) {

    var circleColor: Int
    var labelColor: Int
    var circleText: String?

    private val circlePaint = Paint()

    init {
        val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0)
        circleColor = typeArray.getColor(R.styleable.MyView_circleColor, Color.WHITE)
        labelColor = typeArray.getColor(R.styleable.MyView_labelColor, Color.WHITE)
        circleText = typeArray.getString(R.styleable.MyView_circleLabel)
    }

    override fun onDraw(canvas: Canvas?) {
        val viewWidthHalf = this.measuredWidth/2.0f
        val viewHeightHalf = this.measuredHeight/2.0f

        var radius = 0.0f
        if (viewWidthHalf > viewHeightHalf) {
            radius = viewHeightHalf - 10
        } else {
            radius = viewWidthHalf - 10
        }

        circlePaint.style = Paint.Style.FILL
        circlePaint.isAntiAlias = true
        circlePaint.color = circleColor

        canvas?.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint)

        circlePaint.color = labelColor
        circlePaint.textAlign = Paint.Align.CENTER
        circlePaint.textSize = 50.0f

        canvas?.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint)
    }
}