
/*
 * Copyright 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package utils

import androidx.compose.ui.graphics.Color


@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.FIELD
)
/**
 * AARRGGBB
 */
annotation class ColorInt


//factor should be a fraction 0.0 -> 1.0f
fun Color.shade(factor: Float) : Color {
    return Color(red * factor, blue * factor, green * factor)
}

/* Returns the value of the alpha component in the range `[0..1]`.
*
* @see red
* @see green
* @see blue
*/


/**
 * Return the red component of a color int. This is the same as saying
 * (color >> 16) & 0xFF
 */
//@IntRange(from = 0, to = 255)
fun red(color: Int): Int {
    return color shr 16 and 0xFF
}

/**
 * Return the green component of a color int. This is the same as saying
 * (color >> 8) & 0xFF
 */
///@IntRange(from = 0, to = 255)
fun green(color: Int): Int {
    return color shr 8 and 0xFF
}

/**
 * Return the blue component of a color int. This is the same as saying
 * color & 0xFF
 */
//@IntRange(from = 0, to = 255)
fun blue(color: Int): Int {
    return color and 0xFF
}
/**
 * Blend between two ARGB colors using the given ratio.
 *
 *
 * A blend ratio of 0.0 will result in `color1`, 0.5 will give an even blend,
 * 1.0 will result in `color2`.
 *
 * @param color1 the first ARGB color
 * @param color2 the second ARGB color
 * @param ratio the blend ratio of `color1` to `color2`
 */

@ColorInt
fun blend(
    color1: Color, color2: Color,
    /**FloatRange(from = 0.0, to = 1.0)**/ ratio: Float
): Color {
    val inverseRatio = 1 - ratio
    val (roy,gee,biv,alpha) = color1
    val (roy2,gee2,biv2,alpha2) = color2
    val a: Float = alpha * inverseRatio + alpha2 * ratio
    val r: Float = roy * inverseRatio + roy2 * ratio
    val g: Float = gee * inverseRatio + gee2 * ratio
    val b: Float = biv * inverseRatio + biv2 * ratio
    return Color(r,g,b,a)
}
