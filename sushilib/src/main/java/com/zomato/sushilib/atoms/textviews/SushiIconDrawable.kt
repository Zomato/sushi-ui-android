package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.DimenRes
import android.support.v4.content.res.ResourcesCompat
import android.text.TextPaint
import com.zomato.sushilib.R


// todo remove sushi specific impl from this
class SushiIconDrawable
/**
 * Create an IconDrawable.
 *
 * @param context Your activity or application context.
 * @param icon    The icon you want this drawable to display.
 */
    (private val context: Context) : Drawable(),
    SushiIconEditor {

    private val paint: TextPaint = TextPaint()

    private var size = -1

    private var icon = ""

    init {
        paint.typeface = ResourcesCompat.getFont(context, R.font.wasabi)
        paint.style = Paint.Style.STROKE
        paint.textAlign = Paint.Align.CENTER
        paint.isUnderlineText = false
        paint.color = Color.WHITE
        paint.isAntiAlias = true
    }


    /**
     * Set the size of the drawable.
     *
     * @param dimenRes The dimension resource.
     * @return The current IconDrawable for chaining.
     */
    override fun sizeRes(@DimenRes dimenRes: Int): SushiIconDrawable {
        return sizePx(context.resources.getDimensionPixelSize(dimenRes))
    }

    /**
     * Set the size of the drawable.
     *
     * @param size The size in pixels (px).
     * @return The current IconDrawable for chaining.
     */
    private fun sizePx(size: Int): SushiIconDrawable {
        this.size = size
        setBounds(0, 0, size, size)
        invalidateSelf()
        return this
    }


    /**
     * Set the color of the drawable.
     *
     * @param colorRes The color resource, from your R file.
     * @return The current IconDrawable for chaining.
     */
    override fun colorRes(colorRes: Int): SushiIconDrawable {
        paint.color = context.resources.getColor(colorRes)
        invalidateSelf()
        return this
    }

    override fun colorInt(colorInt: Int): SushiIconEditor {
        paint.color = colorInt
        invalidateSelf()
        return this
    }

    /**
     * Set the alpha of this drawable.
     *
     * @param alpha The alpha, between 0 (transparent) and 255 (opaque).
     * @return The current IconDrawable for chaining.
     */
    override fun alpha(alpha: Int): SushiIconDrawable {
        setAlpha(alpha)
        invalidateSelf()
        return this
    }


    fun editor(): SushiIconDrawable {
        return this
    }

    override fun apply(): SushiIconDrawable = this

    override fun getIntrinsicHeight(): Int {
        return size
    }

    override fun getIntrinsicWidth(): Int {
        return size
    }

    override fun draw(canvas: Canvas) {
        paint.textSize = bounds.height().toFloat()
        val textBounds = Rect()
        val textValue = icon
        paint.getTextBounds(textValue, 0, 1, textBounds)
        val textBottom = (bounds.height() - textBounds.height()) / 2f + textBounds.height() - textBounds.bottom
        canvas.drawText(textValue, bounds.width() / 2f, textBottom, paint)
    }

    override fun isStateful(): Boolean {
        return true
    }

    override fun setState(stateSet: IntArray): Boolean {
        /**
         * not supporting this, can be implemented if use case arises.
         */

        //        int oldValue = paint.getAlpha();
        //        int newValue = isEnabled(stateSet) ? alpha : alpha / 2;
        //        paint.setAlpha(newValue);
        //        return oldValue != newValue;
        return false
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    override fun clearColorFilter() {
        paint.colorFilter = null
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun icon(icon: String): SushiIconEditor = apply {
        this.icon = icon
        invalidateSelf()
    }
}