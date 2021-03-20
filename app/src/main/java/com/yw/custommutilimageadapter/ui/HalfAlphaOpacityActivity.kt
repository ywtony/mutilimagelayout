package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.SpeedBarView
import kotlinx.android.synthetic.main.activity_half_alpha_opacity.*
import kotlinx.android.synthetic.main.activity_speed_bar_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      HalfAlphaOpacityActivity
 * @Description:     测试一下黑色透明遮罩
 * @Author:         wei.yang
 * @CreateDate:     2021/3/13 10:15
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/13 10:15
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class HalfAlphaOpacityActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_half_alpha_opacity)
        linear.background.alpha = 102

    }
}