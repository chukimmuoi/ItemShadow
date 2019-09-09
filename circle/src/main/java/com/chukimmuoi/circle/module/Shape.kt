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
 * Created by chukimmuoi on 2019-09-08.
 */
abstract class Shape {

    val paint = Paint()

    var mShadowColor: Int = Color.BLACK

    init {
        paint.isAntiAlias = true
        paint.setShadowLayer(0F, 0F, 0F, mShadowColor)
    }

    abstract fun draw(canvas: Canvas)

    abstract fun setColor(color: Int)

    fun setShadowColor(color: Int) {
        mShadowColor = color
    }

    abstract fun setAlpha(alpha: Int)

    fun transformation(interpolatedTime: Float, from: Float, to: Float): Float {
        return from + interpolatedTime * (to - from)
    }
}