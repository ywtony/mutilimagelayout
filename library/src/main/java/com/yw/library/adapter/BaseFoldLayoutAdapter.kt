package com.yw.library.adapter

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.widget
 * @ClassName:      BaseFoldLayoutAdapter
 * @Description:     FoldLayoutView的数据适配器
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 16:27
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 16:27
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
interface BaseFoldLayoutAdapter {
     /**
      * @description 获取Item的总长度
      * @date: 2020/11/26 19:20
      * @author: wei.yang
      */
    open fun getItemsCount(): Int
     /**
      * @description 获取ItemView
      * @date: 2020/11/26 19:20
      * @author: wei.yang
      */
    open fun getItem(
        index: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View?




}