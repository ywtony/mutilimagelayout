package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_half_alpha_opacity.*
import kotlinx.android.synthetic.main.activity_tiktok_video_loadding_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TiktokVideoLoaddingViewActivity
 * @Description:     进度条View
 * @Author:         wei.yang
 * @CreateDate:     2021/3/20 13:36
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/20 13:36
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TiktokVideoLoaddingViewActivity  : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiktok_video_loadding_view)
        tiktokVideoLoaddingView.setTimePeriod(20)
    }
}