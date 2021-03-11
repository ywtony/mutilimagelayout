package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.zxing.Result
import com.king.zxing.CaptureActivity
import com.yw.custommutilimageadapter.R

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      ZXingDemoActivity
 * @Description:     zxing识别二维码
 * @Author:         wei.yang
 * @CreateDate:     2021/3/5 16:13
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/5 16:13
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ZXingDemoActivity : CaptureActivity() {

    override fun getViewfinderViewId(): Int {
        return R.layout.activity_zxing_demo
    }

    override fun onScanResultCallback(result: Result?): Boolean {
        return super.onScanResultCallback(result)
    }
}