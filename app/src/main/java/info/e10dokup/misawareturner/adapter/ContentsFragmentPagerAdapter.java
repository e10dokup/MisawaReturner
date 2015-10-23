package info.e10dokup.misawareturner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.e10dokup.misawareturner.fragment.ContentsFragment;
import info.e10dokup.misawareturner.fragment.ContentsTabFragment;
import info.e10dokup.misawareturner.fragment.MainFragment;

/**
 * Created by e10dokup on 2015/10/22
 **/
public class ContentsFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = ContentsFragmentPagerAdapter.class.getSimpleName();
    private final ContentsFragmentPagerAdapter self = this;

    public ContentsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ContentsTabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "トーク";
            case 1:
                return "使い方";
        }
        return null;
    }
}