package com.yw.library.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.widget
 * @ClassName:      CustomMutiImageView
 * @Description:    自定义可适配多图片多样式的ViewGroup
 * @Author:         wei.yang
 * @CreateDate:     2020/11/6 15:39
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/6 15:39
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CustomMutiImageView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    /**
     * 布局之间的填充
     */
    private var margin = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        //获取ziView的数量
        val childCount = childCount
        if (childCount > 4) {
            //如果元素超过四个就抛出异常
            throw IndexOutOfBoundsException("元素数量不能超过四个")
        }
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        //布局必须是固定宽高的，如果不是就抛出异常
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            throw RuntimeException("主布局必须指定宽高")
        }
        //指定布局宽高范围
        setMeasuredDimension(width, height)
        //以下默认布局都是有固定宽高的
        for (index in 0 until childCount) {
            when (childCount) {
                //当只有一个子元素的时候，布局的宽高就是子元素的宽高
                1 -> {
                    val childView = getChildAt(index)
                    val params = ViewGroup.LayoutParams(width, height)
                    childView.layoutParams = params
                }
                2 -> {
                    for (index in 0 until 2) {
                        val childView = getChildAt(index)
                        val params = ViewGroup.LayoutParams((width - margin) / 2, height)
                        childView.layoutParams = params
                    }
                }
                3 -> {
                    for (index in 0 until 3) {
                        val childView = getChildAt(index)
                        var childWidth = 0
                        var childHeight = 0
                        if (index == 0) {
                            childWidth = (width - margin) / 2
                            childHeight = height
                        } else {
                            childWidth = (width - margin) / 2
                            childHeight = (height - margin) / 2
                        }
                        val params = ViewGroup.LayoutParams(childWidth, childHeight)
                        childView.layoutParams = params
                    }
                }
                4 -> {
                    for (index in 0 until 4) {
                        val childView = getChildAt(index)
                        val params =
                            ViewGroup.LayoutParams((width - margin) / 2, (width - margin) / 2)
                        childView.layoutParams = params
                    }
                }
            }
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
        val childCount = childCount
        when (childCount) {
            1 -> {
                val childView = getChildAt(0)
                childView.layout(0, 0, childView.measuredWidth, childView.measuredHeight)
            }
            2 -> {
                val childView1 = getChildAt(0)
                val childWidth = childView1.measuredWidth
                childView1.layout(0, 0, childWidth, childView1.measuredHeight)
                val childView2 = getChildAt(1)
                childView2.layout(
                    childWidth + margin,
                    0,
                    childWidth * 2 + margin,
                    childView2.measuredHeight
                )
            }
            3 -> {
                val childView1 = getChildAt(0)
                val childWidth = childView1.measuredWidth
                childView1.layout(0, 0, childWidth, childView1.measuredHeight)
                val childView2 = getChildAt(1)
                childView2.layout(
                    childWidth + margin,
                    0,
                    childWidth * 2 + margin,
                    childView2.measuredHeight
                )
                val childView3 = getChildAt(2)
                childView3.layout(
                    childWidth + margin,
                    childWidth + margin,
                    childWidth * 2 + margin,
                    childView3.measuredHeight * 2 + margin
                )
            }
            4 -> {
                Log.e("CustomerMusdfj", "布局了四个")
                val childView = getChildAt(0)
                val childWidth = childView.measuredWidth
                val childHeight = childView.measuredHeight
                childView.layout(0, 0, childWidth, childHeight)
                getChildAt(1).layout(childWidth + margin, 0, childWidth * 2 + margin, childHeight)
                getChildAt(2).layout(0, childHeight + margin, childWidth, childHeight * 2 + margin)
                getChildAt(3).layout(
                    childWidth + margin,
                    childHeight + margin,
                    childWidth * 2 + margin,
                    childHeight * 2 + margin
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
     * dp转px
     */
    fun dp2px(dpValue: Float): Int {
        val scale = PxUtils.getScreenDensity(context)
        return (dpValue * scale + 0.5).toInt()
    }
}