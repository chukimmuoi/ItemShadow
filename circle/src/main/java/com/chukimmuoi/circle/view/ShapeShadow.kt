package com.chukimmuoi.circle.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.chukimmuoi.circle.R
import com.chukimmuoi.circle.module.Circle
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

    companion object {
        private val DEFAULT_SIZE_VALUES = 48
        private val SHADOW_WIDTH_VALUES = 16
    }

    private var mRootWidth = DEFAULT_SIZE_VALUES
    private var mRootHeight = DEFAULT_SIZE_VALUES

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

        this.mRootWidth = typeArray.getDimensionPixelSize(
            R.styleable.shapeShadowView_root_width,
            DEFAULT_SIZE_VALUES)

        this.mRootHeight = typeArray.getDimensionPixelSize(
            R.styleable.shapeShadowView_root_height,
            DEFAULT_SIZE_VALUES)

        this.mRootColor = typeArray.getColor(
            R.styleable.shapeShadowView_root_color,
            Color.WHITE)

        this.mRootShadowColor = typeArray.getColor(
            R.styleable.shapeShadowView_root_shadow_color,
            Color.BLACK)

        typeArray.recycle()
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
        val desiredWidth = mRootWidth
        val desireHeight = mRootWidth

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

        val xCenter = (mRootWidth / 2).toFloat()
        val yCenter = (mRootWidth / 2).toFloat()
        val radius = min(xCenter, yCenter) - SHADOW_WIDTH_VALUES

        with(mShape) {
            setCenter(xCenter, yCenter)
            setRadius(radius)
            setColor(mRootColor)
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
}