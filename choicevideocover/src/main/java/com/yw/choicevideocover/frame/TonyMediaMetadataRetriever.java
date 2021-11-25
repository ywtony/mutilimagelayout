package com.yw.choicevideocover.frame;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.choicevideocover.frame
 * @ClassName: TonyMediaMetadataRetriever
 * @Description: 获取视频文件的基本属性
 * @Author: wei.yang
 * @CreateDate: 2021/11/23 11:23
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/11/23 11:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class TonyMediaMetadataRetriever extends Handler implements Runnable {
    public static final int VIDEO_FRAME_TAG = 100;
    public static final int VIDEO_ON_SEEK_TO_TAG = 101;
    private int mVideoHeight, mVideoWidth, mVideoDuration;
    private MediaMetadataRetriever mediaMetadata = null;
    private Context context;
    private String mVideoPath = null;
    private String mVideoRotation;
    private Bitmap bitmap;

    public TonyMediaMetadataRetriever(Context context) {
        this.context = context;
        mediaMetadata = new MediaMetadataRetriever();
    }

    public void init(String mVideoPath) {
        this.mVideoPath = mVideoPath;
        mediaMetadata.setDataSource(context, Uri.parse(mVideoPath));
        mVideoRotation = mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
        mVideoWidth = Integer.parseInt(mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
        mVideoHeight = Integer.parseInt(mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
        mVideoDuration = Integer.parseInt(mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
    }

    public int getmVideoHeight() {
        return mVideoHeight;
    }

    public int getmVideoWidth() {
        return mVideoWidth;
    }


    public int getmVideoDuration() {
        return mVideoDuration;
    }

    public void setmVideoDuration(int mVideoDuration) {
        this.mVideoDuration = mVideoDuration;
    }


    public String getmVideoRotation() {
        return mVideoRotation;
    }

    /**
     * 获取指定的某一帧的图像
     *
     * @param frameTime 平均时长
     * @param index     间隔图片数
     * @return 指定位置的Bitmap
     */
    private Bitmap getFrameBitmap(long frameTime, int index) {
        Log.e("onScrollStateChange2:",""+frameTime* index);
        return mediaMetadata.getFrameAtTime(frameTime * index, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
    }

    /**
     * 获取指定时间的视频帧
     * @param frameTime
     * @return
     */
    public Bitmap getFrameBitmap(long frameTime) {
        return mediaMetadata.getFrameAtTime(frameTime, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
    }

    public void getAllFrames() {
        Executors.newSingleThreadExecutor().execute(this);
    }

    @Override
    public void run() {
        int frame = 7;
        int frameTime = mVideoDuration / frame * 1000;
        for (int i = 0; i < frame; i++) {
            Bitmap bitmap = getFrameBitmap(frameTime, i);
            Message msg = Message.obtain();
            msg.what = VIDEO_FRAME_TAG;
            msg.obj = bitmap;
            sendMessage(msg);
        }
//        release();
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (VIDEO_FRAME_TAG == msg.what) {
            if (onVideoFrameListener != null) {
                onVideoFrameListener.onVideoFrame((Bitmap) msg.obj);
            }
        } else if (VIDEO_ON_SEEK_TO_TAG == msg.what) {
            if (onVideoFrameListener != null) {
                onVideoFrameListener.onSeekToFrame(msg.obj);
                sendEmptyMessageDelayed(VIDEO_ON_SEEK_TO_TAG, 1000);
            }
        }

    }

    /**
     * 销毁视频提取器
     */
    public void release() {
        if (mediaMetadata != null) {
            mediaMetadata.release();
        }
    }

    private OnVideoFrameListener onVideoFrameListener;

    public void setOnVideoFrameListener(OnVideoFrameListener onVideoFrameListener) {
        this.onVideoFrameListener = onVideoFrameListener;
    }

    public interface OnVideoFrameListener {
        void onVideoFrame(Bitmap bitmap);

        void onSeekToFrame(Object obj);
    }
}
