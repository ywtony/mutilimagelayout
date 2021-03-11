package com.yw.library.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.utils
 * @ClassName:      BitmapUtils
 * @Description:     Bitmap工具
 * @Author:         wei.yang
 * @CreateDate:     2021/2/8 16:14
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/8 16:14
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class BitmapUtils {
    /**
     * @description 将图片切割成若干份
     * @date: 2021/2/8 16:15
     * @author: wei.yang
     */
    fun splitBitmap(bitmap: Bitmap, rows: Int, cols: Int): ArrayList<Bitmap> {
        val minWidth = bitmap.width / cols
        val minHeight = bitmap.height / rows
        val list = ArrayList<Bitmap>()
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val cellBitmap = Bitmap.createBitmap(
                    bitmap,
                    (col * minWidth),
                    (row * minHeight),
                    minWidth,
                    minHeight
                )
                list.add(cellBitmap)
            }
        }

        return list
    }

    /**
     * Drawable 转换成 Bitmap
     * @param drawable
     * @return
     */
    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var bitmap: Bitmap?
        val width = Math.max(drawable.intrinsicWidth, 2)
        val height = Math.max(drawable.intrinsicHeight, 2)
        try {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
        } catch (e: Throwable) {
            e.printStackTrace()
            bitmap = null
        }
        return bitmap!!
    }

}
