package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_progress_loop_background.btnStart
import kotlinx.android.synthetic.main.activity_task_progress_bar.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TaskProgressBarActivity
 * @Description:     砍价进度条
 * @Author:         wei.yang
 * @CreateDate:     2021/3/1 17:45
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/1 17:45
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TaskProgressBarActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_progress_bar)
//        taskBar.requestProgressBar(0.9f)
        btnStart.setOnClickListener {
            taskBar.requestProgressBar(1f)
        }
    }
}