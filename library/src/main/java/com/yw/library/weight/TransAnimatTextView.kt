package com.yw.library.weight

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.yw.library.R
import com.yw.library.utils.PxUtils
import com.yw.library.weight.cshape.ShapeUtils


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library
 * @ClassName:      TransAnimatTextView
 * @Description:     属性动画效果类
 * @Author:         wei.yang
 * @CreateDate:     2021/6/9 13:12
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/9 13:12
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TransAnimatTextView : AppCompatTextView {
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        ShapeUtils.setBackground(this,
            R.color.color_ff6510,1f,
            R.color.color_ff6510,4f)
    }

    /**
     * 开启动画
     */
    fun startAnimation() {
        var set = AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(
                this@TransAnimatTextView,
                "translationY",
                0f,
                PxUtils.dp2px(context, 60f).toFloat()
            ),//y轴平移
            ObjectAnimator.ofFloat(this@TransAnimatTextView, "alpha", 0f, 1f, 1f)//透明度
        );
        set.setDuration(1000).start();//时间
        this.postDelayed({
            this@TransAnimatTextView.alpha = 0f
        }, 2000)
    }
}