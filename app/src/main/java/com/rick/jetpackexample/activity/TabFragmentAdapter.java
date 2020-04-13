package com.rick.jetpackexample.activity;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentPagerAdapter {

    public TabFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    SparseArray<Fragment> mFragments = new SparseArray<>();

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new TargetOneFragment();
                    break;
                case 1:
                    fragment = new TargetTwoFragment();
                    break;
                case 2:
                    fragment = new TargetThreeFragment();
                    break;
            }
            mFragments.put(position, fragment);
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
            default:
                return "One";
            case 1:
                return "Two";
            case 2:
                return "Three";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
