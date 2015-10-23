package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/23
 **/
public class Contents {
    private static final String TAG = Contents.class.getSimpleName();
    private final Contents self = this;

    private int mImageId;
    private String mText;

    public Contents(int imageId, String text) {
        mImageId = imageId;
        mText = text;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}