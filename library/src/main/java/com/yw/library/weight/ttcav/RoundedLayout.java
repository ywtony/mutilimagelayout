package com.yw.library.weight.ttcav;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.yw.library.R;


/**
 * 圆角控件.
 * Created by qiaofc on 2017/7/12 0012.
 */
public class RoundedLayout extends FrameLayout {
    private static final int DEFAULT_RADIUS = 12;
    private float mRadius = DEFAULT_RADIUS;

    public RoundedLayout(Context context) {
        this(context, null, 0);
    }

    public RoundedLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.roundedView);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.roundedView_rounded_radius, DEFAULT_RADIUS);
        typedArray.recycle();
        setWillNotDraw(false);//如果你继承的是ViewGroup,注意此行,否则draw方法是不会回调的;
    }

    public void setRoundLayoutRadius(float radius) {
        this.mRadius = radius;
        invalidate();
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        if (mRadius > 0f) {
            Path path = new Path();
            RectF rectF = new RectF();
            rectF.set(0f, 0f, getMeasuredWidth(), getMeasuredHeight());
            path.addRoundRect(rectF, mRadius, mRadius, Path.Direction.CW);
            canvas.clipPath(path);
        }
        super.draw(canvas);
    }
}