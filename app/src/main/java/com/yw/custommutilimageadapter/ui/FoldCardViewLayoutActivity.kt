package com.yw.custommutilimageadapter.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.adapter.FoldAdapter
import com.yw.custommutilimageadapter.adapter.FoldCardViewAdapter
import com.yw.custommutilimageadapter.bean.FoldBean
import com.yw.custommutilimageadapter.utils.PxUtils
import com.yw.library.weight.FoldLayoutView
import kotlinx.android.synthetic.main.activity_fold_layout_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      FoldCardViewLayoutActivity
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2020/11/28 18:00
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/28 18:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FoldCardViewLayoutActivity : Activity() {
    private var adapter: FoldCardViewAdapter? = null
    private var datas = ArrayList<FoldBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foldcardview_layout)
        initViews()
    }

    private fun initViews() {
        datas.add(FoldBean(R.drawable.girl1))
        datas.add(FoldBean(R.drawable.girl2))
        datas.add(FoldBean(R.drawable.girl3))
        datas.add(FoldBean(R.drawable.girl4))
        datas.add(FoldBean(R.drawable.girl1))
        datas.add(FoldBean(R.drawable.girl2))
        datas.add(FoldBean(R.drawable.girl3))
        datas.add(FoldBean(R.drawable.girl4))
        adapter = FoldCardViewAdapter(this@FoldCardViewLayoutActivity, datas)
//        foldView.setAdapter(getDataViews())
        foldView.setAdapter(adapter!!)
        foldView.setOnItemClickListener(object : FoldLayoutView.OnItemClickListener {
            override fun onItemClick(index: Int) {
                Toast.makeText(this@FoldCardViewLayoutActivity, "哈哈哈：$index", Toast.LENGTH_SHORT)
                    .show()
            }

        })
        foldViewH.setAdapter(adapter!!)
        foldViewH.setOnItemClickListener(object : FoldLayoutView.OnItemClickListener {
            override fun onItemClick(index: Int) {
                Toast.makeText(this@FoldCardViewLayoutActivity, "哈哈哈：$index", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun getDataViews(): ArrayList<View> {
        val views = ArrayList<View>()
        val img1 = ImageView(this)
        img1.setImageResource(R.drawable.girl1)
        img1.scaleType = ImageView.ScaleType.CENTER_CROP
        val img2 = ImageView(this)
        img2.setImageResource(R.drawable.girl2)
        img2.scaleType = ImageView.ScaleType.CENTER_CROP
        val img3 = ImageView(this)
        img3.setImageResource(R.drawable.girl3)
        img3.scaleType = ImageView.ScaleType.CENTER_CROP
        val img4 = ImageView(this)
        img4.setImageResource(R.drawable.girl4)
        img4.scaleType = ImageView.ScaleType.CENTER_CROP
        setLayoutParams(img1)
        setLayoutParams(img2)
        setLayoutParams(img3)
        setLayoutParams(img4)
        views.add(img1)
        views.add(img2)
        views.add(img3)
        views.add(img4)
        views.add(img1)
        views.add(img2)
        views.add(img3)
        views.add(img4)


        return views
    }

    private fun setLayoutParams(ivImage: ImageView) {
        val params = ViewGroup.LayoutParams(
            PxUtils.dp2px(this@FoldCardViewLayoutActivity, 50f),
            PxUtils.dp2px(this@FoldCardViewLayoutActivity, 50f)
        )
        ivImage.layoutParams = params
    }
}