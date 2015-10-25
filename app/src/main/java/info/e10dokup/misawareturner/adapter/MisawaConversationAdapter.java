package info.e10dokup.misawareturner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.data.Conversation;

/**
 * Created by e10dokup on 2015/10/11
 **/
class MisawaConversationViewHolder {
    TextView selfMessageText;
    NetworkImageView responseImage;
}

public class MisawaConversationAdapter extends BaseAdapter {
    private static final String TAG = MisawaConversationAdapter.class.getSimpleName();
    private final MisawaConversationAdapter self = this;

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    List<Conversation> mConversationList;
    RequestQueue mRequestQueue;
    ImageLoader.ImageCache mImageCache = new ImageLoader.ImageCache() {
        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {

        }
    };

    public MisawaConversationAdapter(Context context, List<Conversation> conversationList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConversationList = conversationList;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void setConversationList(List<Conversation> conversationList) {
        mConversationList = conversationList;
    }

    @Override
    public int getCount() {
        return mConversationList.size();
    }

    @Override
    public Object getItem(int i) {
        return mConversationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MisawaConversationViewHolder viewHolder = new MisawaConversationViewHolder();
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.row_misawa_conversation, viewGroup, false);
            viewHolder.selfMessageText = (TextView)view.findViewById(R.id.text_self);
            viewHolder.responseImage = (NetworkImageView)view.findViewById(R.id.image_response);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MisawaConversationViewHolder) view.getTag();
        }

        ImageLoader imageLoader = new ImageLoader(mRequestQueue, mImageCache);

        viewHolder.selfMessageText.setText(mConversationList.get(i).getSelf());
        viewHolder.responseImage.setImageUrl(mConversationList.get(i).getUrl(), imageLoader);

        return view;
    }
}