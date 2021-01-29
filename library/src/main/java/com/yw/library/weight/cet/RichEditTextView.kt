package com.yw.library.weight.cet

import android.content.Context
import android.graphics.Typeface
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText


/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.library.weight.cet
 * @ClassName:      RichEditTextView
 * @Description:    富文本TextView,目前的版本支持@符号和#号的支持
 * @Author:         wei.yang
 * @CreateDate:     2021/1/18 10:31
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/18 10:31
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class RichEditTextView(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    init {
        /**
         * @method  监听软键盘输入，此处主要是监听删除按钮
         * @description
         * @date: 2021/1/18 10:45
         * @author: wei.yang
         */
        setOnKeyListener { v, keyCode, event ->
            //监听删除操作，找到最靠近删除的一个span,然后将其整体删除
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {//监听软键盘删除按钮
                var selectionStart = Selection.getSelectionStart(text)
                var selectionEnd = Selection.getSelectionEnd(text)
                val colorSpans =
                    text?.getSpans(selectionStart, selectionEnd, ForegroundColorSpan::class.java)
                colorSpans?.forEach {
                    if (it != null) {
                        if (text?.getSpanEnd(it) == selectionStart) {
                            var spanStart = text?.getSpanStart(it)
                            var spanEnd = text?.getSpanEnd(it)
                            Selection.setSelection(text, spanStart!!, spanEnd!!)
                            return@setOnKeyListener selectionStart == selectionEnd
                        }
                    }
                }
                return@setOnKeyListener false
            }
            return@setOnKeyListener false
        }

        /**
         * @method  监听文本变化
         * @description
         * @date: 2021/1/18 10:45
         * @author: wei.yang
         */
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before == 0 && count > 0) {
                    val selectionStart =
                        if (Selection.getSelectionStart(text) == -1) 0 else Selection.getSelectionStart(
                            text
                        )
                    val selectionEnd =
                        if (Selection.getSelectionEnd(text) == -1) 0 else Selection.getSelectionEnd(
                            text
                        )
                    if (selectionStart != selectionEnd) {
                        return
                    }
                    if (selectionStart == 0) {
                        return
                    }
                    val selectionPrevCharSeq =
                        s!!.subSequence(selectionStart - 1, selectionStart)
                    if (TextUtils.isEmpty(selectionPrevCharSeq)) {
                        return
                    }

                    if (selectionPrevCharSeq.toString() == "#") {//监听#号输入
                        if (listener != null) {
                            listener.onCheckSpecialSymbols(2)
                        }
                    }

                    if (selectionPrevCharSeq.toString() == "@") {//监听@符号输入
                        if (listener != null) {
                            listener.onCheckSpecialSymbols(1)
                        }
                    }
                }
            }

        })
    }

    /**
     * @description 添加ColorSpan
     * @date: 2021/1/18 10:56
     * @author: wei.yang
     * @param mask 前缀符号，如@ # &等
     * @param str 前缀符号后面跟的字符串
     * @param color span的颜色
     */
    fun addRichSpan(mask: String, str: String, color: Int) {
        if (text != null) {
            val strBuilder = StringBuilder()
            strBuilder.append(mask).append(str).append(" ")
            val spannableString = SpannableStringBuilder(text)
            spannableString.append(strBuilder.toString())
            val styleSpan = StyleSpan(Typeface.BOLD_ITALIC)
            val colorSpan = ForegroundColorSpan(context.resources.getColor(color))
            spannableString.setSpan(
                styleSpan,
                spannableString.length - strBuilder.toString().length,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                colorSpan,
                spannableString.length - strBuilder.toString().length,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setText(spannableString)
            setSelection(spannableString.length)
        }
    }

    private lateinit var listener: OnSpecialSymbolsListener
    fun setOnSpecialSymbolsListener(listener: OnSpecialSymbolsListener) {
        this.listener = listener
    }

    interface OnSpecialSymbolsListener {
        /**
         * @description 检测特殊符号类型的回调
         * @date: 2021/1/18 11:35
         * @author: wei.yang
         * @param type 特殊符号类型1代表@ 2代表#
         */
        fun onCheckSpecialSymbols(type: Int)
    }

    fun getSpansString(){
        val spannable = text as Spannable
        spannable.split("@").forEach {
            Log.e("RichEditTextView:",it.toString())
        }
        spannable.forEach {
            Log.e("RichEditTextView:",it.toString())
        }
        val spans = text!!.getSpans(0, spannable!!.length, ForegroundColorSpan::class.java)
        spans.forEach {
            val start = 0;
            val end = 10

        }

    }
}