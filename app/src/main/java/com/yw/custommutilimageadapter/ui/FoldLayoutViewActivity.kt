package com.yw.custommutilimageadapter.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.adapter.FoldAdapter
import com.yw.custommutilimageadapter.bean.FoldBean
import com.yw.custommutilimageadapter.utils.PxUtils
import com.yw.library.weight.FoldLayoutView
import kotlinx.android.synthetic.main.activity_fold_layout_view.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      FlodLayoutViewActivity
 * @Description:    折叠View样例测试
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 17:22
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 17:22
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FoldLayoutViewActivity : Activity() {
    private var adapter:FoldAdapter?=null
    private var datas = ArrayList<FoldBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fold_layout_view)
        initViews()
    }

    private fun initViews() {
        val imgs = ArrayList<FoldBean>()
        imgs.add(FoldBean(type=1,url="http://qiniu.vdou.com/FpWxR8UnXZ5qY0TpEYx8XpIBaRM_?imageView2%2F3%2Fw%2F720%2Fh%2F720%2Fq%2F75%7Cimageslim"))
        imgs.add(FoldBean(type=1,url="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%8A%A8%E6%BC%AB%E5%B0%8F%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=3539500417,1619849463&os=401278371,1481743603&simid=4160319609,709497860&pn=3&rn=1&di=176550&ln=1408&fr=&fmq=1617278101578_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fc-ssl.duitang.com%252Fuploads%252Fitem%252F201902%252F01%252F20190201185109_hnqya.jpeg%26refer%3Dhttp%253A%252F%252Fc-ssl.duitang.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619870102%26t%3D1e920dd9f41ede904b54127db641f742&rpstart=0&rpnum=0&adpicid=0&force=undefined"))
        imgs.add(FoldBean(type=1,url="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%8A%A8%E6%BC%AB%E5%B0%8F%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=3385534040,3476762019&os=1254224123,399556080&simid=0,0&pn=5&rn=1&di=138050&ln=1408&fr=&fmq=1617278101578_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fpics7.baidu.com%2Ffeed%2F63d0f703918fa0ec086bb50efec22ae83c6ddbe5.jpeg%3Ftoken%3Da45692624b839e67901b248fa1cf329f&rpstart=0&rpnum=0&adpicid=0&force=undefined"))
        imgs.add(FoldBean(type=1,url="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%8A%A8%E6%BC%AB%E5%B0%8F%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=3781540395,1521127289&os=3151995526,2877157287&simid=3341513825,124413272&pn=7&rn=1&di=27060&ln=1408&fr=&fmq=1617278101578_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimage.biaobaiju.com%252Fuploads%252F20190703%252F16%252F1562143473-DsMNEvhXZA.jpg%26refer%3Dhttp%253A%252F%252Fimage.biaobaiju.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619870102%26t%3D2ab425a8f57c0238567cc823e4a1abea&rpstart=0&rpnum=0&adpicid=0&force=undefined"))
        imgs.add(FoldBean(type=1,url="https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%8A%A8%E6%BC%AB%E5%B0%8F%E7%BE%8E%E5%A5%B3&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=2935379069,4075793025&os=3272012675,307230964&simid=4125475390,674420143&pn=38&rn=1&di=105820&ln=1408&fr=&fmq=1617278101578_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fpic1.win4000.com%252Fwallpaper%252F6%252F518c67aaaa0e5.jpg%26refer%3Dhttp%253A%252F%252Fpic1.win4000.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619870103%26t%3D6681ade5702465df88dec2dfe1494a5f&rpstart=0&rpnum=0&adpicid=0&force=undefined"))
        imgs.add(FoldBean(type=2,resId = R.drawable.girl1))
        adapter = FoldAdapter(this@FoldLayoutViewActivity,imgs)
//        foldView.setAdapter(getDataViews())
        foldView.setAdapter(adapter!!)
        foldView.setCurrentSelectIndex(datas.size)
        foldView.setOnItemClickListener(object: FoldLayoutView.OnItemClickListener{
            override fun onItemClick(index: Int) {
                Toast.makeText(this@FoldLayoutViewActivity, "哈哈哈：$index",Toast.LENGTH_SHORT).show()
            }

        })
        foldViewH.setAdapter(adapter!!)
        foldViewH.setCurrentSelectIndex(datas.size)
        foldViewH.setOnItemClickListener(object: FoldLayoutView.OnItemClickListener{
            override fun onItemClick(index: Int) {
                Toast.makeText(this@FoldLayoutViewActivity, "哈哈哈：$index",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getDataViews(): ArrayList<View> {
        val views = ArrayList<View>()
        val img1 = ImageView(this)
        img1.setImageResource(R.drawable.girl1)
        img1.scaleType = ImageView.ScaleType.CENTER_CROP
        val img2 = ImageView(this)
        img2.setImageResource(R.drawable.girl2)
        img2.scaleType = ImageView.ScaleType.CENTER_CROP
        val img3 = ImageView(this)
        img3.setImageResource(R.drawable.girl3)
        img3.scaleType = ImageView.ScaleType.CENTER_CROP
        val img4 = ImageView(this)
        img4.setImageResource(R.drawable.girl4)
        img4.scaleType = ImageView.ScaleType.CENTER_CROP
        setLayoutParams(img1)
        setLayoutParams(img2)
        setLayoutParams(img3)
        setLayoutParams(img4)
        views.add(img1)
        views.add(img2)
        views.add(img3)
        views.add(img4)
        views.add(img1)
        views.add(img2)
        views.add(img3)
        views.add(img4)


        return views
    }

    private fun setLayoutParams(ivImage: ImageView) {
        val params = ViewGroup.LayoutParams(
            PxUtils.dp2px(this@FoldLayoutViewActivity, 50f),
            PxUtils.dp2px(this@FoldLayoutViewActivity, 50f)
        )
        ivImage.layoutParams = params
    }
}