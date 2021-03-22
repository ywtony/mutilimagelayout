package com.yw.library.weight

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      TiktokVideoLoaddingView
 * @Description:     视频加载动画
 * @Author:         wei.yang
 * @CreateDate:     2021/3/20 11:38
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/20 11:38
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TiktokVideoLoaddingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * 1.加载条高度为1.5pt,纯白，单个循环周期为0.6s
     * 2.线条从中间出现，用0.5秒向两边扩展至边界
     * 3.第一个0.3s 线条透明度从30%->80%
     * 4.第二个0.3s 线条透明度从80%->0%
     *
     *
     *
     *
     *
     */
    /**
     * view的默认宽高
     */
    private var defaultWidth = 0
    private var defaultHeight = 0

    /**
     * view的实际宽高
     */
    private var mWidth = 0;
    private var mHeight = 0

    /**
     * 进度条的宽度
     */
    private var mProgressWidth = PxUtils.dp2px(context, 15f)

    /**
     * 画笔
     */
    private var mPaint = Paint()

    /**
     * 重绘周期
     */
    private var mTimePeriod = 20
    private var mHandler: Handler? = null

    init {
        //设置虎逼模式为填充带边框
        //设置虎逼模式为填充带边框
        mPaint.style = Paint.Style.FILL_AND_STROKE
        //设置抗锯齿
        //设置抗锯齿
        mPaint.isAntiAlias = true

        mHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                invalidate()
                sendEmptyMessageDelayed(1, mTimePeriod.toLong())
            }
        }
        mHandler?.sendEmptyMessageDelayed(1, mTimePeriod.toLong())
        mPaint.color = Color.parseColor("#ffffff")
    }

    /**
     * 设置重绘的周期
     * @param timePeriod
     */
    fun setTimePeriod(timePeriod: Int) {
        if (mTimePeriod > 0) {
            this.mTimePeriod = timePeriod
        }
        startAnimationAlpha()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //通过widthMeasureSpec,heightMeasureSpec 来获取view的测量模式和宽高
        var width = getValue(widthMeasureSpec, true);
        var height = getValue(heightMeasureSpec, false);
        //通过此方法来设置view的具体宽高
        setMeasuredDimension(width, height)
    }

    /**
     * @method  测量View的宽高
     * @description
     * @date: 2021/3/20 11:45
     * @author: wei.yang
     * @param measureSpec measurespec
     * @param isWidth true测量高度，false测量宽度
     * @return 测量后的宽高
     */
    private fun getValue(measureSpec: Int, isWidth: Boolean): Int {
        //获取测量模式
        val mode = MeasureSpec.getMode(measureSpec)
        //获取测量后的值
        val size = MeasureSpec.getSize(measureSpec)
        /**
         * 如果父控件传递给的MeasureSpec的mode是MeasureSpec.UNSPECIFIED，就说明，父控件对自己没有任何限制，那么尺寸就选择自己需要的尺寸size

        如果父控件传递给的MeasureSpec的mode是MeasureSpec.EXACTLY，就说明父控件有明确的要求，希望自己能用measureSpec中的尺寸，这时就推荐使用MeasureSpec.getSize(measureSpec)

        如果父控件传递给的MeasureSpec的mode是MeasureSpec.AT_MOST，就说明父控件希望自己不要超出MeasureSpec.getSize(measureSpec)，如果超出了，就选择MeasureSpec.getSize(measureSpec)，否则用自己想要的尺寸就行了
         */
        when (mode) {
            MeasureSpec.EXACTLY -> {//固定模式
                //子view的大小已经被限定死，我们只能使用其固定大小
                return size;
            }
            MeasureSpec.AT_MOST -> {//最大模式
                //父控件认为子view的大小不能超过size的值，那么我们就取size和默认值之间的最小值
                return Math.min(if (isWidth) defaultWidth else defaultHeight, size)
            }
            MeasureSpec.UNSPECIFIED -> {//不限制模式
                //父view不限定子view的大小，我们将其值设置为默认值
                return if (isWidth) defaultWidth else defaultHeight
            }
            else -> {
                return if (isWidth) defaultWidth else defaultHeight
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        //如果宽度小于进度条的宽度就抛出异常
        require(mWidth >= mProgressWidth) {
            //如果宽度小于进度条的宽度
            "the progressWidth must less than mWidth"
        }
        mPaint.strokeWidth = mHeight.toFloat()
    }

    /**
     * 防抖音短视频加载进度条
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //首先判断进度条的宽度是否大于view宽度
        if (mProgressWidth < mWidth) {
            //如果不大于，将进度条宽度增加10
            mProgressWidth += 50 //注意执行此步骤是mProgressWidth值有可能大于view宽度；

        } else {
            //如果进度条宽度大于view宽度将进度条宽度设置为初始宽度
            mProgressWidth = 10
        }
        //计算颜色透明度
        //mProgressWidth/mWidth 计算当前进度条宽度占总宽度的比例
        //255*mProgressWidth/mWidth 计算当前比例下对应的透明度值
        //由于是由不透明变成全透明，所以使用255减去其值
        var currentColorValue = 255 - 255 * mProgressWidth / mWidth
        if (currentColorValue > 255) {
            //由于mProgressWidth有可能大于view的宽度，要保证颜色值不能大于255
            currentColorValue = 255
        }
        if (currentColorValue < 30) {
            //此处是为了限制让其不成为全透明，如果设置为全透明，在最后阶段进度宽度渐变观察不到
            currentColorValue = 30
        }
        //将透明度转换为16进制
        val s = Integer.toHexString(currentColorValue)
        //拼接颜色字符串并转化为颜色值
        val color =
            Color.parseColor("#" + s + "ffffff")
        //给画笔设置颜色
//        mPaint.color = color
        //绘制线
        canvas?.drawLine(
            mWidth.toFloat() / 2 - mProgressWidth.toFloat() / 2,
            defaultHeight.toFloat() / 2,
            mWidth.toFloat() / 2 + mProgressWidth.toFloat() / 2,
            defaultHeight.toFloat() / 2,
            mPaint
        )


    }


    /**
     * @method  给指定的View设置动画，动画效果为改变其透明度
     * @description
     * @date: 2021/3/22 9:28
     * @author: wei.yang
     * @param view 指定的View
     */
    fun startAnimationAlpha() {
        val alpha1 = ObjectAnimator.ofFloat(this, "alpha", 0.3f, 0.8f)
        alpha1.duration = 300
        alpha1.repeatCount  = ValueAnimator.INFINITE
        val alph2 = ObjectAnimator.ofFloat(this, "alpha", 0.8f, 0f)
        alph2.duration = 300
        alph2.repeatCount  = ValueAnimator.INFINITE
        alpha1.addListener(object:Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                alph2.start()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        alph2.addListener(object:Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                alpha1.start()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }

}