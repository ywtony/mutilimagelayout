package com.yw.custommutilimageadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yw.custommutilimageadapter.adapter.BaseListAdapter
import com.yw.custommutilimageadapter.adapter.MainAdapter
import com.yw.custommutilimageadapter.bean.ClassBean
import com.yw.custommutilimageadapter.ui.*
import com.yw.custommutilimageadapter.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @description 测试多图片，多样式适配器
 * @date: 2020/11/6 15:38
 * @author: wei.yang
 * @param
 * @return
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val list = ArrayList<ClassBean>()
        list.add(ClassBean("不规则布局多图展示", CustomMutilImageViewActivity::class.java.name))
        list.add(ClassBean("遮掩布局展示", FoldCardViewLayoutActivity::class.java.name))
        list.add(ClassBean("自定义ScrollView", MyScrollViewActivity::class.java.name))
        list.add(ClassBean("自定义EditText", EditTextActivity::class.java.name))
        list.add(ClassBean("模糊半透明效果", BlurAlphaViewActivity::class.java.name))
        val adapter = MainAdapter(this, list,
            BaseListAdapter.OnListItemClickListener<ClassBean> { data, position ->
                ActivityUtils.startActivity(this@MainActivity, data.className)
            })
        listView.adapter = adapter
    }


}