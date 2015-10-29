package info.e10dokup.misawareturner.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import info.e10dokup.misawareturner.MainActivity;
import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.adapter.ContentsAdapter;
import info.e10dokup.misawareturner.adapter.MusicAdapter;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.data.Contents;
import info.e10dokup.misawareturner.data.Music;

/**
 * Created by e10dokup on 2015/10/29
 **/
public class RecommendFragment extends BaseFragment {
    private static final String TAG = RecommendFragment.class.getSimpleName();
    private final RecommendFragment self = this;

    @Bind(R.id.list_contents)
    ListView mContentsListView;

    private List<Music> mMusicList = new ArrayList<>();
    private MusicAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMusicList = ((MainActivity) getBaseActivity()).getMusics();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new MusicAdapter(getBaseActivity(), mMusicList);
        mContentsListView.setAdapter(mAdapter);
        mContentsListView.setOnItemClickListener(mOnItemClickListener);

        return view;

    }

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Music music = mMusicList.get(i);
            Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.setPackage("com.google.android.youtube");
            intent.putExtra("query", music.getTitle() + " " + music.getArtist());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}