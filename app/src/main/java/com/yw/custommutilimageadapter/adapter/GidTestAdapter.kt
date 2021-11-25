package com.yw.custommutilimageadapter.adapter

import android.content.Context
import android.view.View
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.bean.ClassBean
import com.yw.custommutilimageadapter.utils.GlideUtils
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
class GidTestAdapter(
    context: Context?,
    mData: ArrayList<String>?,
    listener: OnListItemClickListener<String>?
) : BaseListAdapter<String>(context, mData, R.layout.item_gif_test, listener) {
    override fun onBindData(
        holder: SuperViewHolder,
        viewType: Int,
        layoutPosition: Int,
        data: String
    ) {
        if (data != null && !"".equals(data)) {
            holder.setVisibility(R.id.ivGif, View.VISIBLE)
            GlideUtils.getInstance().loadNetGif(
                context,
                data,
                holder.findViewById(R.id.ivGif)
            )
        } else {
            holder.setVisibility(R.id.ivGif, View.GONE)
        }


    }
}