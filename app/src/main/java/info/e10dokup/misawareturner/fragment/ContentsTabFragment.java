package info.e10dokup.misawareturner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.adapter.ContentsFragmentPagerAdapter;
import info.e10dokup.misawareturner.core.BaseFragment;

/**
 * Created by e10dokup on 2015/10/22
 **/
public class ContentsTabFragment extends BaseFragment {
    private static final String TAG = ContentsTabFragment.class.getSimpleName();
    private final ContentsTabFragment self = this;

    @Bind(R.id.layout_tab)
    TabLayout mTabLayout;
    @Bind(R.id.contents_view_pager)
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_contents, container, false);

        ButterKnife.bind(this, view);

        ContentsFragmentPagerAdapter adapter = new ContentsFragmentPagerAdapter(getBaseActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public static BaseFragment getInstance(int position) {
        switch (position) {
            case 0:
                return new ContentsFragment();
            case 1:
                return new MainFragment();
        }
        return null;
    }
}