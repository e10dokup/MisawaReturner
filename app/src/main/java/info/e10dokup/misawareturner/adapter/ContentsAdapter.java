package info.e10dokup.misawareturner.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.data.Contents;

/**
 * Created by e10dokup on 2015/10/23
 **/
class ContentsViewHolder {
    TextView contentsText;
    TextView describeText;
    ImageView contentsImage;
}

public class ContentsAdapter extends BaseAdapter {
    private static final String TAG = ContentsAdapter.class.getSimpleName();
    private final ContentsAdapter self = this;

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    List<Contents> mContentsList;

    public ContentsAdapter(Context context, List<Contents> contentsList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentsList = contentsList;
    }

    public void setContentsList(List<Contents> contentsList) {
        mContentsList = contentsList;
    }

    @Override
    public int getCount() {
        return mContentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return mContentsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ContentsViewHolder viewHolder = new ContentsViewHolder();
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.row_contents, viewGroup, false);
            viewHolder.contentsImage = (ImageView)view.findViewById(R.id.img_contents);
            viewHolder.contentsText = (TextView)view.findViewById(R.id.text_contents);
            viewHolder.describeText = (TextView)view.findViewById(R.id.text_describe);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ContentsViewHolder) view.getTag();
        }

        viewHolder.contentsImage.setImageDrawable(getDrawableResource(mContentsList.get(i).getImageId()));
        viewHolder.contentsText.setText(mContentsList.get(i).getText());
        viewHolder.describeText.setText(mContentsList.get(i).getDescribe());

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