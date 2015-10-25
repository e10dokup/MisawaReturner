package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/25
 **/
public class TextConversation {
    private static final String TAG = TextConversation.class.getSimpleName();
    private final TextConversation self = this;

    String mSelf, mResponse;

    public TextConversation(String self, String response) {
        mSelf = self;
        mResponse = response;
    }

    public String getSelf() {
        return mSelf;
    }

    public void setSelf(String self) {
        mSelf = self;
    }

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }
}