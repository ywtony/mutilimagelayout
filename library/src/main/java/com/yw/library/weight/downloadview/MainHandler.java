package com.yw.library.weight.downloadview;

import android.os.Handler;
import android.os.Looper;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.library.weight.downloadview
 * @ClassName: MainHandler
 * @Description: 创建一个主线程Handler
 * @Author: wei.yang
 * @CreateDate: 2021/4/14 11:23
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/4/14 11:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainHandler extends Handler {
    private Handler handler;

    private MainHandler() {
    }

    private static MainHandler instance;

    public synchronized static MainHandler getInstance() {
        if (instance == null) {
            instance = new MainHandler();
        }
        return instance;
    }

    /**
     * 创建一个主线程的Handler
     *
     * @return
     */
    public Handler getMainHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }


}
