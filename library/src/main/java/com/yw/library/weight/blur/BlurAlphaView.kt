package com.yw.library.weight.blur

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.yw.library.R
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      BlurAlphaView
 * @Description:    半透明模糊效果
 * @Author:         wei.yang
 * @CreateDate:     2021/1/27 19:10
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/27 19:10
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class BlurAlphaView : RelativeLayout {
//    var blurAlphaColor: Int = 9
//    var blurWidth: Int = 0
//    var blurHeight: Int = 0
//    var blurAlpha: Int = 0
    var ivBg: ImageView? = null
    var tvBg: TextView? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.blur)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            blurAlphaColor = typeArray.getInteger(
//                R.styleable.blur_blurAlphaColor,
//                context.getColor(R.color.black)
//            )
//        }
//        blurWidth = typeArray.getDimensionPixelSize(R.styleable.blur_blurWidth, 100)
//        blurHeight = typeArray.getDimensionPixelSize(R.styleable.blur_blurHeight, 100)
//        blurAlpha = typeArray.getInteger(R.styleable.blur_blurAlpha, 100)
//        typeArray.recycle()
//        initViews()
    }

    private fun initViews() {

    }

    /**
     * 开始执行半透明模糊效果
     * @description
     * @date: 2021/1/27 19:50
     * @author: wei.yang
     * @param bgLayout 要截取模糊的layout
     * @param blurWidth 模糊区域的宽度
     * @param blurHeight 模糊区域的高度
     * @param blurAlphaColor 模糊图像上面蒙版的颜色
     * @param blurAlpha 模糊图像上面蒙版颜色的透明度
     */
    fun startBlur(bgLayout: View,blurWidth:Int,blurHeight:Int,blurAlphaColor:Int,blurAlpha:Int) {
        removeAllViews()
        //创建view，并将view添加到RelativeLayout中
        ivBg = ImageView(context)
        val ivBgParams = RelativeLayout.LayoutParams(PxUtils.dp2px(context, blurWidth.toFloat()), PxUtils.dp2px(context, blurHeight.toFloat()))
        ivBg?.layoutParams = ivBgParams
        tvBg = TextView(context)
        val tvBgParams = RelativeLayout.LayoutParams(PxUtils.dp2px(context, blurWidth.toFloat()), PxUtils.dp2px(context, blurHeight.toFloat()))
        tvBg?.setBackgroundColor(blurAlphaColor)
        tvBg?.layoutParams = tvBgParams
        this.addView(ivBg)
        this.addView(tvBg)
        //设置tvBg的透明度
        tvBg?.background?.alpha = blurAlpha
        ivBg?.post {
            val position = IntArray(2)
            ivBg?.getLocationInWindow(position)
            val drawableBitmap: Bitmap =
                BlurAlphaUtil.getBitmapFromView(bgLayout)
            val clipBitmap =
                Bitmap.createBitmap(drawableBitmap, position[0], position[1],
                    ivBg?.measuredWidth!!, ivBg?.measuredHeight!!)
            val blurBmp: Bitmap = BlurAlphaUtil.fastblur(context, clipBitmap, 25) //0-25，表示模糊值
            ivBg?.setImageBitmap(blurBmp) //设置背景
        }
    }


}