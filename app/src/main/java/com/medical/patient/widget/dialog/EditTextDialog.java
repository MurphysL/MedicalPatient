package com.medical.patient.widget.dialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Selection;
import android.text.Spannable;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.medical.patient.R;
import com.medical.patient.app.MP_Utils;
import com.medical.patient.app.MyApplication;
import com.rengwuxian.materialedittext.MaterialEditText;


/**
 * Created by chenjun on 2016/10/9.
 */
public class EditTextDialog {
    public static void newEditDialog(final Context context, String title, String hintText,
                                     String flaotLabel, final TextView textView, int type) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .backgroundColor(ContextCompat.getColor(context, R.color.grey_20))
                .title(title)
                .titleColor(ContextCompat.getColor(context, R.color.grey_900))
                .typeface(MyApplication.getTypeface(), MyApplication.getTypeface())
                .customView(R.layout.dialog_edit, true)
                .positiveText("确定")
                .positiveColor(ContextCompat.getColor(context, R.color.my_blue))
                .negativeText("取消")
                .negativeColor(ContextCompat.getColor(context, R.color.grey_700));

        builder.onAny(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                if (which.equals(DialogAction.POSITIVE)) {
                    textView.setText(((EditText) dialog.getCustomView().findViewById(R.id.edittext))
                            .getText().toString().trim());
                    dialog.dismiss();
                } else if (which.equals(DialogAction.NEGATIVE)) {
                    dialog.dismiss();
                }
            }
        });

        MaterialDialog dialog = builder.build();
        MaterialEditText editText = (MaterialEditText) dialog.getCustomView().findViewById(R.id.edittext);
        // 如果编辑的textview存在值，在edittext中显示出来  考虑用户可能只做修改的情况
        String str = textView.getText().toString().trim();
        if (!str.equals("")) {
            editText.setText(str);

            // 光标定位到字符串尾部
           MP_Utils.cursorToEnd(editText);
        }


        editText.setInputType(type);
        editText.setHint(hintText);
        editText.setFloatingLabelText(flaotLabel);

        dialog.show();
    }
}
