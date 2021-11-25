package com.yw.choicevideocover.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.yw.choicevideocover.R;
import com.yw.choicevideocover.widget.VideoCoverView;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.choicevideocover.ui
 * @ClassName: ChoiceVideoCoverActivity
 * @Description: 选择视频图片封面图
 * @Author: wei.yang
 * @CreateDate: 2021/11/23 11:44
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/11/23 11:44
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ChoiceVideoCoverActivity extends BaseActivity implements VideoCoverView.VideoCoverViewCallback {
    private VideoCoverView videoCoverView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_video_cover);
        videoCoverView = findViewById(R.id.videoCoverView);
        videoCoverView.setVideoCoverViewCallback(this);
        findViewById(R.id.btnChoiceVideo).setOnClickListener(v->{
            choiceVideo();
        });
    }

    @Override
    public void videoPathCallback(String videoPath) {
        videoCoverView.setVideoPath(videoPath);
    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    public void onSubmit(Bitmap frame,long frameTime) {
//        Intent intent = new Intent();
//        intent.setClass()
//        setResult();
//        finish();
    }

    @Override
    public void onChoiceVideoCover() {
        //去打开相册选择图片
    }
}
