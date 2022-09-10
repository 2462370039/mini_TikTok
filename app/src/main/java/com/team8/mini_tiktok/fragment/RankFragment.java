package com.team8.mini_tiktok.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.team8.mini_tiktok.R;
import com.team8.mini_tiktok.activity.RankActivity;

import java.util.HashMap;
import java.util.Map;

public class RankFragment extends BaseFragment {
    public static final String ARG_PARAM1 = "access-token";
    private String access_token;

    ImageButton btn_film, btn_tv, btn_variety;


    public RankFragment() {
        // Required empty public constructor
    }

    public static RankFragment newInstance(String accessToken) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, accessToken);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    protected int initLayout() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void initView() {
        btn_film = mRootView.findViewById(R.id.btn_rank_film);
        btn_tv = mRootView.findViewById(R.id.btn_rank_tv);
        btn_variety = mRootView.findViewById(R.id.btn_rank_variety);
    }

    @Override
    protected void initDate() {
        btn_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>();
                params.put("type", "1");
                params.put(ARG_PARAM1, getArguments() != null ? getArguments().getString(ARG_PARAM1) : "");
                navigateToWithBundle(RankActivity.class, params);
            }
        });
        btn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>();
                params.put("type", "2");
                params.put(ARG_PARAM1, getArguments() != null ? getArguments().getString(ARG_PARAM1) : "");
                navigateToWithBundle(RankActivity.class, params);
            }
        });
        btn_variety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>();
                params.put("type", "3");
                params.put(ARG_PARAM1, getArguments() != null ? getArguments().getString(ARG_PARAM1) : "");
                navigateToWithBundle(RankActivity.class, params);
            }
        });
    }
}