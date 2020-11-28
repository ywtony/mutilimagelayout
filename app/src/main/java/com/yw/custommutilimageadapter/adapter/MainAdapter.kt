package com.yw.custommutilimageadapter.adapter

import android.content.Context
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.bean.ClassBean
import org.byteam.superadapter.SuperViewHolder

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.adapter
 * @ClassName:      MainAdapter
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2020/11/28 14:37
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/28 14:37
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class MainAdapter(
    context: Context?,
    mData: MutableList<ClassBean>?,
    listener: OnListItemClickListener<ClassBean>?
) : BaseListAdapter<ClassBean>(context, mData, R.layout.item_main, listener) {
    override fun onBindData(
        holder: SuperViewHolder,
        viewType: Int,
        layoutPosition: Int,
        data: ClassBean
    ) {
       holder.setText(R.id.tvTitle,data.title)
    }
}