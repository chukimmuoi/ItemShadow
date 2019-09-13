package com.chukimmuoi.circle.module

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 373 672 505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://pos365.vn/
 * @Project : ItemShadow
 * Created by chukimmuoi on 2019-09-10.
 */
class Image(private val resources: Context) {
    private var mBitmap: Bitmap? = null
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var mRectF: RectF

    private var xCenter = 0F
    private var yCenter = 0F

    private var left = 0F
    private var top  = 0F

    private var right  = 0F
    private var bottom = 0F

    init {
        mPaint.isAntiAlias = true
    }

    fun setCoordinates(image: Int, xCenter: Float, yCenter: Float, size: Float) {
        mBitmap = AppCompatResources.getDrawable(resources, image)?.toBitmap(size.toInt(), size.toInt())
        setCoordinates(xCenter, yCenter, size, size)
    }

    fun setCoordinates(xCenter: Float, yCenter: Float, width: Float, height: Float) {

        this.xCenter = xCenter
        this.yCenter = yCenter

        this.left   = xCenter - (width / 2)
        this.top    = yCenter - (height / 2)

        this.right  = left + width
        this.bottom = top + height

        mRectF = RectF(left, top, right, bottom)
    }

    fun updateSize(image: Int, interpolatedTime: Float, from: Float, to: Float) {
        val size = from + interpolatedTime * (to - from)
        setCoordinates(image, xCenter, yCenter, size)
    }

    fun draw(canvas: Canvas) {
        mBitmap?.let {
            canvas.drawBitmap(it, null, mRectF, mPaint)
        }
    }
}