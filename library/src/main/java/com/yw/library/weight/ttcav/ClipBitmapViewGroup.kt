package com.yw.library.weight.ttcav

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yw.library.R
import com.yw.library.utils.BitmapUtils
import com.yw.library.utils.PxUtils
import com.yw.library.utils.PxUtils.dp2px

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.ttcav
 * @ClassName:      ClipBitmapViewGroup
 * @Description:     切割bitmap动画容器 ps:如果不是非全屏的需要手动指定此View的宽和高，只指定父容器是不行的
 *                   ps:子View必须是一个FrameLayout中放两个ImageView，目前是固定写法。不支持其他写法
 * @Author:         wei.yang
 * @CreateDate:     2021/2/8 14:32
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/8 14:32
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ClipBitmapViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    /**
     * 布局之间的填充
     */
    private var margin = 0
    private var minWidth = 0;
    private var minHeight = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        //获取ziView的数量
        val childCount = childCount
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        //布局必须是固定宽高的，如果不是就抛出异常
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            width = measuredWidth
            height = measuredHeight
        }
        //指定布局宽高范围
        setMeasuredDimension(width, height)
        Log.e("宽高设置:", "$width|$height")
        //以下默认布局都是有固定宽高的
        for (index in 0 until childCount) {
            val childView = getChildAt(index)
            val params =
                ViewGroup.LayoutParams((width - margin * 6) / 5, (height - margin * 7) / 6)
            childView.layoutParams = params
        }

    }


    /**
     * @description 开始布局
     * @date: 2020/11/6 16:20
     * @author: wei.yang
     * @param
     * @return
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e("margin:", margin.toString())
        for (row in 0 until 6) {
            for (col in 0 until 5) {
                val childView = getChildAt(row * 5 + col)
                val childWidth = childView.measuredWidth
                val childHeight = childView.measuredHeight
                childView.layout(
                    col * childWidth + margin * (col + 1),
                    row * childHeight + margin * (row + 1),
                    col * childWidth + childWidth + margin * (col + 1),
                    row * childHeight + childHeight + margin * (row + 1)
                )
            }
        }

    }

    /**
     * @description 设置图片的数据源
     * @date: 2020/11/6 16:39
     * @author: wei.yang
     * @param margin 单位是dp
     * @return
     */
    fun setAdapter(views: ArrayList<View>, margin: Float) {
        removeAllViews()
        this.margin = dp2px(margin)
        views?.apply {
            this.forEach {
                addView(it)
            }
        }
        requestLayout()

    }

    /**
     * @method  设置布局承载的容器
     * @description
     * @date: 2021/2/8 17:12
     * @author: wei.yang
     * @param bitmapTop 上层的Bitmap
     * @param bitmapBottom 下层的Bitmap
     * @param splitCount 需要将bitmap切割成多少份
     * @param margin 两张切图之间的填充间距
     */
    fun setAdapter(
        bitmapTop: Bitmap,
        bitmapBottom: Bitmap,
        splitCount: Int,
        margin: Float,
        layoutId: Int
    ) {
        removeAllViews()
        this.margin = dp2px(margin)
        getDataViews(bitmapTop, bitmapBottom, splitCount, margin, layoutId)?.apply {
            this.forEach {
                addView(it)
            }
        }
        requestLayout()

    }

    private fun getDataViews(
        bitmap1: Bitmap,
        bitmap2: Bitmap,
        splitCount: Int,
        margin: Float,
        layoutId: Int
    ): ArrayList<View> {
        val list1 = BitmapUtils().splitBitmap(bitmap1, 6, 5)
        val list2 = BitmapUtils().splitBitmap(bitmap2, 6, 5)
        val views = ArrayList<View>()
        for (index in 0 until splitCount) {
            val view = LayoutInflater.from(context)
                .inflate(layoutId, null) as ViewGroup
            val image1 = view.getChildAt(0) as ImageView
            image1.setImageBitmap(list1[index])
            val image2 = view.getChildAt(1) as ImageView
            image2.setImageBitmap(list2[index])
            image2.setOnClickListener {
                val animat = AnimatorSet()
                animat.playTogether(
                    ObjectAnimator.ofFloat(image2, "rotationY", 0f, 90f)//绕X轴翻转
                )
                animat.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        val animat = AnimatorSet()
                        image1.visibility = View.VISIBLE
                        animat.playTogether(
                            ObjectAnimator.ofFloat(image1, "scaleX", 0f, 1f),//X轴拉伸
                            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.25f, 1f)//透明度
                        )
                        animat.setDuration(500).start()

                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                })
                animat.setDuration(500).start()
            }
            views.add(view)
        }
        return views
    }

    /**
     * dp转px
     */
    fun dp2px(dpValue: Float): Int {
        val scale = PxUtils.getScreenDensity(context)
        return (dpValue * scale + 0.5).toInt()
    }

}