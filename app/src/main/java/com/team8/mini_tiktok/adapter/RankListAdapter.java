package com.team8.mini_tiktok.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @introduction: RankList适配器
 * @author: T19
 * @time: 2022.09.10 14:40
 */
public class RankListAdapter extends ArrayAdapter {
    public RankListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
}
