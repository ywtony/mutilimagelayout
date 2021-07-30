package com.yw.library.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.yw.library.R

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight
 * @ClassName:      SwitchImageView
 * @Description:    用于切换两张图片的ImageView
 * @Author:         wei.yang
 * @CreateDate:     2021/6/25 16:55
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/6/25 16:55
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SwitchImageView : androidx.appcompat.widget.AppCompatImageView {
    private var checkRes: Int = 0
    private var uncheckRes: Int = 0

    //是否选中
    private var isCheck: Boolean = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.app)
        checkRes = typedArray.getResourceId(R.styleable.app_checkres, 0)
        uncheckRes = typedArray.getResourceId(R.styleable.app_uncheckres, 0)
        if (uncheckRes > 0) {
            setImageResource(uncheckRes)
        }
        setOnClickListener {
            isCheck = if (isCheck) {
                setImageResource(uncheckRes)
                false
            } else {
                setImageResource(checkRes)
                true
            }
            listener?.onSwitchClick(isCheck)
        }

    }

    /**
     * 设置资源id
     * @param checkres 选中时的图标
     * @param uncheckres 未选中时的图标
     * @param isCheck 是否选中
     */
    fun setResource(checkres: Int, uncheckres: Int, isCheck: Boolean) {
        if (checkres <= 0 || uncheckres <= 0) {
            throw RuntimeException("资源id都不能为空")
        }
        this.checkRes = checkRes
        this.uncheckRes = uncheckRes
        this.isCheck = isCheck
    }

    private var listener: SwitchImageViewClickListener? = null
    fun setSwitchImageViewClickListener(listener: SwitchImageViewClickListener) {
        this.listener = listener
    }

    interface SwitchImageViewClickListener {
        fun onSwitchClick(isCheck: Boolean)
    }


}