package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_circle_progress.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      CircleProgressActivity
 * @Description:     圆形进度条
 * @Author:         wei.yang
 * @CreateDate:     2021/3/4 13:36
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/4 13:36
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CircleProgressActivity  : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_progress)
        tasks_view.setProgress(80)
    }
}