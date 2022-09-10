package com.team8.mini_tiktok.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.09.09 19:07
 */
public class MyAdapter extends PagerAdapter {

    private String[] mTitles;

    private ArrayList<Fragment> mFragments;

    public MyAdapter(String[] mTitles, ArrayList<Fragment> mFragments) {
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
