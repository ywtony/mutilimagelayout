package com.yw.library.weight.downloadview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.yw.library.R
import com.yw.library.weight.downloadview.DownloadUtil.OnDownloadListener

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.downloadview
 * @ClassName:      DownloadProgressView
 * @Description:     关注页面下载进度条View
 * @Author:         wei.yang
 * @CreateDate:     2021/4/15 15:56
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/4/15 15:56
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class DownloadProgressView(context: Context?, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    private var progressViewBar: ProgressBar
    private var downloadUtil: DownloadUtil? = null
    private var tvDownloadText: TextView

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.download_progress_view, null)
        view.background.alpha = 102
        progressViewBar = view.findViewById(R.id.progressViewBar)
        tvDownloadText = view.findViewById(R.id.tvDownloadText)
        removeAllViews()
        addView(view)
        downloadUtil = DownloadUtil()
//        addOnAttachStateChangeListener(object:OnAttachStateChangeListener{
//            override fun onViewDetachedFromWindow(v: View?) {
//
//            }
//
//            override fun onViewAttachedToWindow(v: View?) {
//                //想要中断下载把这个开启
////                downloadUtil?.isDownloadEnd = true
//            }
//
//        })

    }

    /**
     * 开始下载
     */
    fun download(downloadUrl: String) {
        if (!downloadUtil?.isDownloading!!) {
            tvDownloadText.text = "视频下载中"
            visibility = View.VISIBLE
            downloadUtil?.isDownloadEnd = false
            downloadUtil?.download(
                context,
                downloadUrl,
                "/download/",
                System.currentTimeMillis().toString() + ".mp4",
                object : OnDownloadListener {
                    override fun onDownloadSuccess(filePath:String) {
                        tvDownloadText.text = "下载成功"
                        Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show()
                        visibility = View.GONE
                    }

                    override fun onDownloading(progress: Int) {
                        progressViewBar.progress = progress
                    }

                    override fun onDownloadFailed() {
                        visibility = View.GONE
                        tvDownloadText.text = "下载失败"
                        Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            Toast.makeText(context, "正在下载视频文件，请耐心等待！", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 是否有任务正在下载
     */
    fun isDownloading(): Boolean {
        return downloadUtil?.isDownloading!!
    }
}