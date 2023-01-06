
//This contains lots of ported functions from android
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
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Denotes that the annotated element represents a packed color
 * int, `AARRGGBB`. If applied to an int array, every element
 * in the array represents a color integer.
 *
 *
 * Example:
 * ```
 * public abstract void setTextColor(@ColorInt int color)
 * ```
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.FIELD
)
annotation class ColorInt



//private const val XYZ_WHITE_REFERENCE_X = 95.047
//private const val XYZ_WHITE_REFERENCE_Y = 100.0
//private const val XYZ_WHITE_REFERENCE_Z = 108.883
//private const val XYZ_EPSILON = 0.008856
//private const val XYZ_KAPPA = 903.3
//
//private const val MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10
//private const val MIN_ALPHA_SEARCH_PRECISION = 1
private val TEMP_ARRAY = ThreadLocal<DoubleArray>()
//@IntRange(from = 0, to = 255)
fun alpha(color: Int) : Int {
    return color ushr 24
}

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

fun argb(
    /*@IntRange(from = 0, to = 255)*/ alpha: Int,
    /*@IntRange(from = 0, to = 255)*/  red: Int,
    /*@IntRange(from = 0, to = 255)*/  green: Int,
    /*@IntRange(from = 0, to = 255)*/  blue: Int
): Int {
    return alpha shl 24 or (red shl 16) or (green shl 8) or blue
}

private fun compositeAlpha(foregroundAlpha: Int, backgroundAlpha: Int): Int {
    return 0xFF - (0xFF - backgroundAlpha) * (0xFF - foregroundAlpha) / 0xFF
}
private fun compositeComponent(fgC: Int, fgA: Int, bgC: Int, bgA: Int, a: Int): Int {
    return if (a == 0) 0 else 0xFF * fgC * fgA + bgC * bgA * (0xFF - fgA) / (a * 0xFF)
}

fun compositeColors(@ColorInt foreground: Int, @ColorInt background: Int): Int {
    val bgAlpha: Int = alpha(background)
    val fgAlpha: Int = alpha(foreground)
    val a = compositeAlpha(fgAlpha, bgAlpha)
    val r = compositeComponent(
        red(foreground), fgAlpha,
        red(background), bgAlpha, a
    )
    val g = compositeComponent(
        green(foreground), fgAlpha,
        green(background), bgAlpha, a
    )
    val b = compositeComponent(
        blue(foreground), fgAlpha,
        blue(background), bgAlpha, a
    )
    return argb(a, r, g, b)
}

fun RGBToHSL(
    //from 0-255
     r: Int,
     g: Int,
     b: Int,
     outHsl: FloatArray
) {
    val rf = r / 255f
    val gf = g / 255f
    val bf = b / 255f
    val max = Math.max(rf, Math.max(gf, bf))
    val min = Math.min(rf, Math.min(gf, bf))
    val deltaMaxMin = max - min
    var h: Float
    val s: Float
    val l = (max + min) / 2f
    if (max == min) {
        // Monochromatic
        s = 0f
        h = s
    } else {
        h = if (max == rf) {
            (gf - bf) / deltaMaxMin % 6f
        } else if (max == gf) {
            (bf - rf) / deltaMaxMin + 2f
        } else {
            (rf - gf) / deltaMaxMin + 4f
        }
        s = deltaMaxMin / (1f - abs(2f * l - 1f))
    }
    h = h * 60f % 360f
    if (h < 0) {
        h += 360f
    }
    outHsl[0] = h.coerceIn(0f, 360f)
    outHsl[1] = s.coerceIn(0f, 1f)
    outHsl[2] = l.coerceIn(0f, 1f)
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB color.
 *
 *  * hsl[0] is Hue [0, 360)
 *  * hsl[1] is Saturation [0, 1]
 *  * hsl[2] is Lightness [0, 1]
 *
 * If hsv values are out of range, they are pinned.
 *
 * @param hsl 3-element array which holds the input HSL components
 * @return the resulting RGB color
 */
@ColorInt
fun HSLToColor(hsl: FloatArray): Int {
    val h = hsl[0]
    val s = hsl[1]
    val l = hsl[2]
    val c = (1f - abs(2 * l - 1f)) * s
    val m = l - 0.5f * c
    val x = c * (1f - abs(h / 60f % 2f - 1f))
    val hueSegment = h.toInt() / 60
    var r = 0
    var g = 0
    var b = 0
    when (hueSegment) {
        0 -> {
            r = (255 * (c + m)).roundToInt()
            g = (255 * (x + m)).roundToInt()
            b = (255 * m).roundToInt()
        }

        1 -> {
            r = (255 * (x + m)).roundToInt()
            g = (255 * (c + m)).roundToInt()
            b = (255 * m).roundToInt()
        }

        2 -> {
            r = (255 * m).roundToInt()
            g = (255 * (c + m)).roundToInt()
            b = (255 * (x + m)).roundToInt()
        }

        3 -> {
            r = (255 * m).roundToInt()
            g = (255 * (x + m)).roundToInt()
            b = (255 * (c + m)).roundToInt()
        }

        4 -> {
            r = (255 * (x + m)).roundToInt()
            g = (255 * m).roundToInt()
            b = (255 * (c + m)).roundToInt()
        }

        5, 6 -> {
            r = (255 * (c + m)).roundToInt()
            g = (255 * m).roundToInt()
            b = (255 * (x + m)).roundToInt()
        }
    }
    r = r.coerceIn(0, 255)
    g = g.coerceIn(0, 255)
    b = b.coerceIn(0, 255)
    return rgb(r, g, b)
}

/**
 * Return a color-int from red, green, blue components.
 * The alpha component is implicitly 255 (fully opaque).
 * These component values should be \([0..255]\), but there is no
 * range check performed, so if they are out of range, the
 * returned color is undefined.
 *
 * @param red  Red component \([0..255]\) of the color
 * @param green Green component \([0..255]\) of the color
 * @param blue  Blue component \([0..255]\) of the color
 */
@ColorInt
fun rgb(
    /*@IntRange(from = 0, to = 255)*/ red: Int,
    /*@IntRange(from = 0, to = 255)*/ green: Int,
    /*@IntRange(from = 0, to = 255)*/ blue: Int
): Int {
    return -0x1000000 or (red shl 16) or (green shl 8) or blue
}
/**
 * Returns the luminance of a color as a float between {@code 0.0} and {@code 1.0}.
 * <p>Defined as the Y component in the XYZ representation of {@code color}.</p>
 */

//FloatRange(from = 0.0, to = 1.0)
fun calculateLuminance(color: Int): Double {
    val result = getTempDouble3Array()
    colorToXYZ(color, result)
    // Luminance is the Y component
    return result[1] / 100
}

fun colorToXYZ(@ColorInt color: Int, outXyz: DoubleArray) {
    RGBToXYZ(red(color), green(color), blue(color), outXyz)
}

fun RGBToXYZ(
    /*@IntRange(from = 0x0, to = 0xFF)*/ r: Int,
    /*@IntRange(from = 0x0, to = 0xFF)*/ g: Int,  /*@IntRange(from = 0x0, to = 0xFF)*/ b: Int,
    outXyz: DoubleArray
) {
    require(outXyz.size == 3) { "outXyz must have a length of 3." }
    var sr = r / 255.0
    sr = if (sr < 0.04045) sr / 12.92 else ((sr + 0.055) / 1.055).pow(2.4)
    var sg = g / 255.0
    sg = if (sg < 0.04045) sg / 12.92 else ((sg + 0.055) / 1.055).pow(2.4)
    var sb = b / 255.0
    sb = if (sb < 0.04045) sb / 12.92 else ((sb + 0.055) / 1.055).pow(2.4)
    outXyz[0] = 100 * (sr * 0.4124 + sg * 0.3576 + sb * 0.1805)
    outXyz[1] = 100 * (sr * 0.2126 + sg * 0.7152 + sb * 0.0722)
    outXyz[2] = 100 * (sr * 0.0193 + sg * 0.1192 + sb * 0.9505)
}


private fun getTempDouble3Array(): DoubleArray {
    var result = TEMP_ARRAY.get()
    if (result == null) {
        result = DoubleArray(3)
        TEMP_ARRAY.set(result)
    }
    return result
}
