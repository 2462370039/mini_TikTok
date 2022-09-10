package com.team8.mini_tiktok.activity;

import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.adapter.ViewPagerAdapter;
import com.team8.mini_tiktok.entity.view.TabEntity;
import com.team8.mini_tiktok.fragment.MineFragment;
import com.team8.mini_tiktok.fragment.RankFragment;

import java.util.ArrayList;

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
        //TODO
//        client_token = getStringFromSP("client_token");
        client_token = "clt.a00bbfe25f1702ef2e99fd1e3f3f701auURv7175nZq2hhNhZcKTUNUL9VpY";
        if (client_token == null || TextUtils.isEmpty(client_token)) {
            getClientToken();
            client_token = getStringFromSP("client_token");
        }
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
}