package info.e10dokup.misawareturner.data;

/**
 * Created by e10dokup on 2015/10/11
 **/
public class AnalyzeData {
    private static final String TAG = AnalyzeData.class.getSimpleName();
    private final AnalyzeData self = this;

    private String mWord;
    private int mSpn;

    public AnalyzeData(String word, int spn) {
        mWord = word;
        mSpn = spn;
    }

    public String getWord() {
        return mWord;
    }

    public int getSpn() {
        return mSpn;
    }

    public String getStringSpn() {
        switch (mSpn) {
            case 0: return "Nothing";
            case 1: return "Positive";
            case 2: return "Negative";
            case 3: return "Expection";
            case 4: return "Request";
            default: return "Mistake";
        }
    }
}