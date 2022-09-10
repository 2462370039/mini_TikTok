package com.team8.mini_tiktok.entity.view;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.09.09 18:29
 */
public class TabEntity implements CustomTabEntity {
    private String tabTitle;
    private int tabSelectedIcon;
    private int tabUnselectedIcon;

    public TabEntity(){}

    public TabEntity(String title, int iconSelectedId, int iconUnselectId){
        this.tabTitle = title;
        this.tabSelectedIcon = iconSelectedId;
        this.tabUnselectedIcon = iconUnselectId;
    }

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabUnselectedIcon;
    }
}
