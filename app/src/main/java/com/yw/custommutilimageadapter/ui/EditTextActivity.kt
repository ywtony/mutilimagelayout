package com.yw.custommutilimageadapter.ui

import android.app.Activity
import android.os.Bundle
import android.text.Spannable
import com.yw.custommutilimageadapter.R
import kotlinx.android.synthetic.main.activity_edittext.*

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.ui
 * @ClassName:      EditTextActivity
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2021/1/6 15:46
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2021/1/6 15:46
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class EditTextActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext)
        initViews()
    }
    private fun initViews(){
        btnStart.setOnClickListener {
            etContent.addAtSpan("@","朋友的酒");
            etContent.text?.length?.let { it1 -> etContent.setSelection(it1) };
        }
        btnEnd.setOnClickListener {
            etContent.addAtSpan("#","话题1");
            etContent.text?.length?.let { it1 -> etContent.setSelection(it1) };
        }
    }
}