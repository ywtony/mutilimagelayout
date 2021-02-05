package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_arc_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      ArcActivity
 * @Description:     自定义弧形背景测试
 * @Author:         wei.yang
 * @CreateDate:     2021/1/29 14:07
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/29 14:07
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ArcActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_view)
    }
}