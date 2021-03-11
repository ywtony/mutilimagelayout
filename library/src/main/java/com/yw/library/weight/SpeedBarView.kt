package com.yw.library.weight

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.yw.library.R
import com.yw.library.utils.PxUtils
import org.w3c.dom.Text

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      SpeedBarView
 * @Description:    快慢速View
 * @Author:         wei.yang
 * @CreateDate:     2021/3/11 15:10
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/11 15:10
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SpeedBarView : LinearLayout {
    private lateinit var tvLeft: TextView
    private lateinit var tvMiddle: TextView
    private lateinit var tvRight: TextView
    private val color_262626 = "#262626"
    private val color_ffffff = "#ffffff"

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    /**
     * 初始化View
     */
    private fun init() {
        weightSum = 3f//分三等分
        orientation = HORIZONTAL//横向排列
        gravity = Gravity.CENTER_VERTICAL
//        setBackgroundColor(Color.parseColor("#373737"))
        setBackgroundResource(R.drawable.bg_ugc_speed_bar_layout)
        background.alpha = 100//设置背景的透明度
        tvLeft = TextView(context)
        tvLeft.text = "慢速"
        tvLeft.gravity = Gravity.CENTER
        tvLeft.setTextColor(Color.parseColor(color_ffffff))
        tvMiddle = TextView(context)
        tvMiddle.setBackgroundResource(R.drawable.bg_ugc_speed_bar_item)
        tvMiddle.text = "标准"
        tvMiddle.gravity = Gravity.CENTER
        tvMiddle.setTextColor(Color.parseColor(color_262626))
        tvRight = TextView(context)
        tvRight.text = "快速"
        tvRight.gravity = Gravity.CENTER
        tvRight.setTextColor(Color.parseColor(color_ffffff))
        setTextStyle(tvLeft)
        setTextStyle(tvMiddle)
        setTextStyle(tvRight)
        var params = LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.weight = 1f
        tvLeft.layoutParams = params
        tvMiddle.layoutParams = params
        tvRight.layoutParams = params
        tvLeft.setOnClickListener {
            tvLeft.setBackgroundResource(R.drawable.bg_ugc_speed_bar_item)
            tvLeft.setTextColor(Color.parseColor(color_262626))
            tvMiddle.background = null
            tvMiddle.setTextColor(Color.parseColor(color_ffffff))
            tvRight.background = null
            tvRight.setTextColor(Color.parseColor(color_ffffff))
            callback?.onSpeedItemClick(1)
        }
        tvMiddle.setOnClickListener {
            tvLeft.background = null
            tvLeft.setTextColor(Color.parseColor(color_ffffff))
            tvMiddle.setBackgroundResource(R.drawable.bg_ugc_speed_bar_item)
            tvMiddle.setTextColor(Color.parseColor(color_262626))
            tvRight.background = null
            tvRight.setTextColor(Color.parseColor(color_ffffff))
            callback?.onSpeedItemClick(2)
        }
        tvRight.setOnClickListener {
            tvLeft.background = null
            tvLeft.setTextColor(Color.parseColor(color_ffffff))
            tvMiddle.background = null
            tvMiddle.setTextColor(Color.parseColor(color_ffffff))
            tvRight.setBackgroundResource(R.drawable.bg_ugc_speed_bar_item)
            tvRight.setTextColor(Color.parseColor(color_262626))
            callback?.onSpeedItemClick(3)
        }

        addView(tvLeft)
        addView(tvMiddle)
        addView(tvRight)

    }

    private fun setTextStyle(tv: TextView) {

        //设置字体大小
        tv.textSize = 15f
        //设置字体颜色
        tv.typeface = Typeface.DEFAULT_BOLD
    }

    fun setSpeedCallback(callback: SpeedCallback?) {
        this.callback = callback

    }

    private var callback: SpeedCallback? = null

    interface SpeedCallback {
        fun onSpeedItemClick(position: Int)
    }
}