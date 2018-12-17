package com.selecto.banana2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
    //private final List<String> mFragmentTitleList = new ArrayList<String>();
    private final List<FragmentInfo> mFragmentInfoList = new ArrayList<FragmentInfo>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(int iconResId, String title, Fragment fragment) {
        FragmentInfo info = new FragmentInfo(iconResId, title, fragment);
        mFragmentInfoList.add(info);
    }

    public FragmentInfo getFragmentInfo(int position) {
        return mFragmentInfoList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentInfoList.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentInfoList.get(position).getTitleText();
    }

    @Override
    public int getCount() {
        return mFragmentInfoList.size();
    }
}
