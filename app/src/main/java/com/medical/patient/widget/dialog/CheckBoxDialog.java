package com.medical.patient.widget.dialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.medical.patient.R;
import com.medical.patient.app.MyApplication;

/**
 * Created by chenjun on 2016/10/9.
 */
public class CheckBoxDialog {
    public static void newCheckBoxDialog(final Context context, String title, final TextView textView) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .backgroundColor(ContextCompat.getColor(context, R.color.grey_20))
                .title(title)
                .titleColor(ContextCompat.getColor(context, R.color.grey_900))
                .typeface(MyApplication.getTypeface(), MyApplication.getTypeface())
                .items(R.array.gender)
                .itemsColor(ContextCompat.getColor(context, R.color.grey_600))
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        textView.setText((text));
                        return true;
                    }
                })
                .negativeText("取消")
                .negativeColor(ContextCompat.getColor(context, R.color.my_blue));

        MaterialDialog dialog = builder.build();
        dialog.show();
    }
}
