package com.yw.library.weight.taskbar

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.yw.library.R
import com.yw.library.utils.PxUtils

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.taskbar
 * @ClassName:      TaskProgressBar
 * @Description:     任务进度条
 * @Author:         wei.yang
 * @CreateDate:     2021/3/1 16:41
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/3/1 16:41
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class TaskProgressBar : RelativeLayout {
    //布局的宽度
    private var viewWidth: Int = 0

    //布局的高度
    private var viewHeight: Int = 0

    //进度条长度
    private var progressBarWidth: Int = 0

    //进度条
    private var progressBar: TextView? = null
    private var progressBar2: TextView? = null

    //四大进度
    private var tvOne: TextView? = null
    private var tvTwo: TextView? = null
    private var tvThree: TextView? = null
    private var linearFour: LinearLayout? = null
    private var tvTop: TextView? = null
    private var tvBottom: TextView? = null
    private var bar:Float =0f
    /**
     * 0:<25%
     * 1:>2=25%<50%
     * 2:>=50%<=75%
     * 3:>75%<100
     * 4:=100%
     */
    private var progressType: Int = 0//砍一刀进度

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.app)
//        bgColor = typedArray.getColor(R.styleable.app_sliderBg, 0)
//        sliderBg = typedArray.getResourceId(R.styleable.app_sliderChildBg, 0)
//        sliderWidth = typedArray.getDimensionPixelSize(R.styleable.app_sliderWidth, 0)
//        init()
    }

    /**
     * @description 对布局进行初始化
     * @date: 2021/3/1 16:44
     * @author: wei.yang
     */
    private fun init(bar: Float) {
        //初始化View的宽高
        viewWidth = measuredWidth
        viewHeight = measuredHeight
        //初始化进度条
        progressBarWidth = viewWidth - PxUtils.dp2px(context, 36f) - PxUtils.dp2px(context, 61f)
        progressBar = createTextView(
            viewWidth,
            PxUtils.dp2px(context, 8f),
            R.drawable.bg_barg_progressbar,
            CENTER_IN_PARENT,
            0, 0
        )

        progressBar2 = createTextView(
            (progressBarWidth * bar).toInt(),
            PxUtils.dp2px(context, 6.5f),
            R.drawable.bg_barg_progressbar2,
            CENTER_VERTICAL,
            PxUtils.dp2px(context, 36f), PxUtils.dp2px(context, 61f)
        )
        tvOne = createTextView(
            PxUtils.dp2px(context, 36f),
            PxUtils.dp2px(context, 36f),
            R.drawable.bg_barg_one_progress,
            ALIGN_PARENT_START
            ,
            0
        )
        tvOne?.textSize = 12f
        tvOne?.text = "0%"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvOne?.setTextColor(context.getColor(R.color.color_FFFABE))
        }
        tvTwo = createTextView(
            PxUtils.dp2px(context, 36f),
            PxUtils.dp2px(context, 36f),
            R.drawable.bg_barg_one_progress2,
            CENTER_VERTICAL
            ,
            (0.25f * viewWidth).toInt()
        )
        tvTwo?.text = "25%"
        tvTwo?.textSize = 12f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvTwo?.setTextColor(context.getColor(R.color.color_FF7201))
        }
        tvThree = createTextView(
            PxUtils.dp2px(context, 44f),
            PxUtils.dp2px(context, 44f),
            R.drawable.bg_barg_three_progress2,
            CENTER_VERTICAL,
            (0.55f * viewWidth).toInt()
        )
        tvThree?.text = "50%"
        tvThree?.textSize = 15f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvThree?.setTextColor(context.getColor(R.color.color_FF7201))
        }
        linearFour = createLinearLayout()
        linearFour?.setBackgroundResource(R.drawable.bg_barg_four_progress2)
        when (progressType) {
            0 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvOne?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvOne?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTwo?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                tvTwo?.setBackgroundResource(R.drawable.bg_barg_one_progress2)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvThree?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                tvThree?.setBackgroundResource(R.drawable.bg_barg_three_progress2)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTop?.setTextColor(context.getColor(R.color.color_FF7201))
                    tvBottom?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                linearFour?.setBackgroundResource(R.drawable.bg_barg_four_progress2)

            }
            1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvOne?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvOne?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTwo?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvTwo?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvThree?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                tvThree?.setBackgroundResource(R.drawable.bg_barg_three_progress2)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTop?.setTextColor(context.getColor(R.color.color_FF7201))
                    tvBottom?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                linearFour?.setBackgroundResource(R.drawable.bg_barg_four_progress2)
            }
            2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvOne?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvOne?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTwo?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvTwo?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvThree?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvThree?.setBackgroundResource(R.drawable.bg_barg_three_progress)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTop?.setTextColor(context.getColor(R.color.color_FF7201))
                    tvBottom?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                linearFour?.setBackgroundResource(R.drawable.bg_barg_four_progress2)
            }
            else -> {//默认100%
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvOne?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvOne?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTwo?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvTwo?.setBackgroundResource(R.drawable.bg_barg_one_progress)



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvThree?.setTextColor(context.getColor(R.color.color_FFFABE))
                }
                tvThree?.setBackgroundResource(R.drawable.bg_barg_three_progress)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTop?.setTextColor(context.getColor(R.color.color_FF7201))
                    tvBottom?.setTextColor(context.getColor(R.color.color_FF7201))
                }
                linearFour?.setBackgroundResource(R.drawable.bg_barg_four_progress)
            }
        }

        removeAllViews()
        addView(progressBar)
        addView(progressBar2)
        addView(createImageView())
        addView(tvOne)
        addView(tvTwo)
        addView(tvThree)
        addView(linearFour)
    }

    /**
     * @description 创建四大进度
     * @date: 2021/3/1 16:53
     * @author: wei.yang
     * @param width 宽度
     * @param height 高度
     * @param bgRes 背景
     * @return 返回的View
     */
    private fun createTextView(
        width: Int,
        height: Int,
        bgRes: Int,
        rule: Int,
        marginStart: Int
    ): TextView {
        val tvView = TextView(context)
        val params = LayoutParams(width, height)
        params.addRule(CENTER_VERTICAL)
        if (rule > 0) {
            params.addRule(rule)
        }
        params.marginStart = marginStart
        tvView.layoutParams = params
        if (bgRes > 0) {
            tvView.setBackgroundResource(bgRes)
        }
        tvView.gravity = Gravity.CENTER
        return tvView
    }

    private fun createTextView(
        width: Int,
        height: Int,
        bgRes: Int,
        rule: Int,
        marginStart: Int, marginRight: Int
    ): TextView {
        val tvView = TextView(context)
        val params = LayoutParams(width, height)
        params.addRule(CENTER_VERTICAL)
        params.addRule(rule)
        params.marginStart = marginStart
        params.marginEnd = marginRight
        tvView.layoutParams = params
        tvView.setBackgroundResource(bgRes)
        tvView.gravity = Gravity.CENTER
        return tvView
    }

    /**
     * 创建一个LinearLayout
     * @description
     * @date: 2021/3/2 16:15
     * @author: wei.yang
     */
    private fun createLinearLayout(): LinearLayout {
        val linear = LinearLayout(context)
        linear.orientation = LinearLayout.VERTICAL
        linear.gravity = Gravity.CENTER
        val params = LayoutParams(PxUtils.dp2px(context, 61f), PxUtils.dp2px(context, 61f))
        params.addRule(CENTER_VERTICAL)
        params.addRule(ALIGN_PARENT_END)
        linear.layoutParams = params

        tvTop = createTextView(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, -1,
            -1,
            0
        )
        tvTop?.text = "100元"
        tvTop?.textSize = 17f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvTop?.setTextColor(context.getColor(R.color.color_FF7201))
        }

        tvBottom = createTextView(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, -1,
            -1,
            0
        )
        tvBottom?.text = "现金红包"
        tvBottom?.textSize = 10f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvBottom?.setTextColor(context.getColor(R.color.color_FF7201))
        }
        linear.removeAllViews()
        linear.addView(tvTop)
        linear.addView(tvBottom)
        return linear
    }

    private fun initView(bar: Float) {
        init(bar)
    }

    /**
     * @description 创建一个ImageView
     * @date: 2021/3/4 14:58
     * @author: wei.yang
     */
    private fun createImageView(): ImageView {
        val imageView = ImageView(context)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.bottomMargin = PxUtils.dp2px(context,5f)
        params.marginStart = (progressBarWidth * bar).toInt()
        progressBar?.id?.let { params.addRule(ABOVE, it) }
        imageView.layoutParams = params
        imageView.setImageResource(R.drawable.bg_bragaining_progress_money)
        return imageView
    }

    /**
     * @description 更新进度条
     * @date: 2021/3/1 19:32
     * @author: wei.yang
     * @param bar 服务端返回的百分比，此处有一点需要特别注意：由于UI设计的原因进度条没法按照正常的比分比来加载，所以需要针对bar做一系列的逻辑判断
     */
    fun requestProgressBar(bar: Float) {
        this.bar = bar
        var progress = bar
        if (bar in 0.50..0.75) {
            progress = 0.75f
        }
        if (progress < 0.25) {
            progressType = 0
        } else if (progress >= 0.25 && progress < 0.5) {
            progressType = 1
        } else if (progress >= 0.5 && progress < 1) {
            progressType = 2
        } else {
            progressType = 3

        }
        initView(progress)

    }
}