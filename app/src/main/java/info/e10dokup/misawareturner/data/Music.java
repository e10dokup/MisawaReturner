package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/29
 **/
public class Music {
    private static final String TAG = Music.class.getSimpleName();
    private final Music self = this;

    private String mArtist;
    private String mTitle;
    private String mCoverUrl;

    public Music(String artist, String title, String coverUrl) {
        mArtist = artist;
        mTitle = title;
        mCoverUrl = coverUrl;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        mCoverUrl = coverUrl;
    }
}