package com.example.horim.adapters;

import com.example.horim.fragments.InfoFragment;
import com.example.horim.fragments.MenuFragment;
import com.example.horim.fragments.ReviewFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MenuFragment tab1 = new MenuFragment();
                return tab1;
            case 1:
                InfoFragment tab2 = new InfoFragment();
                return tab2;
            case 2:
                ReviewFragment tab3 = new ReviewFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}