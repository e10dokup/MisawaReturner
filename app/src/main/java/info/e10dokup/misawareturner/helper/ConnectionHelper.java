package info.e10dokup.misawareturner.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.util.MisawaUtils;

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

    public void gnRegisterConnection(Response.Listener listener) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = mContext.getString(R.string.gn_api_url) + "register?client=" + mContext.getString(R.string.gn_app_key);
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, mErrorListener);
        queue.add(request);
        queue.start();
    }

    public void gnRadioConnection(int spn, String key, Response.Listener listener) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = mContext.getString(R.string.gn_api_url)
                + "radio/create?"
                + "genre=35616&era=29483&select_extended=cover&mood=" + MisawaUtils.getMood(spn)
                + "&lang=jpn&country=jpn&client=1125183320-C4A3ADF142A70C232D4FEF91A5A1880B&user="
                + key;

        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, mErrorListener);
        queue.add(request);
        queue.start();
    }

    public void misawaConnection(int spn, Response.Listener listener) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = mContext.getString(R.string.kintone_api_url) + "&query=";
        for (int i : MisawaUtils.getMisawaCharacterIds(spn)) {
            url += ("cid=" + String.valueOf(i));
            url += "%20or%20";
        }
        url = url.substring(0, url.length()-5);
        url += "limit%20500";

        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, mErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                // Add Http Header
                Map<String, String> newHeaders = new HashMap<String, String>();
                newHeaders.putAll(headers);
                newHeaders.put("X-Cybozu-API-Token", mContext.getString(R.string.kintone_api_key));
                return newHeaders;
            }
        };

        queue.add(request);
        queue.start();
    }

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, error.toString());
            Toast.makeText(mContext, "Failed connection!", Toast.LENGTH_SHORT).show();
        }
    };
}