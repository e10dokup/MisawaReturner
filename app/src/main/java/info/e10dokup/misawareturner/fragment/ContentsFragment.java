package info.e10dokup.misawareturner.fragment;

import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.core.MyApplication;

/**
 * Created by e10dokup on 2015/10/23
 **/
public class ContentsFragment extends BaseFragment {
    private static final String TAG = ContentsFragment.class.getSimpleName();
    private final ContentsFragment self = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}