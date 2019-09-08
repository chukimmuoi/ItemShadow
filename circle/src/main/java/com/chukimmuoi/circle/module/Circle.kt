package com.chukimmuoi.circle.module

import android.graphics.Canvas
import android.graphics.Color
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

    private var mShadowColor: Int = Color.BLACK

    override fun setColor(color: Int) {
        paint.color = color
    }

    override fun setShadowColor(color: Int) {
        mShadowColor = color
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

    override fun draw(canvas: Canvas) {
        paint.setShadowLayer(4F, 0F, 0F, mShadowColor)
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius, paint)
    }
}