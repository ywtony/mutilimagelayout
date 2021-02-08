package com.yw.library.weight.ttcav

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      TrunTheCellAlphaView
 * @Description:    翻转小方格透明View
 * @Author:         wei.yang
 * @CreateDate:     2021/2/7 9:58
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/7 9:58
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TrunTheCellAlphaView : RoundedLayout {
    constructor(context: Context) : super(context, null, 0) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {

        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val imageView = ImageView(context)
        imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        addView(imageView)
    }
}