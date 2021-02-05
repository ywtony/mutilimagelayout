package com.yw.library.weight.arc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.yw.library.R;

/**
 * @ProjectName: CustomMutilImageAdapter
 * @Package: com.yw.library.weight
 * @ClassName: ArcView
 * @Description: 自定义弧形背景
 * @Author: wei.yang
 * @CreateDate: 2021/1/29 14:11
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/1/29 14:11
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ArcView extends View {
    private int mWidth;
    private int mHeight;

    private int mArcHeight; //圆弧的高度
    private int mBgColor;   //背景颜色
    private int lgColor;    //变化的最终颜色
    private Paint mPaint;  //画笔
    private LinearGradient linearGradient;
    private Rect rect = new Rect(0, 0, 0, 0);//普通的矩形
    private Path path = new Path();//用来绘制曲面

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.app);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.app_arcHeight, 0);
        mBgColor = typedArray.getColor(R.styleable.app_bgColor, Color.parseColor("#303F9F"));
        lgColor = typedArray.getColor(R.styleable.app_lgColor, mBgColor);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.d("----","onSizeChanged");
        linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                mBgColor, lgColor, Shader.TileMode.CLAMP
        );
        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置成填充
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBgColor);

        //绘制矩形
        rect.set(0, 0, mWidth, mHeight - mArcHeight);
        canvas.drawRect(rect, mPaint);

        //绘制路径
        path.moveTo(0, mHeight - mArcHeight);
        path.quadTo(mWidth >> 1, mHeight, mWidth, mHeight - mArcHeight);
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

}
