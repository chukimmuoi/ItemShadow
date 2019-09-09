package com.chukimmuoi.circle.module

import android.graphics.Canvas
import android.graphics.PointF

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 373 672 505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://pos365.vn/
 * @Project : ItemShadow
 * Created by chukimmuoi on 2019-09-08.
 */
class Circle : Shape() {

    private val mCenter = PointF()

    private var mRadius = 0F



    override fun setColor(color: Int) {
        paint.color = color
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    fun setCenter(x: Float, y: Float) {
        mCenter.set(x, y)
    }

    fun setRadius(radius: Float) {
        mRadius = radius
    }

    fun updateRadius(interpolatedTime: Float, from: Float, to: Float) {
        paint.setShadowLayer(
            transformation(interpolatedTime, if (from > to) 16F else 0F, if (from > to) 0F else 16F),
            0F,
            0F,
            mShadowColor)

        mRadius = transformation(interpolatedTime, mRadius, to)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius, paint)
    }
}