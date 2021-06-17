package com.yw.library

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library
 * @ClassName:      WebRTCWebView
 * @Description:    用于做WebRTC测试的WebView
 * @Author:         wei.yang
 * @CreateDate:     2021/6/9 16:23
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/9 16:23
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class WebRTCWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {
    init {
        settings.useWideViewPort = true //自适应
        settings.loadWithOverviewMode = true
        settings.displayZoomControls = false // 设定缩放控件隐藏
        isVerticalScrollBarEnabled = false // 垂直不显示
        settings.saveFormData = true //是否保存表单
        settings.builtInZoomControls = true //是否支持缩放
        webViewClient = MyWebViewClient()
        webChromeClient = object : WebChromeClient() {}
        settings.defaultTextEncodingName = "UTF-8"
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        //以下两行代码必须加上，不然js无法调用后台接口
        //以下两行代码必须加上，不然js无法调用后台接口
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCacheEnabled(false)
    }

    internal class MyWebViewClient : WebViewClient() {
        override fun onReceivedError(
            view: WebView,
            errorCode: Int,
            description: String,
            failingUrl: String
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            view.loadUrl("about:blank")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        loadUrl("about:blank")
        return super.onKeyDown(keyCode, event)
    }
}