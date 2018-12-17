package com.selecto.banana2;

import android.support.v4.app.Fragment;

public class FragmentInfo {
    private int iconResId;
    private String text;
    private Fragment fragment;

    public FragmentInfo(int iconResId, String text, Fragment fragment) {
        this.iconResId = iconResId;
        this.text = text;
        this.fragment = fragment;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getTitleText() {
        return text;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
