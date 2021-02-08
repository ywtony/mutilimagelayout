package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.ttcav.AlphaView
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
        alphaView.setOnDayClickListener(object : AlphaView.OnDayClickListener {
            override fun onDayClick(day: Int, eventX: Float, eventY: Float) {
                Toast.makeText(
                    this@TrunTheCellAlphaViewActivity,
                    "第${day}天。坐标：x:${eventX},y:${eventY}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        })
    }
}
