package com.yw.custommutilimageadapter.utils

import android.content.Context
import android.content.Intent

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.utils
 * @ClassName:      ActivityUtils
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 17:28
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 17:28
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
object ActivityUtils {
    fun startActivity(context:Context,className:String){
        val intent = Intent()
        intent.setClassName(context,className)
        context.startActivity(intent)

    }
}