package com.appersiano.progressimage

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.getDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.progress_loading_view.view.*


class ProgressImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var shadowColor: Int? = null
    private var srcImage: Int? = null

    init {
        inflate(getContext(), R.layout.progress_loading_view, this)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressImage,
            0, 0
        ).apply {
            try {
                shadowColor = getColor(R.styleable.ProgressImage_imageShadowColor, Color.parseColor("#C0C0C0"))
                srcImage = getResourceId(R.styleable.ProgressImage_srcImage, 0)
            } finally {
                recycle()
            }
        }

        val currentImage = getDrawable(context, srcImage!!)
        val treeloading = getDrawable(context, srcImage!!)!!.constantState.newDrawable();

        ivLoadingImageBack.setImageDrawable(ClipDrawable(currentImage, Gravity.BOTTOM, VERTICAL))
        ivLoadingImageBack.drawable.apply {
            level = 10000
            setTint(shadowColor!!)
        }

        ivLoadingImageFront.setImageDrawable(ClipDrawable(treeloading, Gravity.BOTTOM, ClipDrawable.VERTICAL))
    }

    private fun animateImage(percentage: Double?) {
        val animateTree = ValueAnimator.ofInt(0, percentage!!.toInt())

        animateTree.apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int

                ivLoadingImageFront.drawable.level = value * 100
                tvPercentage.text = value.toString() + " %"

                val differ = ivLoadingImageFront.height / 100
                val params = tvPercentage.getLayoutParams() as ConstraintLayout.LayoutParams
                params.bottomMargin = value * differ
                tvPercentage.setLayoutParams(params)
            }
            start()
        }
    }

    fun setProgress(progress: Int, animate: Boolean = false) {
        when (animate) {
            true -> animateImage(progress.toDouble())
            false -> ivLoadingImageFront.drawable.level = progress * 100
        }
    }

}