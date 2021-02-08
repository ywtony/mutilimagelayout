package com.yw.library.weight.ttcav

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.yw.library.R
import com.yw.library.utils.PxUtils


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.ttcav
 * @ClassName:      AlphaView
 * @Description:     半透明View
 * @Author:         wei.yang
 * @CreateDate:     2021/2/7 10:07
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/7 10:07
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class AlphaView : View {
    /**
     * 签到到少天了
     */
    private var days: Int = 1
    private var minWidth = 0;
    private var minHeight = 0
    private var currentRow = 0
    private var currentCol = 0
    private var matrix2: Matrix? = null
    private var camera: Camera? = null

    constructor(context: Context) : super(context, null, 0) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {

        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        matrix2 = Matrix()
        camera = Camera()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置背景颜色为黑色
            setBackgroundColor(context.getColor(R.color.black))
            background.alpha = 135
        }
    }

    override fun onDraw(canvas: Canvas?) {
//        postInvalidate()
        Log.e("AlphaView:", "执行了onDraw方法")
        val paintLine = Paint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paintLine.color = context.getColor(R.color.white)
        }
        paintLine.strokeWidth = PxUtils.dp2px(context, 2f).toFloat()
        val paintRect = Paint()
        //画线的时候使用
//        paint.strokeWidth = PxUtils.dp2px(context, 2f).toFloat()
        //画方块的时候使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paintRect.color = context.getColor(R.color.red)
        }
        paintRect.alpha = 135
        //获取view的宽度
        val width = measuredWidth
        val height = measuredHeight
        minWidth = width / 5
        minHeight = height / 6


//
//        canvas?.save()
//        camera?.save()
//        //饶x轴转
//        camera?.rotateX(60f)
//        //设置camera作用矩阵
//        camera?.getMatrix(matrix2)
//        camera?.restore()
//        //计算中心点坐标
//        var centerX =
//            ((currentCol * minWidth).toFloat() + minWidth - (currentCol * minWidth).toFloat()) / 2 + (currentCol * minWidth).toFloat()
//        var centerY =
//            ((currentRow * minHeight).toFloat() + minHeight - (currentRow * minHeight).toFloat()) / 2 + (currentRow * minHeight).toFloat()
//        //设置旋转中心点
//        matrix2?.preTranslate(centerX, centerY)
//        canvas?.setMatrix(matrix2)
//        canvas?.drawRect(
//            (currentCol * minWidth).toFloat(),
//            (currentRow * minHeight).toFloat(),
//            (currentCol * minWidth).toFloat() + minWidth,
//            (currentRow * minHeight).toFloat() + minHeight,
//            paintRect
//        )
//        canvas?.restore()

//        canvas?.save()
////        matrix2?.setRotate(60f)
//        canvas?.concat(matrix2)
//        canvas?.drawRect(
//            (currentCol * minWidth).toFloat(),
//            (currentRow * minHeight).toFloat(),
//            (currentCol * minWidth).toFloat() + minWidth,
//            (currentRow * minHeight).toFloat() + minHeight,
//            paintRect
//        )
//        canvas?.restore()


        /**
         * 1.判断如果是前三个格子，则不进行绘制
         */
        var rowCount = days / 5
        var alreadySignInWidth = days * minWidth
        //使用rect绘制6*5个小方格
        for (row in 0..5) {
            if (row >= rowCount) {
                for (col in 0..4) {
                    if (row * 5 * minWidth + col * minWidth >= alreadySignInWidth) {
                        //画小方块
                        canvas?.drawRect(
                            (col * minWidth).toFloat(),
                            (row * minHeight).toFloat(),
                            (col * minWidth).toFloat() + minWidth,
                            (row * minHeight).toFloat() + minHeight,
                            paintRect
                        )
                    }

                }
            }
        }

        for (row in 0..5) {
            for (col in 0..4) {
                //绘制分割线
                canvas?.drawLine(
                    (col * minWidth).toFloat() + minWidth,
                    (row * minHeight).toFloat(),
                    (col * minWidth).toFloat() + minWidth,
                    (row * minHeight).toFloat() + minHeight + PxUtils.dp2px(context, 2f),
                    paintLine
                )
                //绘制分割线
                canvas?.drawLine(
                    (col * minWidth).toFloat(),
                    (row * minHeight).toFloat() + minHeight,
                    (col * minWidth).toFloat() + minWidth + PxUtils.dp2px(context, 2f),
                    (row * minHeight).toFloat() + minHeight,
                    paintLine
                )
            }
        }
        super.onDraw(canvas)

    }


    /**
     * @description 给5x6的方格设置点击事件
     * @date: 2021/2/7 11:06
     * @author: wei.yang
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //获取view的宽度
        val width = measuredWidth
        val height = measuredHeight
        val minWidth = width / 5
        val minHeight = height / 6
        var clickX = event?.x
        var clickY = event?.y
        val newCellCountX = clickX?.div(minWidth)?.toInt()
        val newCellCountY = clickY?.div(minHeight)?.toInt()
        days = newCellCountY * 5 + newCellCountX + 1
        currentRow = newCellCountY
        currentCol = newCellCountX + 1
        if (listener != null) {
            //执行点击事件前执行翻转动画
            listener?.onDayClick(days, event.x, event.y)
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    private var listener: OnDayClickListener? = null
    fun setOnDayClickListener(listener: OnDayClickListener) {
        this.listener = listener
    }

    interface OnDayClickListener {
        fun onDayClick(day: Int, eventX: Float, eventY: Float)
    }

    //使用线绘制一个6行5列的小方格
//        for (row in 1..5) {
//            for (col in 1..4) {
//                //画行
//                canvas?.drawLine(
//                    0f,
//                    row.toFloat() * minHeight,
//                    width.toFloat(),
//                    row.toFloat() * minHeight,
//                    paint
//                )
//                //画列
//                canvas?.drawLine(
//                    col.toFloat() * minWidth,
//                    0f,
//                    col.toFloat() * minWidth,
//                    height.toFloat(),
//                    paint
//                )
//            }
//        }
}