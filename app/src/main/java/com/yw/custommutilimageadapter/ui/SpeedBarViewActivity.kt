package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.SpeedBarView
import kotlinx.android.synthetic.main.activity_progress_loop_background.*
import kotlinx.android.synthetic.main.activity_progress_loop_background.btnStart
import kotlinx.android.synthetic.main.activity_speed_bar_view.*
import kotlinx.android.synthetic.main.activity_task_progress_bar.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      SpeedBarViewActivity
 * @Description:     快慢速View插件
 * @Author:         wei.yang
 * @CreateDate:     2021/3/11 15:34
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/11 15:34
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SpeedBarViewActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speed_bar_view)
        speedBarView.setSpeedCallback(object : SpeedBarView.SpeedCallback {
            override fun onSpeedItemClick(position: Int) {
                when (position) {
                    1 -> {
                        Toast.makeText(this@SpeedBarViewActivity, "慢速", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(this@SpeedBarViewActivity, "标准", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        Toast.makeText(this@SpeedBarViewActivity, "快速", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }
}