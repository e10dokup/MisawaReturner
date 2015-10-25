package info.e10dokup.misawareturner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.data.ImageConversation;
import info.e10dokup.misawareturner.data.TextConversation;

/**
 * Created by e10dokup on 2015/10/25
 **/
class TextConversationViewHolder {
    TextView selfMessageText;
    TextView responseText;
}

public class TextConversationAdapter extends BaseAdapter {
    private static final String TAG = TextConversationAdapter.class.getSimpleName();
    private final TextConversationAdapter self = this;

    private LayoutInflater mLayoutInflater = null;
    private List<TextConversation> mConversationList;

    public TextConversationAdapter(Context context, List<TextConversation> conversationList) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConversationList = conversationList;
    }

    public void setConversationList(List<TextConversation> conversationList) {
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
        TextConversationViewHolder viewHolder = new TextConversationViewHolder();
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.row_text_conversation, viewGroup, false);
            viewHolder.selfMessageText = (TextView)view.findViewById(R.id.text_self);
            viewHolder.responseText = (TextView)view.findViewById(R.id.text_response);
            view.setTag(viewHolder);
        } else {
            viewHolder = (TextConversationViewHolder) view.getTag();
        }

        viewHolder.selfMessageText.setText(mConversationList.get(i).getSelf());
        viewHolder.responseText.setText(mConversationList.get(i).getResponse());

        return view;
    }
}