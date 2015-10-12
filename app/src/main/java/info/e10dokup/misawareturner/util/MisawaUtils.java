package info.e10dokup.misawareturner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by e10dokup on 2015/10/12
 **/
public class MisawaUtils {
    private static final String TAG = MisawaUtils.class.getSimpleName();
    private final MisawaUtils self = this;

    public static final int NO_VALUE = 0;
    public static final int POSITIVE = 1;
    public static final int NEGATIVE = 2;
    public static final int EXPECT = 3;
    public static final int REQUEST = 4;

    public static List<Integer> getMisawaCharacterIds(int spn) {
        switch(spn) {
            case NO_VALUE: return Arrays.asList(2, 14);
            case POSITIVE: return Arrays.asList(26, 71);
            case NEGATIVE: return Arrays.asList(25, 17, 13);
            case EXPECT: return Arrays.asList(16, 49);
            case REQUEST: return Arrays.asList(7, 9);
            default: return null;
        }
    }
}