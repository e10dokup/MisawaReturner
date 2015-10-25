package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/23
 **/
public class Contents {
    private static final String TAG = Contents.class.getSimpleName();
    private final Contents self = this;

    private int mImageId;
    private String mText;
    private String mDescribe;

    public Contents(int imageId, String text, String describe) {
        mImageId = imageId;
        mText = text;
        mDescribe = describe;
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

    public String getDescribe() {
        return mDescribe;
    }

    public void setDescribe(String describe) {
        mDescribe = describe;
    }
}