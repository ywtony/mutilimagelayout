package com.yw.library.weight.cshape

import android.content.Context
import android.view.View

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.cshape
 * @ClassName:      ShapeUtils
 * @Description:     自定义Shape工具类
 * @Author:         wei.yang
 * @CreateDate:     2021/5/13 16:25
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/13 16:25
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
object ShapeUtils {
    /**
     * @description 更新Drawable
     * @date: 2021/5/13 15:59
     * @author: wei.yang
     * @param view 目标View
     * @param strokeColor 边框颜色
     * @param strokeWidth 边框宽度
     * @param solidColor 背景颜色
     * @param radius 背景的圆角大小
     */
    fun setBackground(
        view: View,
        strokeColor: Int,
        strokeWidth: Float,
        solidColor: Int,
        radius: Float
    ) {
        view.background = CustomShapeDrawable().updateDrawable(
            view.context,
            strokeColor,
            strokeWidth,
            solidColor,
            radius
        )
    }
}