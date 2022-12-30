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
class folder_minus : Painter() {
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
                moveTo(5.0f, 4.0f)
                lineTo(9.0f, 4.0f)
                lineTo(12.0f, 7.0f)
                lineTo(19.0f, 7.0f)
                cubicTo(20.10457f, 7.0f, 21.0f, 7.8954306f, 21.0f, 9.0f)
                lineTo(21.0f, 17.0f)
                cubicTo(21.0f, 18.10457f, 20.10457f, 19.0f, 19.0f, 19.0f)
                lineTo(5.0f, 19.0f)
                cubicTo(3.8954306f, 19.0f, 3.0f, 18.10457f, 3.0f, 17.0f)
                lineTo(3.0f, 6.0f)
                cubicTo(3.0f, 4.8954306f, 3.8954306f, 4.0f, 5.0f, 4.0f)
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
                it.moveTo(x = 9.0f, y = 13.0f)
                it.lineTo(x = 15.0f, y = 13.0f)
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
            return 2.0
        }

        /**
         * Returns the Y of the bounding box of the original SVG image.
         *
         * @return The Y of the bounding box of the original SVG image.
         */
        fun getOrigY(): Double {
            return 3.0
        }

        /**
         * Returns the width of the bounding box of the original SVG image.
         *
         * @return The width of the bounding box of the original SVG image.
         */
        fun getOrigWidth(): Double {
            return 20.0
        }

        /**
         * Returns the height of the bounding box of the original SVG image.
         *
         * @return The height of the bounding box of the original SVG image.
         */
        fun getOrigHeight(): Double {
            return 17.0
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

