package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      MultiColorBackgroundActivity
 * @Description:    多重颜色渐变背景
 * @Author:         wei.yang
 * @CreateDate:     2021/8/25 15:27
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/8/25 15:27
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class MultiColorBackgroundActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_color_background)
    }
}