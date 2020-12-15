package com.yw.library.weight

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.widget.LinearLayout
import android.widget.Scroller
import kotlin.math.abs

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      MyScrollView
 * @Description:     自定义实现一个ScrollView
 * @Author:         wei.yang
 * @CreateDate:     2020/12/15 10:46
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/12/15 10:46
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class MyScrollView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    /**
     * 弹性滑动
     */
    private var scroller: Scroller? = null

    /**
     * 整个scrollView的高度
     */
    private var scrollHeight = 0
    private var lastX: Float = 0f
    private var lastY: Float = 0f

    private var windowHeight: Int = 0

    /**
     * 这里记录横向或者竖向是否进行了滑动
     */
    private var moveX: Float = 0f
    private var moveY: Float = 0f

    /**
     * 速度检测器
     */
    private var velocityTracker: VelocityTracker? = null

    init {
        scroller = Scroller(context)
        orientation = LinearLayout.VERTICAL
    }

    /**
     * 由于这里继承了LinearLayout，所以就没有必要测量高度和摆放布局了，仅仅计算滑动距离就可以了
     */
    @SuppressLint("LongLogTag")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var childCount = childCount
        scrollHeight = 0
        for (i in 0 until childCount) {
            var child = getChildAt(i)
            //测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            //获取子View的margin
            val marginParams = child.layoutParams as MarginLayoutParams
            //添加上所有view的margin，并获取整个View的高度
            scrollHeight += child.measuredHeight + marginParams.topMargin + marginParams.bottomMargin
        }
        //获取ScrollView的高度
        windowHeight = measuredHeight//如果此处继承的是ViewGroup则要手动进行计算ScrollView的高度

        Log.e("windowHeight:scrollHeight", "$windowHeight|$scrollHeight")

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = ev.x
                moveX = ev.x
                lastY = ev.y
                moveY = ev.y
                scroller?.forceFinished(true)
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain()
                } else {
                    velocityTracker?.clear()
                }
                velocityTracker?.addMovement(ev)
                Log.e("dispatchTouchEvent_down", "$lastX|$lastY")
            }
            MotionEvent.ACTION_MOVE -> {
                velocityTracker?.addMovement(ev)
                var curX: Float = ev.x
                var curY: Float = ev.y
                //垂直滑动的时候进行移动
                if (abs(curY - lastY) > abs(curX - lastX)) {
                    scrollBy(0, -(curY - lastY).toInt())
                    //或者使用下面的方法，因为设置了平滑过渡时间为0
//                    scroller?.startScroll(0,scrollY,0,-(curY-lastY).toInt(),0)
                    invalidate()
                }
                lastX = curX
                lastY = curY
                Log.e("dispatchTouchEvent_move", "$lastX|$lastY")
            }
            MotionEvent.ACTION_UP -> {
                Log.e("dispatchTouchEvent_up", "$lastX|$lastY")
                scroller?.abortAnimation()
                if (scrollY < 0) {//证明达到上界，这时要进行回弹处理
                    scroller?.startScroll(0, scrollY, 0, -scrollY)
                    //重新绘制执行computeScroll()
                } else if (windowHeight + scrollY > scrollHeight) {//达到最顶部
                    scroller?.startScroll(0, scrollY, 0, -(scrollY - (scrollHeight - windowHeight)))
                    postInvalidate()
                } else {
                    velocityTracker?.computeCurrentVelocity(1000)
                    val yVelocity = velocityTracker?.yVelocity
                    /**
                     * fling 方法参数注解
                     * startX 滚动起始点X坐标
                     * startY 滚动起始点Y坐标
                     * velocityX   当滑动屏幕时X方向初速度，以每秒像素数计算
                     * velocityY   当滑动屏幕时Y方向初速度，以每秒像素数计算
                     * minX    X方向的最小值，scroller不会滚过此点。
                     *　maxX    X方向的最大值，scroller不会滚过此点。
                     *　minY    Y方向的最小值，scroller不会滚过此点。
                     *　maxY    Y方向的最大值，scroller不会滚过此点。
                     */
                    if (abs(yVelocity!!) > 50) {
//                        scroller?.extendDuration(2000)
                        scroller?.fling(
                            0,
                            scrollY,
                            0,
                            -yVelocity?.toInt()!!,
                            0,
                            0,
                            0,
                            (scrollHeight - windowHeight)
                        )
                        postInvalidate()
                    }
                }
                //进行计算移动距离
                moveX = abs(ev.x - moveX)
                moveY = abs(ev.y - moveY)
                //如果横向或竖向已经移动了一段距离，那么久不能响应子控件的点击事件
                if (moveY > 10) {
                    return true
                }

            }


        }
        Log.e("dispatchTouchEvent", ev?.action.toString())
        return super.onInterceptTouchEvent(ev)
    }

    override fun computeScroll() {
        super.computeScroll()
        //如果scroll还没有停止，就继续动画
        if (scroller?.computeScrollOffset()!!) {
            //注意这里的getCurrY()的源码获取的是进行微移后的当前的坐标，不是相对距离
            scrollTo(0, scroller?.currY!!);
            postInvalidate()
        }
    }
}