package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/11
 **/
public class ImageConversation {
    private static final String TAG = ImageConversation.class.getSimpleName();
    private final ImageConversation self = this;

    String mSelf;
    String mUrl;

    public ImageConversation(String self, String url) {
        mSelf = self;
        mUrl = url;
    }

    public String getSelf() {
        return mSelf;
    }

    public void setSelf(String self) {
        mSelf = self;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}