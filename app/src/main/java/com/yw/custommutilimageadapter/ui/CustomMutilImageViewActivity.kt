package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.CustomMutiImageView
import kotlinx.android.synthetic.main.activity_custom_mutil_imag_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      CustomMutilImageViewActivity
 * @Description:    多图展示样式
 * @Author:         wei.yang
 * @CreateDate:     2020/11/28 10:28
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/28 10:28
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CustomMutilImageViewActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_mutil_imag_view)
        initViews()
    }

    private fun initViews() {
        customMutiImageView.setAdapter(getDataViews(4), 0f)
        customMutiImageView2.setAdapter(getDataViews(4), 5f)
        customMutiImageViewOnLayout.setAdapter(getDataViews(1), 0f)
        customMutiImageViewTwoLayout.setAdapter(getDataViews(2), 5f)
        customMutiImageViewThreeLayout.setAdapter(getDataViews(3), 0f)
        customMutiOtherLayout.setAdapter(getCustomLayout(), 5f)
    }

    private fun getCustomLayout(): ArrayList<View> {
        val views = ArrayList<View>()
        for (index in 0 until 4) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_mutillayout, null)
            val customView =
                view.findViewById<CustomMutiImageView>(R.id.customMutiImageLittle)
            customView.setAdapter(getDataViews(4), 0f)
            views.add(view)
        }

        return views
    }

    private fun getDataViews(size: Int): ArrayList<View> {
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


        when (size) {
            1 -> {
                views.add(img4)
            }
            2 -> {
                views.add(img3)
                views.add(img4)
            }
            3 -> {
                views.add(img1)
                views.add(img2)
                views.add(img3)
            }
            4 -> {
                views.add(img1)
                views.add(img2)
                views.add(img3)
                views.add(img4)
            }
        }
        return views
    }


}