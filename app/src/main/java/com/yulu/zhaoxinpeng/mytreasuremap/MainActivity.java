package com.yulu.zhaoxinpeng.mytreasuremap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.UserPrefs;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.login.LoginActivity;
import com.yulu.zhaoxinpeng.mytreasuremap.activity.user.register.RegisterActivity;
import com.yulu.zhaoxinpeng.mytreasuremap.commons.ActivityUtils;
import com.yulu.zhaoxinpeng.mytreasuremap.treasure.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static final String MAI_ACTION = "navigate_to_main";
    @BindView(R.id.btn_Register)
    Button btnRegister;
    @BindView(R.id.btn_Login)
    Button btnLogin;
    private ActivityUtils mActivityUtils;
    private Unbinder unbinder;

    //接收到广播后关闭 MainActivity
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityUtils = new ActivityUtils(this);

        unbinder = ButterKnife.bind(this);

        /**
         * 判断用户是否已经登录过
         * 如果SharedPreferences 中已经存在登录的ID，则跳过登录界面直接进入主界面
         */
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        if (preferences!=null) {
            if (preferences.getInt("key_tokenid",0)== UserPrefs.getInstance().getTokenid()) {
                mActivityUtils.startActivity(HomeActivity.class);
                finish();
            }
        }

        //注册本地广播
        IntentFilter filter = new IntentFilter(MAI_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    //使用Butterknife插件自动生成
    @OnClick({R.id.btn_Register, R.id.btn_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
