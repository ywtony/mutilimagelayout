package com.yw.custommutilimageadapter.ui

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.yw.custommutilimageadapter.R
import com.yw.custommutilimageadapter.utils.PermisstionUtil
import com.yw.library.weight.downloadview.DownloadProgressDialog
import com.yw.library.weight.downloadview.DownloadUtil
import kotlinx.android.synthetic.main.activity_download_view.*


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      DownLoadViewActivity
 * @Description:    下载视频文件
 * @Author:         wei.yang
 * @CreateDate:     2021/4/14 11:00
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/4/14 11:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class DownLoadViewActivity : FragmentActivity() {


    private var url: String = "https://qiniu.vdou.com/lmIrVyJhJQl8y-h84sQcE3TPuq5-"
    private var fileName: String = System.currentTimeMillis().toString() + ".mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_view)
        btnDownload.setOnClickListener {
            DownloadProgressDialog(this@DownLoadViewActivity, url).show()
        }
        btnDownload1.setOnClickListener {
            downloadProgressView.download(url)
        }
        PermisstionUtil.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )
    }



}