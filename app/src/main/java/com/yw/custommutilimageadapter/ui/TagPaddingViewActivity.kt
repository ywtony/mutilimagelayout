package com.yw.custommutilimageadapter.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.library.weight.TagPaddingView
import kotlinx.android.synthetic.main.activity_tag_padding_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      TagPaddingViewActivity
 * @Description:     带标签的View
 * @Author:         wei.yang
 * @CreateDate:     2021/5/13 10:28
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/5/13 10:28
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TagPaddingViewActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_padding_view)
        val tagConfig = TagPaddingView.TagViewConfig("社团购",R.color.color_ff6510,20f,R.drawable.bg_tag,23)
        val valueConfig = TagPaddingView.ValueViewConfig("2020年新款七分袖娃娃领雪纺衬衫女夏装…",R.color.t333,26f)
        tagPaddingView.setPaddingViewConfig(tagConfig,valueConfig)
    }
}