package com.team8.mini_tiktok.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.team8.mini_tiktok.AppConfig;

import java.util.Map;

/**
 * @introduction: Fragment基类
 * @author: T19
 * @time: 2022.09.09 15:55
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDate();
    }

    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initDate();


    /**
     * 跳转到activity
     * @param activity 目标Activity
     */
    public void navigateTo(Class activity){
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    /**
     * 跳转到activity
     * @param activity  目标Activity
     * @param params  传递的数据集
     */
    public void navigateToWithBundle(Class activity, Map<String, Object> params){
        Intent intent = new Intent(getActivity(), activity);
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            bundle.putString(entry.getKey(), String.valueOf(entry.getValue()));
        }
        intent.putExtra("Args", bundle);
        startActivity(intent);
    }

    /**
     * 跳转到activity 同时处理Task
     * @param activity 目标Activity
     * @param flags 处理标志 Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK
     */
    public void navigateToWithFlag(Class activity, int flags){
        Intent intent = new Intent(getActivity(), activity);
        intent.setFlags(flags);
        startActivity(intent);
    }

    /**
     * 从XML文件中获取数据值
     * @param key 要获取数据的key
     * @return 要获取的数据
     */
    protected String getStringFromSP(String key){
        SharedPreferences sp = getActivity().getSharedPreferences( AppConfig.SP_FILE_NAME, MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存token信息
     * @param key  "token"
     * @param val  token值
     */
    protected void saveStringToSP(String key, String val){
        SharedPreferences sp = getActivity().getSharedPreferences( AppConfig.SP_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(key, val);
        editor.apply();
    }
}
