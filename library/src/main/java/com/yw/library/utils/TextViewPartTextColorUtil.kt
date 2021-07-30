package com.yw.library.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.utils
 * @ClassName:      TextViewPartTextColorUtil
 * @Description:     改变TextView部分字体颜色
 * @Author:         wei.yang
 * @CreateDate:     2021/6/17 9:42
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/17 9:42
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
object TextViewPartTextColorUtil {
    /**
     * @description 改变TextView的部分字体颜色
     * @date: 2021/6/17 9:44
     * @author: wei.yang
     * @param view 要改变字体颜色的TextView
     * @param textColor 字体颜色
     * @param allString 整串字符串
     * @param partString 要改变颜色的部分字符串
     */
    fun showPartTextColor(
        view: TextView,
        textColor: Int,
        allString: String,
        partString: String
    ) {
        val spannableStringBuilder = SpannableStringBuilder(allString)
        for (index in allString.indices) {
            var start = allString.indexOf(partString, index)
            var end = start + partString.length
            if (start != -1) {
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(textColor),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }
        }

        view.text = spannableStringBuilder
    }

    /**
     * @description
     * @date: 2021/6/17 10:44
     * @author: wei.yang
     * @param view 要设置的TextView
     * @param textColors 颜色列表
     * @param allString 全字符串
     * @param partStrings 要变颜色的字符串列表
     * @return
     */
    fun showPartTextColors(
        view: TextView,
        textColors: Array<Int>,
        allString: String,
        partStrings: Array<String>
    ) {
        val spannableStringBuilder = SpannableStringBuilder(allString)
        var len = textColors.size
        for (index in 0 until len) {
            var partStr = partStrings[index]
            var start = allString.indexOf(partStr)
            var end = start + partStr.length
            if (start != -1) {
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(textColors[index]),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            }
        }
        view.text = spannableStringBuilder
    }

    fun showPartTextColor(
        view: TextView,
        textColor: String,
        allString: String,
        partString: String
    ) {
        var start = allString.indexOf(partString)
        var end = start + partString.length
        val spannableStringBuilder = SpannableStringBuilder(allString)
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(Color.parseColor(textColor)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        view.text = spannableStringBuilder
    }
}