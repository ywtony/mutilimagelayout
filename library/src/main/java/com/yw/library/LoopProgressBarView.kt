package com.yw.library

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library
 * @ClassName:      循环滚动进度条
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/7/30 16:12
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/7/30 16:12
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class LoopProgressBarView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var relBg: RelativeLayout
    var tvBFB: TextView
    var flFrame: FrameLayout
    var ivAnimLeft: ImageView
    var ivAnimRight: ImageView

    init {
        var view = LayoutInflater.from(context).inflate(R.layout.loop_progressbar_view, null)
        relBg = view.findViewById(R.id.relBg)
        tvBFB = view.findViewById(R.id.tvBFB)
        flFrame = view.findViewById(R.id.flFrame)
        ivAnimLeft = view.findViewById(R.id.ivAnimLeft)
        ivAnimRight = view.findViewById(R.id.ivAnimRight)
        relBg.background.alpha = 143
        removeAllViews()
        addView(view)
    }

    /**
     * 左右循环动画
     */
    private fun startLoopAnim() {
        var animset: AnimatorSet? = null
        flFrame.post {
            val objectAnimator =
                ObjectAnimator.ofFloat(
                    ivAnimRight,
                    "translationX",
                    0f,
                    flFrame.measuredWidth.toFloat()
                )
            objectAnimator.repeatCount = ValueAnimator.INFINITE
            objectAnimator.interpolator = LinearInterpolator()

            val objectAnimator2 =
                ObjectAnimator.ofFloat(
                    ivAnimLeft,
                    "translationX",
                    -flFrame.measuredWidth.toFloat(),
                    0f
                )
            objectAnimator2.repeatCount = ValueAnimator.INFINITE
            //线性插值器，使其匀速运动，防止循环一周期中间会有停顿
            objectAnimator2.interpolator = LinearInterpolator()

            animset = AnimatorSet()
            animset?.play(objectAnimator)?.with(objectAnimator)?.with(objectAnimator2)
            animset?.duration = 4000
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
    }

    /**
     * 改变View宽度的动画
     */
    private fun startChangeViewWidthAnim(progress: Float) {
        val valueAnimator = ValueAnimator()
        valueAnimator.setIntValues(0, PxUtils.dp2px(context, progress))
        valueAnimator.duration = 4000
        valueAnimator.addUpdateListener { animation ->
            var v = animation.animatedValue as Int
            tvBFB.text =
                "${((v / PxUtils.dp2px(context, 109f)
                    .toFloat()) * 100).toInt()}%"
            flFrame.layoutParams.width = v
            flFrame.requestLayout()

        }
        valueAnimator.start()
    }

    /**
     * 开始加载动画
     * @param progress 需要加载到的进度 这是一个百分比，如30%就传0.3,80%就传0.8,100就传1
     */
    fun loadProgress(progress: Float) {
        startLoopAnim()
        startChangeViewWidthAnim(progress * 109)
    }
}