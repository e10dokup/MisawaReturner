package info.e10dokup.misawareturner.helper;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import info.e10dokup.misawareturner.R;

/**
 * Created by e10dokup on 2015/10/11
 **/
public class ConnectionHelper {
    private static final String TAG = ConnectionHelper.class.getSimpleName();
    private final ConnectionHelper self = this;

    private Context mContext;

    public ConnectionHelper(Context context) {
        mContext = context;
    }

    public void wordAnalyzeConnection(String word, Response.Listener listener) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = mContext.getString(R.string.iminos_api_url) + word;
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, mErrorListener);
        queue.add(request);

        queue.start();
    }

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}