package com.medical.patient.widget.dialog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.medical.patient.R;
import com.medical.patient.app.MyApplication;
import com.medical.patient.view.home.activity.ProFillinActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by chenjun on 2016/10/10.
 *
 * showmore的一个开源
 *      compile 'com.borjabravo:readmoretextview:2.0.1'
 */
public class TextViewDialog {
    public static void newTextDialog(final Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .backgroundColor(ContextCompat.getColor(context, R.color.grey_20))
                .title("Pro简介")
                .titleColor(ContextCompat.getColor(context, R.color.grey_900))
                .customView(R.layout.dialog_text_pro, false)
                .typeface(MyApplication.getTypeface(), MyApplication.getTypeface())
                .positiveText("Pro测量")
                .positiveColor(ContextCompat.getColor(context, R.color.my_blue))
                .negativeText("取消")
                .negativeColor(ContextCompat.getColor(context, R.color.grey_700));

        builder.onAny(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                if (which.equals(DialogAction.POSITIVE)) {
                    // 跳转到
                    context.startActivity(new Intent(context, ProFillinActivity.class));
                    dialog.dismiss();
                } else if (which.equals(DialogAction.NEGATIVE)) {
                    dialog.dismiss();
                }
            }
        });

        MaterialDialog dialog = builder.build();

        // 点击 获取更多
        ExpandableTextView text_pro = (ExpandableTextView) dialog.getCustomView().findViewById(R.id.expand_text_view);
        text_pro.setText(context.getString(R.string.pro_intro));

        dialog.show();
    }
}
