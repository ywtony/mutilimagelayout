package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_web_rtc.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      WebRTCActivity
 * @Description:    测试WebRTC获取设备信息
 * @Author:         wei.yang
 * @CreateDate:     2021/6/9 16:20
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/9 16:20
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class WebRTCActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_rtc)
        initViews()
    }

    private fun initViews() {
        webView.loadUrl("https://172.22.33.25:8888/index.html")
    }
}