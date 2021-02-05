package com.yw.library.weight.arc

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import com.yw.library.R

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      ArcImageView
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/1/29 14:28
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/29 14:28
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ArcImageView : androidx.appcompat.widget.AppCompatImageView {
    private var mArcHeight = 100
    constructor(context: Context?):super(context!!){

    }
    constructor(context: Context?, attrs: AttributeSet?):super(context!!,attrs){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.app)
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.app_arcHeight, 0)
    }

    override fun onDraw(canvas: Canvas) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(0f, (height - mArcHeight).toFloat())
        path.quadTo((width / 2).toFloat(), (height + mArcHeight).toFloat(), width.toFloat(), (height - mArcHeight).toFloat())
        path.lineTo(width.toFloat(), 0f)
        path.close()
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

}