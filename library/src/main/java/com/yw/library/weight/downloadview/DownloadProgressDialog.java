package com.yw.library.weight.downloadview;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yw.library.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.library.weight.downloadview
 * @ClassName: LoadingDialog
 * @Description: 下载进度框
 * @Author: wei.yang
 * @CreateDate: 2021/4/15 9:38
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/4/15 9:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DownloadProgressDialog extends Dialog {
    private String downloadUrl;
    private RoundProgressBar progressBar;
    private LinearLayout linearDownloadTopBg;
    private TextView tvCancelDownload;
    private LinearLayout linerContainer;
    private DownloadUtil downloadUtil = null;
    public DownloadProgressDialog(Context context, String downloadUrl) {
        super(context, R.style.download_progress_dialog);
        this.downloadUrl = downloadUrl;
        downloadUtil = new DownloadUtil();
        initView();
    }

    @Override
    public void show() {
        super.show();
        download();//开始下载
        stopTimer();
        startTimer();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                downloadUtil.setDownloadEnd(true);
                if (DownloadProgressDialog.this.isShowing())
                    DownloadProgressDialog.this.dismiss();
                break;
        }
        return true;
    }

    private void initView() {
        setContentView(R.layout.download_progress_dialog);
        progressBar = findViewById(R.id.progress);
        linearDownloadTopBg = findViewById(R.id.linearDownloadTopBg);
        tvCancelDownload = findViewById(R.id.tvCancelDownload);
        tvCancelDownload.getBackground().setAlpha(153);
        linearDownloadTopBg.getBackground().setAlpha(153);
        linerContainer = findViewById(R.id.linerContainer);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.6f;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(true);
        setCancelable(false);

        tvCancelDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭窗口
                dismiss();
                //取消下载任务
                downloadUtil.setDownloadEnd(true);
            }
        });
    }

    //定时器Timer
    TimerTask timeTask = null;
    Timer mTimer = null;

    /**
     * 开启定时器
     */
    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (timeTask == null) {
            timeTask = new TimerTask() {
                @Override
                public void run() {
                    MainHandler.getInstance().getMainHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            tvCancelDownload.setVisibility(View.VISIBLE);
                        }
                    });

                }
            };
        }
        if (mTimer != null && timeTask != null) {
            mTimer.schedule(timeTask, 5 * 1000);
        }
    }

    /**
     * 停止定时器
     */
    private void stopTimer() {
        if (timeTask != null) {
            timeTask.cancel();
            timeTask = null;
        }
        if (mTimer != null) {
            mTimer.purge();
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void download() {
        downloadUtil.setDownloadEnd(false);
        downloadUtil.download(getContext(), downloadUrl, "/download/", System.currentTimeMillis() + ".mp4", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String filePath) {
                Toast.makeText(getContext(), "下载成功", Toast.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onDownloading(int progress) {
                progressBar.setProgress(progress);
            }

            @Override
            public void onDownloadFailed() {
                dismiss();
                Toast.makeText(getContext(), "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}