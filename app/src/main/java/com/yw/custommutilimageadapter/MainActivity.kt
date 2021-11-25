package com.yw.custommutilimageadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.MockView
import com.airbnb.lottie.LottieAnimationView
import com.yw.choicevideocover.ui.ChoiceVideoCoverActivity
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
        list.add(ClassBean("商品价格显示", GoodsPriceActivity::class.java.name))
        list.add(ClassBean("标签View", TagPaddingViewActivity::class.java.name))
        list.add(ClassBean("自定义Shape", CustomShapeDrawableActivity::class.java.name))
        list.add(ClassBean("属性动画", TransAnimitActivity::class.java.name))
        list.add(ClassBean("WebRTC测试", WebRTCActivity::class.java.name))
        list.add(ClassBean("改变TextView部分字体 颜色", TextViewPartTextColorActivity::class.java.name))
        list.add(ClassBean("可两个图标切换的ImageView", SwitchImageViewActivity::class.java.name))
        list.add(ClassBean("进度条动画", ProgressBarLoopBackgroundActivity::class.java.name))
        list.add(ClassBean("多重颜色渐变背景", MultiColorBackgroundActivity::class.java.name))
        list.add(ClassBean("Glide加载gif", GlideGifActivity::class.java.name))
        list.add(ClassBean("加载视频封面图", ChoiceVideoCoverActivity::class.java.name))

        val adapter = MainAdapter(this, list,
            BaseListAdapter.OnListItemClickListener<ClassBean> { data, position ->
                ActivityUtils.startActivity(this@MainActivity, data.className)
            })
        listView.adapter = adapter
        /**
         * 设置一个可拖动的View
         */
//        dragView.setDefaultViewResource(R.drawable.girl4)
        val view = LayoutInflater.from(this)
            .inflate(R.layout.drag_json_child_layout, null) as LottieAnimationView
        dragView.setAnimationJson(view)
        dragView.setOnClickListener {
            Toast.makeText(this@MainActivity, "可以", Toast.LENGTH_SHORT).show()
        }
    }


}