package com.yw.custommutilimageadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.MockView
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
        list.add(ClassBean("任意形状及方向的折叠View", FoldLayoutViewActivity::class.java.name))
        list.add(ClassBean("遮掩布局展示", FoldCardViewLayoutActivity::class.java.name))
        list.add(ClassBean("自定义ScrollView", MyScrollViewActivity::class.java.name))
        list.add(ClassBean("自定义EditText", EditTextActivity::class.java.name))
        list.add(ClassBean("模糊半透明效果", BlurAlphaViewActivity::class.java.name))
        list.add(ClassBean("自定义弧形背景", ArcActivity::class.java.name))
        list.add(ClassBean("自定义背景动画", ProgressLoopBackgroundActivity::class.java.name))
        list.add(ClassBean("自定义翻卡效果", TrunTheCellAlphaViewActivity::class.java.name))
        list.add(ClassBean("砍价进度条", TaskProgressBarActivity::class.java.name))
        list.add(ClassBean("圆形进度条", CircleProgressActivity::class.java.name))
        list.add(ClassBean("二维码识别", ZXingDemoActivity::class.java.name))
        list.add(ClassBean("快慢速TabView", SpeedBarViewActivity::class.java.name))
        list.add(ClassBean("半透明遮罩", HalfAlphaOpacityActivity::class.java.name))
        list.add(ClassBean("防抖音视频进度加载进度条", TiktokVideoLoaddingViewActivity::class.java.name))
        list.add(ClassBean("下载视频文件进度", DownLoadViewActivity::class.java.name))


        val adapter = MainAdapter(this, list,
            BaseListAdapter.OnListItemClickListener<ClassBean> { data, position ->
                ActivityUtils.startActivity(this@MainActivity, data.className)
            })
        listView.adapter = adapter
        /**
         * 设置一个可拖动的View
         */
        dragView.setPosition(100,100,100,100)
        dragView.setDefaultViewResource(R.drawable.girl4)
        dragView.setOnClickListener {
            Toast.makeText(this@MainActivity, "可以", Toast.LENGTH_SHORT).show()
        }
    }


}