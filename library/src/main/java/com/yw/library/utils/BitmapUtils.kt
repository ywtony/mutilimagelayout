package com.yw.library.utils

import android.graphics.Bitmap

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
}