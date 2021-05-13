package com.yw.library.weight

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.yw.library.R
import com.yw.library.utils.PxUtils
import java.math.BigDecimal

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      GoodsPriceView
 * @Description:    商品价格View
 * @Author:         wei.yang
 * @CreateDate:     2021/5/11 18:00
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/11 18:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class GoodsPriceView : FrameLayout {
    private var priceRMB: TextView? = null
    private var priceStart: TextView? = null
    private var priceEnd: TextView? = null
    private var priceColor: Int = 0//字体颜色
    private var _priceRMB = 0//RMB符号文字大小
    private var _priceStart = 0//价格证书部分文字大小
    private var _priceEnd = 0//价格小数本分文字大小

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_goods_price, null)
        priceRMB = view.findViewById(R.id.tvStart)
        priceStart = view.findViewById(R.id.tvMiddle)
        priceEnd = view.findViewById(R.id.tvEnd)
        removeAllViews()
        addView(view)

        //得到数据后直接转化为dp
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.price)
        priceColor = typeArray.getResourceId(R.styleable.price_priceColor, 0)
        _priceRMB = typeArray.getInteger(R.styleable.price_priceRMB, 0)
        _priceStart = typeArray.getInteger(R.styleable.price_priceStartSize, 0)
        _priceEnd = typeArray.getInteger(R.styleable.price_priceEndSize, 0)
        typeArray?.recycle()
    }

    /**
     * 设置价格
     */
    fun setPriceSource(price: String) {
        if (price != null) {
            if (priceColor > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    priceRMB?.setTextColor(context.getColor(priceColor))
                    priceStart?.setTextColor(context.getColor(priceColor))
                    priceEnd?.setTextColor(context.getColor(priceColor))
                }
            }
            if (_priceRMB > 0) {

                priceRMB?.textSize = PxUtils.px2dp(context, _priceRMB.toFloat()).toFloat()

            }
            if (_priceStart > 0) {
                priceStart?.textSize = PxUtils.px2dp(context, _priceStart.toFloat()).toFloat()

            }
            if (_priceEnd > 0) {
                priceEnd?.textSize = PxUtils.px2dp(context, _priceEnd.toFloat()).toFloat()
            }
            val arr = getRoundUp(price?.toDouble(), 2)?.split(".")
            arr?.let {
                priceStart?.text = arr[0]
                priceEnd?.text = "." + arr[1]
            }
        }

    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    fun getRoundUp(value: BigDecimal, digit: Int): String? {
        return value.setScale(digit, BigDecimal.ROUND_HALF_UP).toString()
    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    fun getRoundUp(value: Double, digit: Int): String? {
        val result = BigDecimal(value)
        return result.setScale(digit, BigDecimal.ROUND_HALF_UP).toString()
    }
}