package com.medical.patient.widget.snackbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.medical.patient.app.MyApplication;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;


/**
 * Created by chenjun on 2016/10/5.
 */
public class CustomSnackBar {
    public static void showNoAction(Context context, int bgColor, String text, Activity activity) {
        SnackbarManager.show(
                Snackbar.with(context) // context
                        .text(text) // text to be displayed
                        .textColor(Color.WHITE) // change the text color
                        .textTypeface(MyApplication.getTypeface()) // change the text font
                        .color(bgColor) // change the background color
                        , activity);
    }

    public static void showWithAction(Context context, int bgColor, String text, String actionLable,
                                      Activity activity, int actionColor) {
        SnackbarManager.show(
                Snackbar.with(context) // context
                        .text(text) // text to be displayed
                        .textColor(Color.WHITE) // change the text color
                        .textTypeface(MyApplication.getTypeface()) // change the text font
                        .color(bgColor) // change the background color
                        .actionLabel(actionLable) // action button label
                        .actionColor(actionColor) // action button label color
                        .actionLabelTypeface(MyApplication.getTypeface()) // change the action button font
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                Log.d("", "Doing something");
                            }
                        }) // action button's ActionClickListener
                , activity); // activity where it is displayed

    }
}
