package com.chukimmuoi.circle.module

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 373 672 505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://pos365.vn/
 * @Project : ItemShadow
 * Created by chukimmuoi on 2019-09-10.
 */
class Point {
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.BLACK
        mPaint.strokeWidth = 10F
    }

    fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawPoint(x, y, mPaint)
    }
}