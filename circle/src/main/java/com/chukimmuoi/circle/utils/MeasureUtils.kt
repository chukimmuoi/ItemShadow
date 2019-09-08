package com.chukimmuoi.circle.utils

import android.content.res.Resources
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * @author  : Pos365
 * @Skype   : chukimmuoi
 * @Mobile  : +84 373 672 505
 * @Email   : chukimmuoi@gmail.com
 * @Website : https://pos365.vn/
 * @Project : ItemShadow
 * Created by chukimmuoi on 2019-09-08.
 */
fun Float.convertPixelToDp(resources: Resources) = this / resources.displayMetrics.density

fun Float.convertDpToPixel(resources: Resources) = this * resources.displayMetrics.density

/**
 * Converts a pixel value to a density independent pixels (DPs).
 *
 * @param resources A reference to the [Resources] in the current context.
 * @param pixels    A measurement in pixels.
 * @return The value of {@code pixels} in DPs.
 */
fun Int.convertPixelToDp(resources: Resources)
        = (this / resources.displayMetrics.density).roundToInt()

/**
 * Converts a density independent pixels (DPs) value to a pixel.
 *
 * @param resources A reference to the [Resources] in the current context.
 * @param dp    A measurement in Dps.
 * @return The value of {@code Dps} in pixels.
 */
fun Int.convertDpToPixel(resources: Resources)
        = (this * resources.displayMetrics.density).roundToInt()

fun Int.getGoldenRatioSmall() = (this / ((1 + sqrt(5.0)) / 2)).roundToInt()

fun Int.getGoldenRatioLarge() = (this * ((1 + sqrt(5.0)) / 2)).roundToInt()

fun Float.getGoldenRatioSmall() = (this / ((1 + sqrt(5.0)) / 2)).toFloat()

fun Float.getGoldenRatioLarge() = (this * ((1 + sqrt(5.0)) / 2)).toFloat()