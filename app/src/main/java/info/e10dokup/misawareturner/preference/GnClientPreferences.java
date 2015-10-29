package info.e10dokup.misawareturner.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by e10dokup on 2015/10/28
 **/
public class GnClientPreferences {
    private static final String TAG = GnClientPreferences.class.getSimpleName();
    private final GnClientPreferences self = this;

    public final static String PREF_NAME = "Gracenote";
    public final static String KEY_FIRST = "boot_first";
    public final static String KEY_CLIENT = "client";
    private SharedPreferences mPreferences;

    public GnClientPreferences(Context context) {
        this(context, PREF_NAME);
    }

    public GnClientPreferences(Context context, String prefName) {
        mPreferences = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
    }

    public boolean isFirst() {
        return mPreferences.getBoolean(KEY_FIRST, true);
    }

    public String getClient() {
        return mPreferences.getString(KEY_CLIENT, null);
    }

    public void setInfo(boolean flag, String client) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(KEY_FIRST, flag);
        editor.putString(KEY_CLIENT, client);
        editor.apply();
    }
}