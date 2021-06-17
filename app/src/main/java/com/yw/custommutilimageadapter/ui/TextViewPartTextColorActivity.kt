package com.yw.custommutilimageadapter.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.utils.TextViewPartTextColorUtil
import kotlinx.android.synthetic.main.activity_textview_part_textcolor.*
import java.util.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TextViewPartTextColorActivity
 * @Description:     改变TextView部分字体颜色
 * @Author:         wei.yang
 * @CreateDate:     2021/6/17 9:56
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/17 9:56
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TextViewPartTextColorActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview_part_textcolor)
        var allString =  "你好啊，我是杨洛峋小宝宝你好啊，我是杨洛峋小宝宝你好啊，我是杨洛峋小宝宝你好啊，我是杨洛峋小宝宝你好啊，我是杨洛峋小宝宝"
        var partString = "洛峋"
        tvPartTextColor.text = allString
        TextViewPartTextColorUtil.showPartTextColor(tvPartTextColor,getColor(R.color.color_CF111A),allString,partString)

//        TextViewPartTextColorUtil.showPartTextColors(tvPartTextColor, arrayOf(getColor(R.color.color_CF111A),getColor(R.color.color_FFF35F),getColor(R.color.color_FF7201))
//        ,allString, arrayOf("好","杨洛峋","宝宝"))

    }
}