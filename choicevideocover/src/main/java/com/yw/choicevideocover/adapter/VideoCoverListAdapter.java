package com.yw.choicevideocover.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yw.choicevideocover.R;

import org.byteam.superadapter.SuperViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.choicevideocover.adapter
 * @ClassName: VideoCoverListAdapter
 * @Description: 封面图列表数据源集合
 * @Author: wei.yang
 * @CreateDate: 2021/11/23 13:59
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/11/23 13:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class VideoCoverListAdapter extends BaseListAdapter<Bitmap> {
    private float ivWidth;

    public VideoCoverListAdapter(Context context, List<Bitmap> mData, OnListItemClickListener<Bitmap> listener) {
        super(context, mData, R.layout.item_video_cover_list, listener);
    }

    @Override
    public void onBindData(@NotNull SuperViewHolder holder, int viewType, int layoutPosition, @NotNull Bitmap data) {

        ImageView ivCover = holder.findViewById(R.id.itemIvCover);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) ivWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        ivCover.setLayoutParams(params);
        ivCover.setImageBitmap(data);
    }

    public void setIvWidth(float ivWidth) {
        this.ivWidth = ivWidth;
    }

}
