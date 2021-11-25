package com.yw.choicevideocover.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yw.choicevideocover.R;
import com.yw.choicevideocover.adapter.VideoCoverListAdapter;
import com.yw.choicevideocover.frame.TonyMediaMetadataRetriever;

import java.util.ArrayList;
import java.util.List;

import static com.yw.choicevideocover.frame.TonyMediaMetadataRetriever.VIDEO_ON_SEEK_TO_TAG;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.choicevideocover.frame
 * @ClassName: VideoCoverView
 * @Description: 获取视频封面图View
 * @Author: wei.yang
 * @CreateDate: 2021/11/23 13:10
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/11/23 13:10
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class VideoCoverView extends FrameLayout implements TonyMediaMetadataRetriever.OnVideoFrameListener, ThumbnailSelTimeView.OnScrollBorderListener, VideoCoverRecyclerView.OnGetPointerListener {
    private TonyMediaMetadataRetriever mediaData;
    private Context context;
    private TextView tvCancel;
    private TextView tvSubmit;
    private ImageView ivChoiceVideoCover;
    private VideoCoverRecyclerView recyclerViewCovert;
    private ThumbnailSelTimeView slideView;
    //    private VideoView videoView;
    private ImageView ivCoverImage;
    private float mSelStartTime = 0.5f;
    private VideoCoverListAdapter adapter;
    private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
    private String videoPath = null;

    public VideoCoverView(@NonNull Context context) {
        super(context);
        this.context = context;
        mediaData = new TonyMediaMetadataRetriever(context);
        mediaData.setOnVideoFrameListener(this);
        removeAllViews();
        initView();
    }

    public VideoCoverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mediaData = new TonyMediaMetadataRetriever(context);
        mediaData.setOnVideoFrameListener(this);
        removeAllViews();
        initView();
    }

    public VideoCoverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.view_video_cover_view, null);
        tvCancel = mView.findViewById(R.id.tvCancel);
        tvSubmit = mView.findViewById(R.id.tvSubmit);
        ivChoiceVideoCover = mView.findViewById(R.id.ivChoiceImage);
        slideView = mView.findViewById(R.id.thumbnailSelTimeView);
        slideView.setOnScrollBorderListener(this);
        ivCoverImage = mView.findViewById(R.id.ivCoverView);
//        videoView = mView.findViewById(R.id.videoView);
        recyclerViewCovert = mView.findViewById(R.id.recyclerView);
        recyclerViewCovert.setOnGetPointerListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }
        };
        recyclerViewCovert.setLayoutManager(linearLayoutManager);
        adapter = new VideoCoverListAdapter(context, bitmapList, (data, position) -> {
        });

        recyclerViewCovert.setAdapter(adapter);
        //设置监听事件
        tvCancel.setOnClickListener(v -> {
            if (callback != null) {
                callback.onCancel();
            }
        });
        ImageView ivTest = mView.findViewById(R.id.ivTest);
        tvSubmit.setOnClickListener(v -> {
            try {
                if (callback != null) {
                    long frameTime = (long) mSelStartTime * 1000 * 1000;
                    Bitmap bitmap = getBitmap(false);
                    ivTest.setImageBitmap(bitmap);
                    callback.onSubmit(bitmap, frameTime);
//                    mediaData.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        ivChoiceVideoCover.setOnClickListener(v -> {
            if (callback != null) {
                callback.onChoiceVideoCover();
            }
        });
        addView(mView);
    }

    private void initSetParam(String videoPath) {
//        this.videoPath = videoPath;
//        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
//        if (mediaData.getmVideoDuration() == 0 && mediaData.getmVideoWidth() > mediaData.getmVideoHeight()) {//本地视频横屏 0表示竖屏
//            layoutParams.width = 1120;
//            layoutParams.height = 630;
//        } else {
//            layoutParams.width = 630;
//            layoutParams.height = 1120;
//        }
//
//        videoView.setLayoutParams(layoutParams);
//        videoView.setVideoPath(videoPath);
//        videoView.start();
//        videoView.getDuration();
    }
    private int slideViewWidth = 0;//滑块的整体长度（也就是recyclerview的整体长度）
    private float mItemWidth = 0;
    /**
     * 设置视频路径并得到Bitmap
     *
     * @param videoPath
     */
    public void setVideoPath(String videoPath) {
        slideView.post(()->{
            slideViewWidth = slideView.getMeasuredWidth();
            mItemWidth = slideViewWidth/7;
            Log.e("空间宽度：",""+slideViewWidth+", "+mItemWidth);
            adapter.setIvWidth(mItemWidth);
            initSetParam(videoPath);
            mediaData.init(videoPath);
            mediaData.getAllFrames();
            getBitmap(true);
        });

    }


    /**
     * 视频中7帧封面图的回调
     *
     * @param bitmap
     */
    @Override
    public void onVideoFrame(Bitmap bitmap) {
        bitmapList.add(bitmap);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSeekToFrame(Object obj) {
//        videoView.seekTo((int) mSelStartTime * 1000);
//        videoView.start();
    }

    @Override
    public void OnScrollBorder(float left, float right) {
        float rectLeft = slideView.getRectLeft();
        mSelStartTime = (mediaData.getmVideoDuration() * rectLeft) / 1000;
        getBitmap(false);
    }

    @Override
    public void onScrollStateChange() {
        mediaData.removeMessages(VIDEO_ON_SEEK_TO_TAG);
        float rectLeft = slideView.getRectLeft();
        mSelStartTime = (mediaData.getmVideoDuration() * rectLeft) / 1000;






        Log.e("Atest", "onScrollStateChange: " + mSelStartTime);
//        videoView.seekTo((int) mSelStartTime);
//        Message msg = Message.obtain();
//        msg.what = VIDEO_ON_SEEK_TO_TAG;
//        msg.obj = mSelStartTime;
//        mediaData.sendMessage(msg);
        getBitmap(false);

    }

    private Bitmap getBitmap(boolean isFirstFrame) {
        long frameTime = (long) mSelStartTime * 1000 * 1000;
        if (isFirstFrame) {
            frameTime = 0;
        }
        Bitmap bitmap = mediaData.getFrameBitmap(frameTime);
        ivCoverImage.setImageBitmap(bitmap);
        return bitmap;
    }

    private VideoCoverViewCallback callback;

    public void setVideoCoverViewCallback(VideoCoverViewCallback callback) {
        this.callback = callback;
    }

    /**
     * 点击RecyclerView获取相对坐标点
     *
     * @param x
     */
    @Override
    public void onClickPointer(float x) {
        slideView.setCusRectF(x);
    }

    public interface VideoCoverViewCallback {
        /**
         * 当取消的时候执行
         */
        void onCancel();

        /**
         * 当提交的时候执行
         */
        void onSubmit(Bitmap frame, long frameTime);

        /**
         * 当从相册中选择封面图的时候执行
         */
        void onChoiceVideoCover();
    }
}
