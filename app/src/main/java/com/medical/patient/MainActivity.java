package com.medical.patient;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.medical.patient.message.IMMLeaks;
import com.medical.patient.message.RefreshEvent;
import com.medical.patient.message.UserBean;
import com.medical.patient.view.account.fragment.AccountFragment;
import com.medical.patient.view.home.fragment.HomeFragment;
import com.medical.patient.view.message.fragment.MessageFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.notification.BmobNotificationManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private SpaceNavigationView navigationView;
    private Context mContext;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        if (fragmentList == null)
            fragmentList = new ArrayList<>();

        bindViews();
        initViews(savedInstanceState);
        initPagers();
        initIM();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigationView.onSaveInstanceState(outState);
    }

    public void bindViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        navigationView = (SpaceNavigationView) findViewById(R.id.buttom_navigation);
    }


    public void initViews(Bundle savedInstanceState) {
        // 底部导航
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.setCentreButtonIcon(R.mipmap.home_outline);
        navigationView.addSpaceItem(new SpaceItem("消息", R.mipmap.message_text_outline));
        navigationView.addSpaceItem(new SpaceItem("个人中心", R.mipmap.account_outline));
        navigationView.setSpaceOnClickListener(new NavigationClickListener());
        // 中心按钮的背景
        navigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.my_blue));

        // 未选中的条目文字和图标颜色
        navigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.grey_500));
        // 选中的颜色
        navigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.centre_button_color));
        viewPager.setOnPageChangeListener(this);
    }

    private void initPagers() {
        MessageFragment messageFragment = new MessageFragment();
        HomeFragment homeFragment = new HomeFragment();
        AccountFragment accountFragment = new AccountFragment();

        fragmentList.add(messageFragment);
        fragmentList.add(homeFragment);
        fragmentList.add(accountFragment);

        // 绑定适配器
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));

        viewPager.setCurrentItem(1);
    }

    public class NavigationClickListener implements SpaceOnClickListener {
        @Override
        public void onCentreButtonClick() {
            viewPager.setCurrentItem(1);
        }

        @Override
        public void onItemClick(int itemIndex, String itemName) {
            switch (itemIndex) {
                case 0:
                    viewPager.setCurrentItem(0);
                    break;
                case 1:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }

        @Override
        public void onItemReselected(int itemIndex, String itemName) {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigationView.changeCurrentItem(0);
                break;
            case 2:
                navigationView.changeCurrentItem(1);
                break;
        }
    }

    /**
     * IM
     */
    @Override
    protected void onResume() {
        super.onResume();
        //显示小红点
        /*checkRedPoint();*/
        //进入应用后，通知栏应取消
        BmobNotificationManager.getInstance(this).cancelNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BmobIM.getInstance().clear();//清理导致内存泄露的资源
    }

    /**
     * 初始化即时通讯
     */
    private void initIM() {
        UserBean user = BmobUser.getCurrentUser(this, UserBean.class);
        BmobIM.connect(user.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    EventBus.getDefault().post(new RefreshEvent());//发送一个更新事件，同步更新会话及主页的小红点
                } else {
                    Log.d("IMurphySL", e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });
        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
        BmobIM.getInstance().getCurrentStatus();
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus connectionStatus) {
                Log.d("IMurphySL", "" + connectionStatus.getMsg());
                Toast.makeText(MainActivity.this , "" + connectionStatus.getMsg() , Toast.LENGTH_SHORT ).show();
            }
        });
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());
    }

    /**
     * 注册消息接收事件
     *
     * @param event
     */
    /*@Subscribe
    public void onEventMainThread(MessageEvent event) {
        checkRedPoint();
    }*/

    /**
     * 注册离线消息接收事件
     *
     * @param event
     */
   /* @Subscribe
    public void onEventMainThread(OfflineMessageEvent event) {
        checkRedPoint();
    }*/

    /**
     * 注册自定义消息接收事件
     *
     * @param event
     */
   /* @Subscribe
    public void onEventMainThread(RefreshEvent event) {
        log("---主页接收到自定义消息---");
        checkRedPoint();
    }*/
}
