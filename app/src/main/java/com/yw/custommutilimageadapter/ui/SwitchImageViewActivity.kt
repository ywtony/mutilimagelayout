package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.SwitchImageView
import kotlinx.android.synthetic.main.activity_switch_image_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      SwitchImageViewActivity
 * @Description:    可选中和取消选中的ImageView
 * @Author:         wei.yang
 * @CreateDate:     2021/6/25 17:10
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/25 17:10
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SwitchImageViewActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_image_view)
        switchImageView.setSwitchImageViewClickListener(object :
            SwitchImageView.SwitchImageViewClickListener {
            override fun onSwitchClick(isCheck: Boolean) {
                Toast.makeText(this@SwitchImageViewActivity, isCheck.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}