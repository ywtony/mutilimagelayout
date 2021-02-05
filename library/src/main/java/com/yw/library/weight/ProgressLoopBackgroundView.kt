package com.yw.library.weight

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.yw.library.R


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      ProgressBackgroundView
 * @Description:     循环进度View
 * @Author:         wei.yang
 * @CreateDate:     2021/2/5 14:27
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/5 14:27
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ProgressLoopBackgroundView : LinearLayout {
    //<color name="color_FFEA5B">#FFEA5B</color>
    /**
     * 主背景
     */
    private var bgColor = 0

    /**
     * 滑块背景
     */
    private var sliderBg = 0
    private var sliderWidth = 0
    private var slideView: TextView? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.app)
        bgColor = typedArray.getColor(R.styleable.app_sliderBg, 0)
        sliderBg = typedArray.getResourceId(R.styleable.app_sliderChildBg, 0)
        sliderWidth = typedArray.getDimensionPixelSize(R.styleable.app_sliderWidth, 0)
        init()
    }

    private fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setBackgroundColor(bgColor)
        }
        if (slideView == null) {
            slideView = TextView(context)
            val params = LayoutParams(sliderWidth, LayoutParams.MATCH_PARENT)
            slideView?.layoutParams = params
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                slideView?.setBackgroundColor(context.getColor(R.color.black))
//            }
            slideView?.setBackgroundResource(sliderBg)
            addView(slideView)
        }


    }

    private var animset: AnimatorSet? = null

    /**
     * @description 开始执行动画
     * @date: 2021/2/5 14:51
     * @author: wei.yang
     */
    @SuppressLint("ObjectAnimatorBinding")
    fun startAnimation() {
        val objectAnimator =
            ObjectAnimator.ofFloat(slideView, "translationX", 0f, this.measuredWidth.toFloat())
        objectAnimator.repeatCount = ValueAnimator.INFINITE
        animset = AnimatorSet()
        animset?.play(objectAnimator)?.with(objectAnimator)
        animset?.duration = 500
        animset?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                Log.e("动画：", "重复")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.e("动画：", "结束")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.e("动画：", "取消")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.e("动画：", "开始")
            }

        })
        animset?.start()
    }

     /**
      * @description 结束动画
      * @date: 2021/2/5 15:28
      * @author: wei.yang
      */
    fun stopAnimation(){
        animset?.pause()
    }

}