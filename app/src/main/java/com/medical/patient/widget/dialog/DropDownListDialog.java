package com.medical.patient.widget.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.medical.patient.R;
import com.medical.patient.app.MyApplication;

import java.util.List;

/**
 * Created by chenjun on 2016/10/9.
 */
public class DropDownListDialog {
    private static int selectedItem;
    public static void newListDialog(final Context context, String title, final List<String> data,
                                     final TextView textView) {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .backgroundColor(ContextCompat.getColor(context, R.color.grey_20))
                .title(title)
                .titleColor(ContextCompat.getColor(context, R.color.grey_900))
                .typeface(MyApplication.getTypeface(), MyApplication.getTypeface())
                .customView(R.layout.recycler_view, false)
                .positiveColor(ContextCompat.getColor(context, R.color.my_blue))
                .positiveText("确定")
                .negativeColor(ContextCompat.getColor(context, R.color.grey_700))
                .negativeText("取消")
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        if (which.equals(DialogAction.POSITIVE)) {
                            textView.setText(data.get(selectedItem));
                            dialog.dismiss();
                        } else if (which.equals(DialogAction.NEGATIVE)) {
                            dialog.dismiss();
                        }
                    }
                }).build();

        RecyclerView recyclerView = (RecyclerView) dialog.getCustomView().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context.getResources(),
                R.color.grey_200, R.dimen.dimen_1px, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedItem = position;
            }
        });

        dialog.show();
    }

    // 设置分割线
    private static class DividerItemDecoration extends RecyclerView.ItemDecoration {
        private final Drawable mDivider;
        private final int mSize;
        private final int mOrientation;

        public DividerItemDecoration(Resources resources, @ColorRes int color,
                                     @DimenRes int size, int orientation) {
            mDivider = new ColorDrawable(resources.getColor(color));
            mSize = resources.getDimensionPixelSize(size);
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int left;
            int right;
            int top;
            int bottom;
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params =
                            (RecyclerView.LayoutParams) child.getLayoutParams();
                    left = child.getRight() + params.rightMargin;
                    right = left + mSize;
                    mDivider.setBounds(left+10, top, right+10, bottom);
                    mDivider.draw(canvas);
                }
            } else {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params =
                            (RecyclerView.LayoutParams) child.getLayoutParams();
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + mSize;
                    mDivider.setBounds(left+10, top, right+10, bottom);
                    mDivider.draw(canvas);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                outRect.set(0, 0, mSize, 0);
            } else {
                outRect.set(0, 0, 0, mSize);
            }
        }
    }
}
