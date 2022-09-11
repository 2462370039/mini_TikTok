package com.team8.mini_tiktok.activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.team8.mini_tiktok.AppConfig;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.adapter.ViewPagerAdapter;
import com.team8.mini_tiktok.entity.GetClientTokenResponse;
import com.team8.mini_tiktok.entity.view.TabEntity;
import com.team8.mini_tiktok.fragment.MineFragment;
import com.team8.mini_tiktok.fragment.RankFragment;
import com.team8.mini_tiktok.network.Api;
import com.team8.mini_tiktok.network.ApiConfig;
import com.team8.mini_tiktok.network.MyCallBack;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends BaseActivity {
    private String access_token;
    private String client_token;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity>  mTabEntities = new ArrayList<>();

    private final String[] mTitles = {"榜单", "我"};
    private final int[] mIconUnselectIds = {R.drawable.ic_tab_rank_unselect_24, R.drawable.ic_tab_home_unselect_24};
    private final int[] mIconSelectedIds = {R.drawable.ic_tab_rank_24, R.drawable.ic_tab_home_24};

    private ViewPager2 viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initDate() {
        //TODO:判断access_token是否过期
        access_token = getStringFromSP("access_token");

        //同步请求client_token
        new Thread(new Runnable() {
            @Override
            public void run() {
                getClientToken();
            }
        }).start();

        client_token = getStringFromSP("client_token");
        
        Log.d("TZH", "HomeActivity initDate: client_token=" + client_token);

        mFragments.add(RankFragment.newInstance(client_token));
        mFragments.add(MineFragment.newInstance());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectedIds[i], mIconUnselectIds[i]));
        }

        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        //预加载
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), mTitles, mFragments);
        viewPager.setAdapter(adapter);

    }

    protected void getClientToken(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("client_key", AppConfig.clientkey);
        params.put("client_secret", AppConfig.clientSecret);
        params.put("grant_type", ApiConfig.GRANT_TYPE_GET_CLT);

        Api.config(ApiConfig.GET_CLIENT_TOKEN, params).postRequest("multipart/form-data;charset=utf-8", new MyCallBack() {
            @Override
            public void onSuccess(String res) {
                String client_token;
                Gson gson = new Gson();
                GetClientTokenResponse clientTokenResponse = gson.fromJson(res, GetClientTokenResponse.class);
                if ("success".equals(clientTokenResponse.getMessage())) {//响应成功
                    Log.d("TZH", "onSuccess : get client_token --->  success");
                    GetClientTokenResponse.DataBean data = clientTokenResponse.getData();
                    client_token = data.getAccess_token();
                    //TODO:存放到数据库中
                    //缓存access_token
                    SharedPreferences sp = getSharedPreferences(AppConfig.SP_FILE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("client_token", client_token);
                    editor.apply();
                } else {
                    Log.d("TZH", "onSuccess: get client_token ---> error_code:" + clientTokenResponse.getData().getError_code());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}