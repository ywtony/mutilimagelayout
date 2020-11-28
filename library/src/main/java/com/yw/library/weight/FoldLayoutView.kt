package com.yw.library.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.yw.library.R
import com.yw.library.adapter.BaseFoldLayoutAdapter
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.widget
 * @ClassName:      FoldView
 * @Description:     实现一个折叠View
 * 可实现任意布局、任意方向、任意形状的嵌套，用户只需要实现自定义的Adapter就行了。
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 16:20
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 16:20
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FoldLayoutView : ViewGroup {
    /**
     * 子布局的默认宽高
     */
    private var defaultChildWidth: Int = PxUtils.dp2px(context, 50f)
    private var defaultChildHeight: Int = PxUtils.dp2px(context, 50f)

    /**
     * 默认两个View的填充距离
     */
    private var defaultChildMarginLeft: Int = PxUtils.dp2px(context, 10f)
    private var defaultChildMarginRight: Int = PxUtils.dp2px(context, 0f)
    private var defaultChildMarginTop: Int = PxUtils.dp2px(context, 0f)
    private var defaultChildMarginBottom: Int = PxUtils.dp2px(context, 0f)

    /**
     * View的整体显示方向
     * 0：水平显示
     * 1：垂直显示
     * 2：对角显示
     */
    private var directionMode: Int? = 0

    /**
     * 当前选中的布局，如果选中了就给完全漏出来
     */
    private var currentSelectIndex = 0

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        //得到数据后直接转化为dp
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.app)
        defaultChildWidth = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildWidth, 0).toFloat()
        )
        defaultChildHeight = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildHeight, 0).toFloat()
        )
        defaultChildMarginLeft = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildMarginLeft, 0).toFloat()
        )
        defaultChildMarginRight = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildMarginRight, 0).toFloat()
        )
        defaultChildMarginTop = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildMarginTop, 0).toFloat()
        )
        defaultChildMarginBottom = PxUtils.dp2px(
            context,
            typeArray.getInteger(R.styleable.app_defaultChildMarginBottom, 0).toFloat()
        )
        directionMode =
            PxUtils.dp2px(context, typeArray.getInteger(R.styleable.app_directionMode, 0).toFloat())
        typeArray?.recycle()
    }


    /**
     * @description 测量View
     * @date: 2020/11/26 16:21
     * @author: wei.yang
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * @description 对测量后的View进行布局
     * @date: 2020/11/26 16:21
     * @author: wei.yang
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val count = childCount
        Log.e("TAG_______", count.toString())
        for (index in 0 until count) {
            //获取子布局
            val childView = this.getChildAt(index)
            val width = childView.measuredWidth
            val height = childView.measuredHeight
            when {
                index < currentSelectIndex -> {
                    childView.elevation = index.toFloat()
                    childView.translationZ = index.toFloat()
                }
                index == currentSelectIndex -> {
                    childView.elevation = currentSelectIndex.toFloat()
                    childView.translationZ = currentSelectIndex.toFloat()
                }
                else -> {
                    childView.elevation = (currentSelectIndex - index).toFloat()
                    childView.translationZ = (currentSelectIndex - index).toFloat()
                }
            }
            //横向布局，只会改变其长度而不会改变其高度
            when (directionMode) {
                0 -> {
                    childView.layout(
                        index * width - index * 60,
                        0,
                        (index + 1) * (width) - index * 60,
                        height
                    )
                }
                else -> {//纵向布局，只会改变其高度不会改变其长度
                    childView.layout(
                        0,
                        index * height - index * 60,
                        width,
                        (index + 1) * (height) - index * 60
                    )
                }
            }


        }
    }

    /**
     * @description 设置图片的数据源
     * @date: 2020/11/6 16:39
     * @author: wei.yang
     * @param margin 单位是dp
     * @return
     */
    fun setAdapter(views: ArrayList<View>) {
        removeAllViews()
        views?.apply {
            this.forEach {
                addView(it)
            }
        }
        requestLayout()
    }

    /**
     * @description 设置数据源
     * @date: 2020/11/26 19:49
     * @author: wei.yang
     */
    fun setAdapter(adapter: BaseFoldLayoutAdapter) {
        val count = adapter.getItemsCount()
        for (index in 0 until count) {
            val view = adapter.getItem(index, null, null)
            addView(view)
        }
        requestLayout()
    }

    /**
     * @description 设置当前选中的布局位置，谁被选中谁就全部显示
     * @date: 2020/11/27 19:22
     * @author: wei.yang
     * @param index 当前选中的布局坐标
     */
    fun setCurrentSelectIndex(index: Int) {
        this.currentSelectIndex = index
        requestLayout()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        val count = childCount
        for (index in 0 until count) {
            getChildAt(index).setOnClickListener {
                listener.onItemClick(index)
                setCurrentSelectIndex(index)
            }

        }

    }

    interface OnItemClickListener {
        fun onItemClick(index: Int)
    }
}