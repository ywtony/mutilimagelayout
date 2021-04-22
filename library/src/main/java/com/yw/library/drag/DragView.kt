package com.yw.library.drag

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.yw.library.utils.PxUtils


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.drag
 * @ClassName:      DragView
 * @Description:    可拖动到任意位置的View
 * @Author:         wei.yang
 * @CreateDate:     2021/4/22 14:37
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/4/22 14:37
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class DragView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var lastX = 0f
    private var lastY = 0f
    private var screenHeight = PxUtils.getScreenHeight(getContext())
    private var screenWidth = PxUtils.getScreenWidth(context)
    private var startTime = System.currentTimeMillis()
    private var endTime = System.currentTimeMillis()

    init {
        elevation = PxUtils.dp2px(context, 5f).toFloat()

    }

    fun setPosition(l: Int, t: Int, r: Int, b: Int) {
        this.layout(l, t, r, b)
    }

    fun setDefaultViewResource(resId: Int) {
        removeAllViews()
        val imagView = ImageView(context)
        imagView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        imagView.scaleType = ImageView.ScaleType.CENTER_CROP
        imagView.setImageResource(resId)
        addView(imagView)
    }

    fun setContentView(view: View) {
        Log.e("DragView", screenHeight.toString())
        removeAllViews()
        addView(view)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {//按下
                startTime = System.currentTimeMillis()
                lastX = event.rawX
                lastY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {//移动
                //移动中动态设置位置
                var dx = event.rawX - lastX
                var dy = event.rawY - lastY
                var l = this.left + dx
                var t = this.top + dy
                var r = this.right + dx
                var b = this.bottom + dy
                if (l < 0) {
                    l = 0f
                    r = l + this.width
                }
                if (r > screenWidth) {
                    r = screenWidth.toFloat()
                    l = r - this.width
                }

                if (t < 0) {
                    t = 0f
                    b = t + this.height

                }
                if (b > screenHeight) {
                    b = screenHeight.toFloat()
                    t = b - this.height
                }
                this.setRelativeViewLocation(this,l.toInt(), t.toInt(), r.toInt(), b.toInt())
                this.layout(l.toInt(), t.toInt(), r.toInt(), b.toInt())
                Log.i("DragView", "Position：$l, $t, $r, $b");
                lastX = event.rawX
                lastY = event.rawY

            }
            MotionEvent.ACTION_UP -> {//抬起
                endTime = System.currentTimeMillis()
                if ((endTime - startTime) > 0.1 * 1000L) {
                    return true
                }
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }
    private fun setRelativeViewLocation(
        view: View,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {  //防止刷新布局，组件回到原来位置
        val params =
            RelativeLayout.LayoutParams(right - left, bottom - top)
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        val parent = view.parent
        val p = parent as View
        val marginRight = p.width - right
        val marginBottom = p.height - bottom
        params.setMargins(left, top, marginRight, marginBottom)
        view.layoutParams = params
    }
}