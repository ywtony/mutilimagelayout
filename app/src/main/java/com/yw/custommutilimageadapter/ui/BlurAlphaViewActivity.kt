package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_blur_alpha.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      BlurAlphaView
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/1/27 20:02
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/27 20:02
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class BlurAlphaViewActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_alpha)
        blurAlphaView.startBlur(container, 200, 200, R.color.red, 30)
    }
}