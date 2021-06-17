package com.yw.library.weight.cshape

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      CustomShape
 * @Description:    自定义Shape
 * @Author:         wei.yang
 * @CreateDate:     2021/5/13 15:49
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/13 15:49
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CustomShapeDrawable : GradientDrawable() {
    /**
     * @description 更新Drawable
     * @date: 2021/5/13 15:59
     * @author: wei.yang
     * @param strokeColor 边框颜色
     * @param strokeWidth 边框宽度
     * @param solidColor 背景颜色
     * @param radius 背景的圆角大小
     */
    fun updateDrawable(
        context: Context,
        strokeColor: Int,
        strokeWidth: Float,
        solidColor: Int,
        radius: Float
    ): CustomShapeDrawable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStroke(PxUtils.dp2px(context, strokeWidth.toFloat()), context.getColor(strokeColor))
            cornerRadius = PxUtils.dp2px(context, radius).toFloat()
            setColor(context.getColor(solidColor))
        }
        return this
    }

}