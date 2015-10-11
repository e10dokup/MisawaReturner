package info.e10dokup.misawareturner.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Request;

import java.sql.ResultSet;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by e10dokup on 2015/10/09
 **/
@Module
public class MyModule {
    private static final String TAG = MyModule.class.getSimpleName();
    private final MyModule self = this;

    private Context mContext;

    public MyModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    public RequestQueue provideRequestQueue() {
        return Volley.newRequestQueue(mContext);
    }
}