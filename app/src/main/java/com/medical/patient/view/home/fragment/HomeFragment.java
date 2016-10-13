package com.medical.patient.view.home.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.medical.patient.R;
import com.medical.patient.app.Constants;
import com.medical.patient.widget.dialog.TextViewDialog;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenjun on 2016/10/7.
 */
public class HomeFragment extends Fragment {
    private View view;
    private Toolbar toolbar;
    private ConvenientBanner convenientBanner;  //顶部广告栏控件
    private ImageView broadcast_icon;
    private AnimationDrawable animationDrawable;
    private HTextView broadcast_text;
    private ArrayList<Integer> localImages = new ArrayList<>();

    private int mCounter = Constants.BRAODCAST.length;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler mHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 开始翻页
        convenientBanner.startTurning(3000);

        // 获取 AnimationDrawable
        animationDrawable = (AnimationDrawable) broadcast_icon.getDrawable();
        animationDrawable.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        // 停止翻页
        convenientBanner.stopTurning();
        // 停止广播图标的动画
        animationDrawable = (AnimationDrawable) broadcast_icon.getDrawable();
        animationDrawable.stop();
    }

    private void bindViews() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.banner_view);

        broadcast_icon = (ImageView) view.findViewById(R.id.broadcast_anim);
        broadcast_text = (HTextView) view.findViewById(R.id.broadcast_text);
    }

    private void initViews() {
        toolbar.setTitle("");
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //监听翻页事件
        //.setOnPageChangeListener(this)
        //.setOnItemClickListener(this);

        setListeners();

        showBroadcastText();
    }

    private void setListeners() {
        (view.findViewById(R.id.layout_pro_review)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        (view.findViewById(R.id.layout_pro_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextViewDialog.newTextDialog(getActivity());
            }
        });

        (view.findViewById(R.id.layout_my_doctor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        (view.findViewById(R.id.layout_health_center)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void showBroadcastText() {
        mTimer = new Timer();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        mCounter = mCounter >= Constants.BRAODCAST.length - 1 ? 0 : mCounter + 1;
                        broadcast_text.animateText(Constants.BRAODCAST[mCounter]);
                        break;
                }
            }
        };

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };

        mTimer.schedule(mTimerTask, 1000, 3000);
    }

    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
