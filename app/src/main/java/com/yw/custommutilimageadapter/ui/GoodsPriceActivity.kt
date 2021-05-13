package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_goods_price.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      GoodsPriceActivity
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/5/11 19:52
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/11 19:52
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class GoodsPriceActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_price)
        goodsPrice.setPriceSource("22.56")
    }
}