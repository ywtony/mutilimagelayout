package com.yw.library.weight

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.yw.library.R
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library
 * @ClassName:      TagPaddingView
 * @Description:     可带标签的TextView，前标签和后标签都可以的那种
 * @Author:         wei.yang
 * @CreateDate:     2021/5/13 9:45
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/13 9:45
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TagPaddingView : RelativeLayout {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

    }

    /**
     * 设置标签TextView的配置属性
     */
    fun setPaddingViewConfig(tagConfig: TagViewConfig, valueConfig: ValueViewConfig) {
        removeAllViews()
        //创建标签View
        val tagView = TextView(context)
        tagView.text = tagConfig.tagValue
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tagView.setTextColor(context.getColor(tagConfig.tagTextColor))
        }
        tagView.textSize = PxUtils.px2dp(context, tagConfig.tagTextSize).toFloat()
        tagView.setBackgroundResource(tagConfig.tagBackgroundResource)
        tagView.background.alpha = tagConfig.tagBackgroundAlpha
        tagView.setPadding(5,2,5,2)
        //创建文本内容TextView
        val valueView = TextView(context)
        valueView.text = valueConfig.vValue
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            valueView.setTextColor(context.getColor(valueConfig.vTextColor))
        }
        valueView.textSize = PxUtils.px2dp(context, valueConfig.vTextSize).toFloat()
        tagView.post {
            val w = tagView.measuredWidth//获取tagView测量宽度
            valueView.setPadding(w + 5, 0, 0, 0)
        }
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.ALIGN_BASELINE)
        val params2 = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params2.addRule(RelativeLayout.ALIGN_BASELINE)
        tagView.layoutParams = params
        valueView.layoutParams = params2
        //将设置好的View添加进去
        addView(tagView)
        addView(valueView)
    }

    /**
     * 标签设置
     */
    class TagViewConfig(
        //标签值
        var tagValue: String,
        //标签颜色
        var tagTextColor: Int,
        //标签字体大小
        var tagTextSize: Float,
        //标签背景
        var tagBackgroundResource: Int,
        //标签背景透明度
        var tagBackgroundAlpha: Int
    )

    /**
     * 值设置
     */
    class ValueViewConfig(
        //值
        var vValue: String,
        //颜色
        var vTextColor: Int,
        //字体大小
        var vTextSize: Float
    )
}