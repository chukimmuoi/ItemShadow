package com.chukimmuoi.circle.module

import android.graphics.Canvas
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

    init {
        paint.isAntiAlias = true
    }

    abstract fun draw(canvas: Canvas)

    abstract fun setColor(color: Int)

    abstract fun setShadowColor(color: Int)

    abstract fun setAlpha(alpha: Int)
}