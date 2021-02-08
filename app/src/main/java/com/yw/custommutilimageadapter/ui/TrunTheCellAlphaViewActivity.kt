package com.yw.custommutilimageadapter.ui

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.utils.BitmapUtils
import com.yw.library.weight.ttcav.AlphaView
import kotlinx.android.synthetic.main.activity_custom_mutil_imag_view.*
import kotlinx.android.synthetic.main.activity_trun_the_cell_alpha_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TrunTheCellAlphaViewActivity
 * @Description:     半透明翻卡效果
 * @Author:         wei.yang
 * @CreateDate:     2021/2/7 11:44
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/7 11:44
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TrunTheCellAlphaViewActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trun_the_cell_alpha_view)
//        alphaView.setOnDayClickListener(object : AlphaView.OnDayClickListener {
//            override fun onDayClick(day: Int, eventX: Float, eventY: Float) {
//                Toast.makeText(
//                    this@TrunTheCellAlphaViewActivity,
//                    "第${day}天。坐标：x:${eventX},y:${eventY}",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//            }
//
//        })


        clipBitmapViewGroup.setAdapter(getDataViews(), 2f)
    }


    private fun getDataViews(): ArrayList<View> {
        val bitmap1 = BitmapFactory.decodeResource(resources,R.mipmap.big_girl)
        val bitmap2 = BitmapFactory.decodeResource(resources,R.mipmap.fengjing1)
        val list1 = BitmapUtils().splitBitmap(bitmap1,6,5)
        val list2 = BitmapUtils().splitBitmap(bitmap2,6,5)
        val views = ArrayList<View>()
        for (index in 0 until 30) {
            val view = LayoutInflater.from(this@TrunTheCellAlphaViewActivity)
                .inflate(R.layout.item_clip_bitmap_viewgroup, null)
            val image1 = view.findViewById<ImageView>(R.id.ivItemView)
            image1.setImageBitmap(list1[index])
            val image2 = view.findViewById<ImageView>(R.id.ivItemView2)
            image2.setImageBitmap(list2[index])
            image2.setOnClickListener {
                val animat = AnimatorSet()
                animat.playTogether(
                    ObjectAnimator.ofFloat(image2, "rotationY", 0f, 90f)//绕X轴翻转
                )
                animat.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        val animat = AnimatorSet()
                        image1.visibility = View.VISIBLE
                        animat.playTogether(
                            ObjectAnimator.ofFloat(image1, "scaleX", 0f, 1f),//X轴拉伸
                            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.25f, 1f)//透明度
                        )
                        animat.setDuration(500).start()

                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                })
                animat.setDuration(1000).start()
            }
            views.add(view)
        }
        return views
    }

}
