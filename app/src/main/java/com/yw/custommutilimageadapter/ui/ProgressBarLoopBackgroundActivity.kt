package com.yw.custommutilimageadapter.ui

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.utils.PxUtils
import kotlinx.android.synthetic.main.activity_progressbar_loop_background.*


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      ProgressBarLoopBackgroundActivity
 * @Description:     循环进度条背景
 * @Author:         wei.yang
 * @CreateDate:     2021/7/30 13:15
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/7/30 13:15
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ProgressBarLoopBackgroundActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progressbar_loop_background)
        btnStart.setOnClickListener {
            loopProgressBarView.loadProgress(0.8f)
        }
    }
//
//    private fun start() {
//        var animset: AnimatorSet? = null
//        flFrame.post {
//            val objectAnimator =
//                ObjectAnimator.ofFloat(
//                    ivAnimRight,
//                    "translationX",
//                    0f,
//                    flFrame.measuredWidth.toFloat()
//                )
//            objectAnimator.repeatCount = ValueAnimator.INFINITE
//            objectAnimator.interpolator = LinearInterpolator()
//
//            val objectAnimator2 =
//                ObjectAnimator.ofFloat(
//                    ivAnimLeft,
//                    "translationX",
//                    -flFrame.measuredWidth.toFloat(),
//                    0f
//                )
//            objectAnimator2.repeatCount = ValueAnimator.INFINITE
//            //线性插值器，使其匀速运动，防止循环一周期中间会有停顿
//            objectAnimator2.interpolator = LinearInterpolator()
//
//            animset = AnimatorSet()
//            animset?.play(objectAnimator)?.with(objectAnimator)?.with(objectAnimator2)
//            animset?.duration = 4000
//            animset?.addListener(object : Animator.AnimatorListener {
//                override fun onAnimationRepeat(animation: Animator?) {
//                    Log.e("动画：", "重复")
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//                    Log.e("动画：", "结束")
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//                    Log.e("动画：", "取消")
//                }
//
//                override fun onAnimationStart(animation: Animator?) {
//                    Log.e("动画：", "开始")
//                }
//
//            })
//            animset?.start()
//        }
//    }
//
//    /**
//     * 自定义动画
//     */
//    private fun startCustomAnim() {
//        val valueAnimator = ValueAnimator()
//        valueAnimator.setIntValues(0, PxUtils.dp2px(this, 109f))
//        valueAnimator.duration = 4000
//        valueAnimator.addUpdateListener { animation ->
//            var v = animation.animatedValue as Int
//            tvBFB.text =
//                "${((v / PxUtils.dp2px(this@ProgressBarLoopBackgroundActivity, 109f)
//                    .toFloat()) * 100).toInt()}%"
//            flFrame.layoutParams.width = v
//            flFrame.requestLayout()
//
//        }
//        valueAnimator.start()
//    }
}