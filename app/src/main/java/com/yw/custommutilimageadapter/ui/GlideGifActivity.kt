package com.yw.custommutilimageadapter.ui

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.adapter.BaseListAdapter
import com.yw.custommutilimageadapter.adapter.GidTestAdapter
import com.yw.custommutilimageadapter.utils.GlideUtils
import com.yw.custommutilimageadapter.utils.PxUtils
import kotlinx.android.synthetic.main.activity_glide_gif.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      GlideGifActivity
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/9/15 16:16
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/9/15 16:16
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class GlideGifActivity : FragmentActivity() {
    private var mAdapter: GidTestAdapter? = null
    var datas = ArrayList<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_gif)
//        ivGif.setOnClickListener {
//            GlideUtils.getInstance().loadNetGif(this@GlideGifActivity,"http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim",ivGif)
//            GlideUtils.getInstance().loadNetGif(this@GlideGifActivity,"http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX",ivGif1)
//            GlideUtils.getInstance().loadNetGif(this@GlideGifActivity,"http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim",ivGif2)
//        }
        initRecyclerView()
        refreshData()
    }

//    private fun loadGif() {
//        Glide.with(this@GlideGifActivity).asGif()
//            .load("http://qiniu.vdou.com/Fg3JGMe8uJRIKzDIWydkmEBeMf43")
//            .listener(object : RequestListener<GifDrawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<GifDrawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    return true;
//                }
//
//                override fun onResourceReady(
//                    resource: GifDrawable?,
//                    model: Any?,
//                    target: Target<GifDrawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//
//
//                    return true;
//                }
//
//            }).into(ivGif)
//    }

    fun initRecyclerView() {
        recyclerView!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.setHasFixedSize(true)

        recyclerView!!.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val left = PxUtils.dip2px(view.context, 16f)
                val right = PxUtils.dip2px(view.context, 16f)
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = left
                    outRect.right = 0
                } /*else if (parent.getChildLayoutPosition(view) == mAdapter?.getItemViewType(HREA).size - 1) {
                    outRect.right = right
                    outRect.left = left
                }*/ else {
                    outRect.left = left
                    outRect.right = 0
                }
            }
        })



        mAdapter = GidTestAdapter(this@GlideGifActivity,datas,object:BaseListAdapter.OnListItemClickListener<String>{
            override fun onItemClick(data: String?, position: Int) {
            }

        })
        recyclerView!!.adapter = mAdapter


    }

    /**
     * 刷新数据
     */
    private fun refreshData(){
        initData();
        mAdapter?.notifyDataSetChanged()
    }
    private fun initData(){
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
        datas.add("")
        datas.add("http://qiniu.vdou.com/FvagDqB_cAOvtZvGVEuapWM59vzX")
        datas.add("http://qiniu.vdou.com/Fjzj4603Y0viIDycYzHQPDqbFtny?imageView2%2F3%2Fw%2F150%2Fh%2F150%2Fq%2F75%7Cimageslim")
    }
}