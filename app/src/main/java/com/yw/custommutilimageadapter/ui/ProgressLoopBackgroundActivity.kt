package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_progress_loop_background.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      ProgressLoopBackgroundActivity
 * @Description:     循环背景动画
 * @Author:         wei.yang
 * @CreateDate:     2021/2/5 14:52
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/2/5 14:52
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ProgressLoopBackgroundActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_loop_background)

        btnStart.setOnClickListener {
            progressLoopBackgroundView.startAnimation()
        }
    }
}