package com.yw.custommutilimageadapter.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.utils.PxUtils
import kotlinx.android.synthetic.main.activity_trans_anima_layout.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TransAnimitActivity
 * @Description:     属性动画测试
 * @Author:         wei.yang
 * @CreateDate:     2021/6/9 11:27
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/9 11:27
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TransAnimitActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans_anima_layout)
        initViews()
    }

    private fun initViews() {
        tvStart.setOnClickListener {
            tvNetworkTips.startAnimation()
        }

    }


}