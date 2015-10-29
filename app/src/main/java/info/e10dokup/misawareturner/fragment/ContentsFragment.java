package info.e10dokup.misawareturner.fragment;

import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import info.e10dokup.misawareturner.MainActivity;
import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.adapter.ContentsAdapter;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.core.MyApplication;
import info.e10dokup.misawareturner.data.Contents;

/**
 * Created by e10dokup on 2015/10/23
 **/
public class ContentsFragment extends BaseFragment {
    private static final String TAG = ContentsFragment.class.getSimpleName();
    private final ContentsFragment self = this;

    @Bind(R.id.list_contents)
    ListView mContentsListView;

    private List<Contents> mContentsList = new ArrayList<>();
    private ContentsAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] contents = getResources().getStringArray(R.array.contents);
        String[] describes = getResources().getStringArray(R.array.describes);
        int[] drawableIds = {R.drawable.ic_queue_music_cyan_a400_48dp, R.drawable.ic_theaters_cyan_a400_48dp};
        for(int i=0; i<contents.length; i++) {
            mContentsList.add(new Contents(drawableIds[i], contents[i], describes[i]));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ContentsAdapter(getBaseActivity(), mContentsList);
        mContentsListView.setAdapter(mAdapter);
        mContentsListView.setOnItemClickListener(mOnItemClickListener);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            getBaseActivity().replaceFragment(getNewFragment(i), true);
        }
    };

    private BaseFragment getNewFragment(int index) {
        switch (index) {
            case 0:
                return new MusicConversationFragment();
            case 1:
                return new MusicConversationFragment();
        }
        return null;
    }


}