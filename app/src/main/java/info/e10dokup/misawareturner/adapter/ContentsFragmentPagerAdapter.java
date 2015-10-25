package info.e10dokup.misawareturner.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.fragment.ContentsFragment;
import info.e10dokup.misawareturner.fragment.ContentsTabFragment;
import info.e10dokup.misawareturner.fragment.MainFragment;

/**
 * Created by e10dokup on 2015/10/22
 **/
public class ContentsFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = ContentsFragmentPagerAdapter.class.getSimpleName();
    private final ContentsFragmentPagerAdapter self = this;

    private Context mContext;

    public ContentsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
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
                return mContext.getString(R.string.title_talks);
            case 1:
                return mContext.getString(R.string.title_misawa);
        }
        return null;
    }
}