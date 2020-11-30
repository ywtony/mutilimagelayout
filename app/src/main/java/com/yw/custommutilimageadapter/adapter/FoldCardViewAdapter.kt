package com.yw.custommutilimageadapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.bean.FoldBean
import com.yw.library.adapter.BaseFoldLayoutAdapter
import de.hdodenhof.circleimageview.CircleImageView

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.adapter
 * @ClassName:      FoldAdapter
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 19:21
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 19:21
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FoldCardViewAdapter : BaseFoldLayoutAdapter {
    private var context: Context? = null
    private var datas = ArrayList<FoldBean>()

    constructor(context: Context, datas: ArrayList<FoldBean>){
        this.context = context
        this.datas = datas
    }

    override fun getItemsCount(): Int {
        return datas.size
    }

    override fun getItem(index: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fold_cardview, null)
        }
        val ivGirl = view?.findViewById<ImageView>(R.id.ivCardView)
        ivGirl?.setImageResource(datas[index].resId)
        return view
    }



}