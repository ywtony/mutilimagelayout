package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.utils.PxUtils
import com.yw.library.weight.cshape.CustomShapeDrawable
import com.yw.library.weight.cshape.ShapeUtils
import kotlinx.android.synthetic.main.activity_custom_shape_drawable.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      CustomShapeDrawableActivity
 * @Description:    测试自定义的Drawable：由于直接在drawable文件夹中定义xml复用性太差，所以自定义一个
 * @Author:         wei.yang
 * @CreateDate:     2021/5/13 16:06
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/13 16:06
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class CustomShapeDrawableActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_shape_drawable)
        ShapeUtils.setBackground(
            tvShape1, R.color.color_CF111A,
            2f,
            R.color.color_CF111A,
            12f
        )
        ShapeUtils.setBackground(
            tvShape2, R.color.color_CF111A,
            0.5f,
            R.color.white,
            6f
        )
    }
}