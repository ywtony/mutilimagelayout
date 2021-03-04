package com.yw.library.weight

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.yw.library.R
import com.yw.library.utils.PxUtils
import kotlin.math.ceil

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      MyTaskProgressBar
 * @Description:    自定义一个任务进度
 * @Author:         wei.yang
 * @CreateDate:     2021/3/4 15:53
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/4 15:53
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class MyTaskProgressBar : View {
    //外边框颜色值
    private var outerBorderColor: Int = 0
    private var outerBorderColor2: Int = 0

    //字体的两种颜色
    private var textColor1: Int = 0
    private var textColor2: Int = 0

    //进度条的两种颜色
    private var progressColor: Int = 0
    private var progressColor2: Int = 0

    //布局的宽度和高度
    private var viewWidth = 0
    private var viewHeight = 0

    //圆圈的paint
    private var paintCircle: Paint = Paint()

    //紫色线段画笔
    private var paintLine1: Paint = Paint()

    //黄色线段画笔
    private var paintLine2: Paint = Paint()

    //字体画笔
    private var paintText1: Paint = Paint()
    private var paintText2: Paint = Paint()

    //bitmap画笔
    private var paintBitmap: Paint = Paint()

    //字体高度
    private var mTxtHeight: Float = 0f

    //进度0~100
    private var progress: Float = 0.70f
    /**
     * 0:<25%
     * 1:>2=25%<50%
     * 2:>=50%<=75%
     * 3:>75%<100
     * 4:=100%
     */
    private var progressType: Int = 0//砍一刀进度
    //
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paintCircle?.color = Color.parseColor("#FCF05D")
        paintCircle?.strokeWidth = PxUtils.dp2px(context, 1.5f).toFloat()
        paintCircle?.style = Paint.Style.STROKE


        paintLine1?.color = Color.parseColor("#7FCF111A")
        paintLine1?.strokeWidth = PxUtils.dp2px(context, 11f).toFloat()
        paintLine1?.style = Paint.Style.FILL

        paintLine2?.color = Color.parseColor("#FFF35F")
        paintLine2?.strokeWidth = PxUtils.dp2px(context, 8f).toFloat()
        paintLine2?.style = Paint.Style.FILL

        paintText1?.color = Color.parseColor("#000000")
        paintText1?.strokeWidth = PxUtils.dp2px(context, 8f).toFloat()
        paintText1?.style = Paint.Style.FILL
        paintText1?.textSize = PxUtils.sp2px(context, 12f).toFloat()
        val fm: Paint.FontMetrics = paintText1.fontMetrics
        mTxtHeight = ceil(fm.descent - fm.ascent.toDouble()).toFloat()

        paintText2?.color = Color.parseColor("#000000")
        paintText2?.strokeWidth = PxUtils.dp2px(context, 8f).toFloat()
        paintText2?.style = Paint.Style.FILL
        paintText2?.textSize = PxUtils.sp2px(context, 12f).toFloat()
        mTxtHeight = ceil(fm.descent - fm.ascent.toDouble()).toFloat()


    }

    /**
     * @description 绘制出指定的进度
     * @date: 2021/3/4 15:55
     * @author: wei.yang
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //view的宽高
        viewWidth = width
        viewHeight = height
        //线段的总长度
        var lineTotalWidth = viewWidth - PxUtils.dp2px(context, 177f).toFloat()
        //平均每段线段的长度
        var lineWidth = lineTotalWidth / 3
        when(progressType){
            0->{
                drawType1(canvas,lineWidth)
            }
            1->{
                drawType2(canvas,lineWidth)
            }
            2->{
                drawType3(canvas,lineWidth)
            }
            else->{
                drawType4(canvas,lineWidth)
            }
        }



    }
    private fun drawType1(canvas:Canvas,lineWidth:Float){
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        //绘制第一个圆环
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(canvas, "0元", PxUtils.dp2px(context, 18f).toFloat(), height / 2f)
        //绘制第一条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() + lineWidth,
            height / 2f,
            paintLine1
        )
        //        //绘制第二条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2,
            height / 2f,
            paintLine1
        )
        //        //绘制第三条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            paintLine1
        )
        if (progress <= 0.25) {
            //进度=lineWidth*progress/25

            //绘制进度条
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f
            )
        } else if (progress > 0.25 && progress <= 0.5) {
            //进度=lineWidth+36dp+lineWidth*(progress-0.25)/0.25
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f
            )
        } else {
            //进度=lineWidth+36dp+lineWidth+44+lineWidth*(progress-0.5)/0.5
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,

                (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(),
                height / 2f,
                paintLine2
            )
            drawBitmap(
                canvas, (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(), height / 2f
            )
        }
//        //绘制第二个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "25元",
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f
        )

//        //绘制第三个圆环
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "50元",
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f
        )


//        //绘制第四个圆环
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText2(
            canvas,
            "100元",
            "现金红包",
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f
        )
    }
    private fun drawType2(canvas: Canvas, lineWidth: Float){
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        //绘制第一个圆环
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(canvas, "0元", PxUtils.dp2px(context, 18f).toFloat(), height / 2f)
        //绘制第一条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() + lineWidth,
            height / 2f,
            paintLine1
        )
        //        //绘制第二条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2,
            height / 2f,
            paintLine1
        )
        //        //绘制第三条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            paintLine1
        )
        if (progress <= 0.25) {
            //进度=lineWidth*progress/25

            //绘制进度条
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f
            )
        } else if (progress > 0.25 && progress <= 0.5) {
            //进度=lineWidth+36dp+lineWidth*(progress-0.25)/0.25
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f
            )
        } else {
            //进度=lineWidth+36dp+lineWidth+44+lineWidth*(progress-0.5)/0.5
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,

                (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(),
                height / 2f,
                paintLine2
            )
            drawBitmap(
                canvas, (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(), height / 2f
            )
        }
//        //绘制第二个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "25元",
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f
        )

//        //绘制第三个圆环
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "50元",
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f
        )


//        //绘制第四个圆环
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText2(
            canvas,
            "100元",
            "现金红包",
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f
        )
    }
    private fun drawType3(canvas: Canvas, lineWidth: Float) {
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        //绘制第一个圆环
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(canvas, "0元", PxUtils.dp2px(context, 18f).toFloat(), height / 2f)
        //绘制第一条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() + lineWidth,
            height / 2f,
            paintLine1
        )
        //        //绘制第二条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2,
            height / 2f,
            paintLine1
        )
        //        //绘制第三条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            paintLine1
        )
        if (progress <= 0.25) {
            //进度=lineWidth*progress/25

            //绘制进度条
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f
            )
        } else if (progress > 0.25 && progress <= 0.5) {
            //进度=lineWidth+36dp+lineWidth*(progress-0.25)/0.25
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f
            )
        } else {
            //进度=lineWidth+36dp+lineWidth+44+lineWidth*(progress-0.5)/0.5
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,

                (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(),
                height / 2f,
                paintLine2
            )
            drawBitmap(
                canvas, (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(), height / 2f
            )
        }
//        //绘制第二个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "25元",
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f
        )

//        //绘制第三个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "50元",
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f
        )


//        //绘制第四个圆环
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText2(
            canvas,
            "100元",
            "现金红包",
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f
        )
    }
    private fun drawType4(canvas: Canvas, lineWidth: Float){
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        //绘制第一个圆环
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(canvas, "0元", PxUtils.dp2px(context, 18f).toFloat(), height / 2f)
        //绘制第一条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat(),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() + lineWidth,
            height / 2f,
            paintLine1
        )
        //        //绘制第二条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2,
            height / 2f,
            paintLine1
        )
        //        //绘制第三条线段
        canvas?.drawLine(
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 2 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 36f).toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            paintLine1
        )
        if (progress <= 0.25) {
            //进度=lineWidth*progress/25

            //绘制进度条
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f).toFloat() + lineWidth * progress / 0.25).toFloat(),
                height / 2f
            )
        } else if (progress > 0.25 && progress <= 0.5) {
            //进度=lineWidth+36dp+lineWidth*(progress-0.25)/0.25
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f,
                paintLine2
            )
            //绘制进度图片
            drawBitmap(
                canvas,
                (PxUtils.dp2px(context, 36f)
                    .toFloat() * 2 + lineWidth + lineWidth * (progress - 0.25) / 0.25).toFloat(),
                height / 2f
            )
        } else {
            //进度=lineWidth+36dp+lineWidth+44+lineWidth*(progress-0.5)/0.5
            canvas?.drawLine(
                PxUtils.dp2px(context, 36f).toFloat(),
                height / 2f,

                (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(),
                height / 2f,
                paintLine2
            )
            drawBitmap(
                canvas, (PxUtils.dp2px(context, 36f).toFloat() * 2
                        + lineWidth * 2 + (PxUtils.dp2px(context, 44f)
                    .toFloat() + lineWidth * (progress - 0.5) / 0.5)).toFloat(), height / 2f
            )
        }
//        //绘制第二个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f,
            PxUtils.dp2px(context, 18f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "25元",
            PxUtils.dp2px(context, 18f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() + lineWidth,
            height / 2f
        )

//        //绘制第三个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FF7201")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f,
            PxUtils.dp2px(context, 22f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText(
            canvas,
            "50元",
            PxUtils.dp2px(context, 22f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 2,
            height / 2f
        )


//        //绘制第四个圆环
        paintCircle?.style = Paint.Style.FILL
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        paintCircle?.style = Paint.Style.STROKE
        paintCircle?.color = Color.parseColor("#FFF35F")
        canvas?.drawCircle(
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f,
            PxUtils.dp2px(context, 30.5f).toFloat(),
            paintCircle
        )
        //绘制文本
        drawText2(
            canvas,
            "100元",
            "现金红包",
            PxUtils.dp2px(context, 30.5f).toFloat() + PxUtils.dp2px(context, 36f)
                .toFloat() * 2 + lineWidth * 3 + PxUtils.dp2px(context, 44f),
            height / 2f
        )
    }
    /**
     * @description 绘制文本
     * @date: 2021/3/4 17:24
     * @author: wei.yang
     */
    private fun drawText(canvas: Canvas, text: String, centerX: Float, centerY: Float) {
        //计算文本宽度
        val mTxtWidth = paintText1.measureText(text, 0, text.length)
        canvas?.drawText(text, centerX - mTxtWidth / 2, centerY + mTxtHeight / 4, paintText1)
    }

    /**
     * @description 设置两行文字
     * @date: 2021/3/4 17:38
     * @author: wei.yang
     */
    private fun drawText2(
        canvas: Canvas,
        text1: String,
        text2: String,
        centerX: Float,
        centerY: Float
    ) {
        //计算文本宽度
        val mTxtWidth1 = paintText1.measureText(text1, 0, text1.length)
        val mTxtWidth2 = paintText1.measureText(text2, 0, text2.length)
        canvas?.drawText(text1, centerX - mTxtWidth1 / 2, centerY - mTxtHeight / 2, paintText1)
        canvas?.drawText(text2, centerX - mTxtWidth2 / 2, centerY + mTxtHeight / 2, paintText1)
    }

    /**
     * @description 绘制进度图片
     * @date: 2021/3/4 17:39
     * @author: wei.yang
     */
    private fun drawBitmap(canvas: Canvas, processWidth: Float, centerY: Float) {
        //进度图片
        val bitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.bg_bragaining_progress_money)
        val l = processWidth - bitmap.width / 2
        val t = centerY - bitmap.height - PxUtils.dp2px(context, 9.9f)
        canvas.drawBitmap(bitmap, l, t, paintBitmap)
        //在图片上绘制文本
        drawText2(canvas,"99.99","元",processWidth,t+bitmap.height/2)

    }

    fun requestProgressBar(bar: Float) {
        this.progress = bar
        var progress = bar
        if (progress < 0.25) {
            progressType = 0
        } else if (progress >= 0.25 && progress < 0.5) {
            progressType = 1
        } else if (progress >= 0.5 && progress < 1) {
            progressType = 2
        } else {
            progressType = 3
        }
        invalidate()
    }
}