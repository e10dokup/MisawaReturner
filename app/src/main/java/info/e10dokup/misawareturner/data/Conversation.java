package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/11
 **/
public class Conversation {
    private static final String TAG = Conversation.class.getSimpleName();
    private final Conversation self = this;

    String mSelf;
    String mUrl;

    public Conversation(String self, String url) {
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