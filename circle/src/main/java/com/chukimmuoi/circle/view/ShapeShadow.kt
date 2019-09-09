package com.chukimmuoi.circle.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.annotation.RequiresApi
import com.chukimmuoi.circle.R
import com.chukimmuoi.circle.module.Circle
import com.chukimmuoi.circle.utils.convertDpToPixel
import com.chukimmuoi.circle.utils.getGoldenRatioLarge
import kotlin.math.min

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 373 672 505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://pos365.vn/
 * @Project : ItemShadow
 * Created by chukimmuoi on 2019-09-08.
 */
class ShapeShadow : View {

    private val DEFAULT_SIZE_VALUES = 48.convertDpToPixel(resources)
    private val SHADOW_WIDTH_VALUES = 4.convertDpToPixel(resources)

    private var mRootWidthNormal  = DEFAULT_SIZE_VALUES
    private var mRootHeightNormal = DEFAULT_SIZE_VALUES
    private var mRadiusNormal          = 0F

    private var mRootWidthPress  = 0
    private var mRootHeightPress = 0
    private var mRadiusPress     = 0F

    private var mRootColor = Color.WHITE
    private var mRootShadowColor = Color.BLACK

    private val mShape: Circle by lazy {
        Circle()
    }

    /**
     * Được dùng khi add view lúc runtime (đang chạy).
     *
     * @param context
     * */
    constructor(context: Context)
            : super(context)

    /**
     * Được dùng khi khai báo view trong file layout xml.
     *
     * @param context
     * @param attrs custom tuỳ chọn, cho phép chỉnh sửa từ file layout xml (attrs.xml).
     * */
    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs) {
        var typeArray = context.obtainStyledAttributes(attrs, R.styleable.shapeShadowView)

        this.mRootWidthNormal = typeArray.getDimensionPixelSize(
            R.styleable.shapeShadowView_root_width,
            DEFAULT_SIZE_VALUES)

        this.mRootHeightNormal = typeArray.getDimensionPixelSize(
            R.styleable.shapeShadowView_root_height,
            DEFAULT_SIZE_VALUES)

        this.mRootColor = typeArray.getColor(
            R.styleable.shapeShadowView_root_color,
            Color.WHITE)

        this.mRootShadowColor = typeArray.getColor(
            R.styleable.shapeShadowView_root_shadow_color,
            Color.BLACK)

        typeArray.recycle()

        this.mRootWidthPress  = mRootWidthNormal.getGoldenRatioLarge()
        this.mRootHeightPress = mRootHeightNormal.getGoldenRatioLarge()
    }

    /**
     * Được dùng khi khai báo view trong file layout xml.
     * @param context
     * @param attrs custom tuỳ chọn, cho phép chỉnh sửa từ file layout xml (attrs.xml).
     * @param defStyleAttr huộc tính style của theme mặc định.
     * */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    /**
     * Được dùng khi khai báo view trong file layout xml.
     * @param context
     * @param attrs custom tuỳ chọn, cho phép chỉnh sửa từ file layout xml (attrs.xml).
     * @param defStyleAttr thuộc tính style của theme mặc định.
     * @param defStyleRes truyền style riêng thông qua resource.
     * */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * Được gọi khi view group (view cha) gọi addView(View)
     * Tại đây có thể xác định các view xung quanh.
     * Xác định các view xung quanh bằng id và có thể lưu lại để sử dụng.
     * */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    /**
     * Đo lường. Tính toán kích thước hiển thị cho view.
     * Dựa vào nội dung muốn hiển thị mà bạn sẽ tính ra bạn cần tối thiểu bao nhiêu không gian để bạn hiển thị.
     * EXACTLY:     Chính xác bằng.
     * AT_MOST:     Tối đa.
     * UNSPECIFIED: Sao cũng được.
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // Width & height nhỏ nhất của view.
        val desiredWidth = mRootWidthPress
        val desireHeight = mRootHeightPress

        // Tính toán, thỏa thuận với viewGroup để xác định kích thước cho view.
        val width  = resolveSize(desiredWidth, widthMeasureSpec)
        val height = resolveSize(desireHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    /**
     * Thiết lập toạ độ và kích thước cho các thành phần trong view.
     * Ở đây là: toạ độ tâm, bán kính, màu và alpha.
     * */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val xCenter = (mRootWidthPress / 2).toFloat()
        val yCenter = (mRootHeightPress / 2).toFloat()

        mRadiusPress  = min(xCenter, yCenter)
        mRadiusNormal = min(mRootWidthNormal / 2, mRootHeightNormal / 2).toFloat()

        with(mShape) {
            setCenter(xCenter, yCenter)
            setRadius(mRadiusNormal)
            setColor(mRootColor)
            setShadowColor(mShadowColor)
            setAlpha(255)
        }
    }

    /**
     * Dựa và canvas để vẽ và paint để tô màu.
     * */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mShape.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startAnimation(ZoomInAnimation())
                true
            }
            MotionEvent.ACTION_UP -> {
                startAnimation(ZoomOutAnimation())
                true
            }
            else -> false
        }
    }

    inner class ZoomInAnimation: Animation() {

        init {
            duration = 300L
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            mShape.updateRadius(interpolatedTime, mRadiusNormal, mRadiusNormal * 1.2F)
            invalidate()
        }
    }

    inner class ZoomOutAnimation: Animation() {

        init {
            duration = 300L
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            mShape.updateRadius(interpolatedTime, mRadiusNormal * 1.2F, mRadiusNormal)
            invalidate()
        }
    }
}