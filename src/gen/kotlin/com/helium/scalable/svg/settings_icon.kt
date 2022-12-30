package com.helium.scalable.svg

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import java.util.*
import kotlin.math.min

/**
 * This class has been automatically generated using
 * <a href="https://github.com/kirill-grouchnikov/aurora">Aurora SVG transcoder</a>.
 */
class settings_icon : Painter() {
    @Suppress("UNUSED_VARIABLE")
    private var shape: Outline? = null
    @Suppress("UNUSED_VARIABLE")
    private var generalPath: Path? = null
    @Suppress("UNUSED_VARIABLE")
    private var brush: Brush? = null
    @Suppress("UNUSED_VARIABLE")
    private var stroke: Stroke? = null
    @Suppress("UNUSED_VARIABLE")
    private var clip: Shape? = null
    private var alpha = 1.0f
    private var blendMode = DrawScope.DefaultBlendMode
    private var alphaStack = mutableListOf(1.0f)
    private var blendModeStack = mutableListOf(DrawScope.DefaultBlendMode)

    @Suppress(
        "UNUSED_VARIABLE",
        "UNUSED_VALUE",
        "VARIABLE_WITH_REDUNDANT_INITIALIZER",
        "UNNECESSARY_NOT_NULL_ASSERTION"
    )
    private fun _paint0(drawScope: DrawScope) {
        var shapeText: Outline?
        var generalPathText: Path? = null
        var alphaText = 0.0f
        var blendModeText = DrawScope.DefaultBlendMode
        with(drawScope) {
// 
            alphaStack.add(0, alpha)
            alpha *= 1.0f
            blendModeStack.add(0, BlendMode.SrcOver)
            blendMode = BlendMode.SrcOver
// _0
            alphaStack.add(0, alpha)
            alpha *= 1.0f
            blendModeStack.add(0, BlendMode.SrcOver)
            blendMode = BlendMode.SrcOver
// _0_0
            alpha = alphaStack.removeAt(0)
            blendMode = blendModeStack.removeAt(0)
            alphaStack.add(0, alpha)
            alpha *= 1.0f
            blendModeStack.add(0, BlendMode.SrcOver)
            blendMode = BlendMode.SrcOver
// _0_1
            brush = SolidColor(Color(0, 0, 0, 255))
            stroke = Stroke(width = 2.0f, cap = StrokeCap.Round, join = StrokeJoin.Round, miter = 4.0f)
            if (generalPath == null) {
                generalPath = Path()
            } else {
                generalPath!!.reset()
            }
            generalPath?.run {
                moveTo(10.325f, 4.317f)
                cubicTo(10.750999f, 2.5609999f, 13.249f, 2.5609999f, 13.674999f, 4.317f)
                cubicTo(13.804643f, 4.852059f, 14.182033f, 5.293024f, 14.690651f, 5.5037456f)
                cubicTo(15.199268f, 5.714467f, 15.777941f, 5.669601f, 16.248f, 5.383f)
                cubicTo(17.790998f, 4.443f, 19.557999f, 6.2089996f, 18.618f, 7.753f)
                cubicTo(18.331812f, 8.222854f, 18.287054f, 8.801081f, 18.497528f, 9.30938f)
                cubicTo(18.708002f, 9.8176775f, 19.14843f, 10.195002f, 19.683f, 10.325f)
                cubicTo(21.439001f, 10.750999f, 21.439001f, 13.249f, 19.683f, 13.674999f)
                cubicTo(19.147942f, 13.804643f, 18.706976f, 14.182033f, 18.496254f, 14.690651f)
                cubicTo(18.285534f, 15.199268f, 18.330399f, 15.777941f, 18.617f, 16.248f)
                cubicTo(19.557001f, 17.790998f, 17.791f, 19.557999f, 16.247002f, 18.618f)
                cubicTo(15.777147f, 18.331812f, 15.19892f, 18.287054f, 14.690622f, 18.497528f)
                cubicTo(14.182323f, 18.708002f, 13.804999f, 19.14843f, 13.675001f, 19.683f)
                cubicTo(13.2490015f, 21.439001f, 10.751001f, 21.439001f, 10.325001f, 19.683f)
                cubicTo(10.195357f, 19.147942f, 9.817967f, 18.706976f, 9.309349f, 18.496254f)
                cubicTo(8.800732f, 18.285534f, 8.222059f, 18.330399f, 7.752001f, 18.617f)
                cubicTo(6.2090006f, 19.557001f, 4.442001f, 17.791f, 5.382001f, 16.247002f)
                cubicTo(5.6681886f, 15.777147f, 5.7129464f, 15.19892f, 5.5024734f, 14.690622f)
                cubicTo(5.292f, 14.182323f, 4.851572f, 13.804999f, 4.317001f, 13.675001f)
                cubicTo(2.5610008f, 13.2490015f, 2.5610008f, 10.751001f, 4.317001f, 10.325001f)
                cubicTo(4.85206f, 10.195357f, 5.293025f, 9.817967f, 5.5037465f, 9.309349f)
                cubicTo(5.714468f, 8.800732f, 5.669602f, 8.222059f, 5.383001f, 7.752001f)
                cubicTo(4.443001f, 6.2090006f, 6.2090006f, 4.442001f, 7.7530007f, 5.382001f)
                cubicTo(8.753f, 5.9900007f, 10.049001f, 5.452001f, 10.325001f, 4.317001f)
                close()
            }
            shape = Outline.Generic(generalPath!!)
            drawOutline(outline = shape!!, style = stroke!!, brush = brush!!, alpha = alpha, blendMode = blendMode)
            alpha = alphaStack.removeAt(0)
            blendMode = blendModeStack.removeAt(0)
            alphaStack.add(0, alpha)
            alpha *= 1.0f
            blendModeStack.add(0, BlendMode.SrcOver)
            blendMode = BlendMode.SrcOver
// _0_2
            brush = SolidColor(Color(0, 0, 0, 255))
            stroke = Stroke(width = 2.0f, cap = StrokeCap.Round, join = StrokeJoin.Round, miter = 4.0f)
            shape = Outline.Generic(path = Path().also {
                it.addOval(
                    oval = Rect(
                        left = 9.0f,
                        top = 9.0f,
                        right = 15.0f,
                        bottom = 15.0f
                    )
                )
            })
            drawOutline(outline = shape!!, style = stroke!!, brush = brush!!, alpha = alpha, blendMode = blendMode)
            alpha = alphaStack.removeAt(0)
            blendMode = blendModeStack.removeAt(0)
            alpha = alphaStack.removeAt(0)
            blendMode = blendModeStack.removeAt(0)

        }
    }


    private fun innerPaint(drawScope: DrawScope) {
        _paint0(drawScope)


        shape = null
        generalPath = null
        brush = null
        stroke = null
        clip = null
        alpha = 1.0f
    }

    companion object {
        /**
         * Returns the X of the bounding box of the original SVG image.
         *
         * @return The X of the bounding box of the original SVG image.
         */
        fun getOrigX(): Double {
            return 1.764506220817566
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 1.7645052671432495
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 20.470989227294922
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 20.470991134643555
        }


    }

    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        clipRect {
            // Use the original icon bounding box and the current icon dimension to compute
            // the scaling factor
            val fullOrigWidth = getOrigX() + getOrigWidth()
            val fullOrigHeight = getOrigY() + getOrigHeight()
            val coef1 = size.width / fullOrigWidth
            val coef2 = size.height / fullOrigHeight
            val coef = min(coef1, coef2).toFloat()

            // Use the original icon bounding box and the current icon dimension to compute
            // the offset pivot for the scaling
            var translateX = -getOrigX()
            var translateY = -getOrigY()
            if (coef1 != coef2) {
                if (coef1 < coef2) {
                    val extraDy = ((fullOrigWidth - fullOrigHeight) / 2.0f).toFloat()
                    translateY += extraDy
                } else {
                    val extraDx = ((fullOrigHeight - fullOrigWidth) / 2.0f).toFloat()
                    translateX += extraDx
                }
            }
            val translateXDp = translateX.toFloat().toDp().value
            val translateYDp = translateY.toFloat().toDp().value

            // Create a combined scale + translate + clip transform before calling the transcoded painting instructions
            withTransform({
                scale(scaleX = coef, scaleY = coef, pivot = Offset.Zero)
                translate(translateXDp, translateYDp)
                clipRect(
                    left = 0.0f,
                    top = 0.0f,
                    right = fullOrigWidth.toFloat(),
                    bottom = fullOrigHeight.toFloat(),
                    clipOp = ClipOp.Intersect
                )
            }) {
                innerPaint(this)
            }
        }
    }
}

