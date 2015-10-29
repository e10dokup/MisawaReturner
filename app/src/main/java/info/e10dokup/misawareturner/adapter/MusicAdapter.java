package info.e10dokup.misawareturner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.data.Contents;
import info.e10dokup.misawareturner.data.Music;

/**
 * Created by e10dokup on 2015/10/28
 **/
class MusicViewHolder {
    TextView artistText;
    TextView titleText;
    NetworkImageView coverImage;
}

public class MusicAdapter extends BaseAdapter {
    private static final String TAG = ContentsAdapter.class.getSimpleName();
    private final MusicAdapter self = this;

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    List<Music> mMusicList;
    private RequestQueue mRequestQueue;
    private ImageLoader.ImageCache mImageCache = new ImageLoader.ImageCache() {
        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {

        }
    };

    public MusicAdapter(Context context, List<Music> musicList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMusicList = musicList;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void setMusicList(List<Music> musicList) {
        mMusicList = musicList;
    }

    @Override
    public int getCount() {
        return mMusicList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMusicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MusicViewHolder viewHolder = new MusicViewHolder();
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.row_musics, viewGroup, false);
            viewHolder.coverImage = (NetworkImageView)view.findViewById(R.id.img_cover);
            viewHolder.artistText = (TextView)view.findViewById(R.id.text_artist);
            viewHolder.titleText = (TextView)view.findViewById(R.id.text_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MusicViewHolder) view.getTag();
        }

        ImageLoader imageLoader = new ImageLoader(mRequestQueue, mImageCache);

        viewHolder.coverImage.setDefaultImageResId(R.drawable.ic_album_cyan_a700_48dp);
        if(mMusicList.get(i).getCoverUrl() != null) {
            viewHolder.coverImage.setImageUrl(mMusicList.get(i).getCoverUrl(), imageLoader);
        }
        viewHolder.artistText.setText(mMusicList.get(i).getArtist());
        viewHolder.titleText.setText(mMusicList.get(i).getTitle());

        return view;
    }

    @SuppressWarnings("deprecation")
    public Drawable getDrawableResource(int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return mContext.getDrawable(id);
        }
        else{
            return mContext.getResources().getDrawable(id);
        }
    }
}