package com.appersiano.progressimage

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getDrawable
import android.support.v4.widget.ImageViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.progress_loading_view.view.*


class ProgressImage(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var shadowColor: Int? = null
    private var srcImage: Int? = null
    private var percentageTextColor: Int? = null

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
                percentageTextColor = getColor(R.styleable.ProgressImage_percentageTextColor, Color.parseColor("#C0C0C0"))
            } finally {
                recycle()
            }
        }

        val currentImage = getDrawable(context, srcImage!!)
        val treeloading = getDrawable(context, srcImage!!)!!.constantState.newDrawable()

        ivLoadingImageBack.setImageDrawable(ClipDrawable(currentImage, Gravity.BOTTOM, VERTICAL))
        ivLoadingImageBack.drawable.apply {
            level = 10000
            setTint(shadowColor!!)
        }

        ivLoadingImageFront.setImageDrawable(ClipDrawable(treeloading, Gravity.BOTTOM, ClipDrawable.VERTICAL))

        tvPercentage.setTextColor(percentageTextColor!!)
    }

    private fun animateImage(from: Int, to: Int, dur: Long) {
        val animateTree = ValueAnimator.ofInt(from, to)

        animateTree.apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = dur
            addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int

                ivLoadingImageFront.drawable.level = value * 100
                tvPercentage.text = "$value %"

                setTvParams(value)
            }
            start()

            ImageViewCompat.setImageTintList(ivLoadingImageFront, null)
        }
    }

    fun setProgress(from: Int, to: Int, duration: Long) {
        animateImage(from, to, duration)
    }

    fun setProgress(to: Int, duration: Long) {
        setProgress(0, to, duration)
    }

    fun setProgress(to: Int) {
        ivLoadingImageFront.drawable.level = to * 100
        tvPercentage.text = "$to %"
        setTvParams(to)
    }

    private fun setTvParams(value: Int) {
        val differ = ivLoadingImageFront.height / 100
        val params = tvPercentage.layoutParams as ConstraintLayout.LayoutParams
        params.bottomMargin = value * differ
        tvPercentage.layoutParams = params
    }
}