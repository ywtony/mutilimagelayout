package com.yw.choicevideocover.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.choicevideocover.widget
 * @ClassName: VideoCoverRecyclerView
 * @Description: java类作用描述
 * @Author: wei.yang
 * @CreateDate: 2021/11/23 16:47
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/11/23 16:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class VideoCoverRecyclerView extends RecyclerView {
    public VideoCoverRecyclerView(@NonNull Context context) {
        super(context);
    }

    public VideoCoverRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoCoverRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //相对于View自身的坐标
    private float mX = 0.0f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mX = event.getX();
                if (onGetPointerListener != null) {
                    onGetPointerListener.onClickPointer(mX);
                }
                break;
        }
        return true;

    }

    public OnGetPointerListener onGetPointerListener;

    public void setOnGetPointerListener(OnGetPointerListener onGetPointerListener) {
        this.onGetPointerListener = onGetPointerListener;
    }

    public interface OnGetPointerListener {
        void onClickPointer(float x);
    }
}
