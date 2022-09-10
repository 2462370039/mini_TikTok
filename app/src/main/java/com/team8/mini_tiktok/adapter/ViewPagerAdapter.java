package com.team8.mini_tiktok.adapter;

import android.view.FrameMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.09.09 18:44
 */
public class ViewPagerAdapter extends FragmentStateAdapter {
    private String[] mTitles;
    private ArrayList<Fragment> mFragments;

    public ViewPagerAdapter(@NonNull Fragment fragment, String[] mTitles, ArrayList<Fragment> mFragments) {
        super(fragment);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String[] mTitles, ArrayList<Fragment> mFragments) {
        super(fragmentManager, lifecycle);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
