package info.e10dokup.misawareturner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.e10dokup.misawareturner.MainActivity;
import info.e10dokup.misawareturner.R;
import info.e10dokup.misawareturner.adapter.MisawaConversationAdapter;
import info.e10dokup.misawareturner.adapter.TextConversationAdapter;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.core.MyApplication;
import info.e10dokup.misawareturner.data.AnalyzeData;
import info.e10dokup.misawareturner.data.ImageConversation;
import info.e10dokup.misawareturner.data.Music;
import info.e10dokup.misawareturner.data.TextConversation;
import info.e10dokup.misawareturner.helper.ConnectionHelper;
import info.e10dokup.misawareturner.preference.GnClientPreferences;

/**
 * Created by e10dokup on 2015/10/25
 **/
public class MusicConversationFragment extends BaseFragment {
    private static final String TAG = MusicConversationFragment.class.getSimpleName();
    private final MusicConversationFragment self = this;

    @Inject
    ConnectionHelper mConnectionHelper;

    @Bind(R.id.list_conversation)
    ListView mListView;
    @Bind(R.id.btn_voice)
    ImageView mRecognitionButton;
    @Bind(R.id.layout_conversation)
    RelativeLayout mLayout;

    private List<TextConversation> mConversationList = new ArrayList<>();
    private TextConversationAdapter mAdapter;
    private AnalyzeData mAnalyzeData;
    private SpeechRecognizer mSpeechRecognizer;
    private String mWord;
    private GnClientPreferences mGnClientPreferences;
    private String mGnKey;
    private List<List<Music>> mMusicsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);

        { // Inject values
            MyApplication app = (MyApplication) getBaseActivity().getMyApplication();
            app.getComponent().inject(this);
        }
        ButterKnife.bind(this, view);

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getBaseActivity());
        mSpeechRecognizer.setRecognitionListener(mRecognitionListener);

        mAdapter = new TextConversationAdapter(getBaseActivity(), mConversationList);
        mListView.setAdapter(mAdapter);
        mRecognitionButton.setOnClickListener(mOnClickListener);

        mGnClientPreferences = new GnClientPreferences(getBaseActivity());

        if (mGnClientPreferences.isFirst()) {
            mConnectionHelper.gnRegisterConnection(mGnRegisterListener);
        } else {
            mGnKey = mGnClientPreferences.getClient();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<Music> musics = mMusicsList.get(i);
                ((MainActivity) getBaseActivity()).setMusics(musics);
                replaceFragment(new RecommendFragment(), true);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSpeechRecognizer.destroy();
        ButterKnife.unbind(this);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    getBaseActivity().getPackageName());
            mSpeechRecognizer.startListening(intent);
        }
    };

    private void setResponse(List<Music> musics) {
        String response = "今のあなたには\n";
        for(Music m : musics) {
            response += "\n" + m.getArtist() + " の " + m.getTitle() + "\n";
        }
        response += "\nがおすすめです";
        TextConversation conversation = new TextConversation(mAnalyzeData.getWord(), response);
        mConversationList.add(conversation);
        mAdapter.notifyDataSetChanged();
    }

    private Response.Listener<JSONObject> mGnRegisterListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try{
                JSONObject res = response.getJSONArray("RESPONSE").getJSONObject(0);
                JSONObject user = res.getJSONArray("USER").getJSONObject(0);
                mGnKey = user.getString("VALUE");
                mGnClientPreferences.setInfo(false, mGnKey);
                Snackbar.make(mLayout, "Gracenote APIとの連携が完了しました", Snackbar.LENGTH_SHORT).show();
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    };

    private Response.Listener<JSONObject> mGnRadioSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject res = response.getJSONArray("RESPONSE").getJSONObject(0);
                JSONArray albums = res.getJSONArray("ALBUM");
                List<Music> musics = new ArrayList<>();
                for(int i=0; i < albums.length(); i++) {
                    JSONObject album = albums.getJSONObject(i);
                    String coverUrl;
                    try {
                        coverUrl = album.getJSONArray("URL").getJSONObject(0).getString("VALUE");
                    } catch(JSONException e) {
                        coverUrl = null;
                    }
                    String artist = album.getJSONArray("ARTIST").getJSONObject(0).getString("VALUE");
                    String title = album.getJSONArray("TRACK").getJSONObject(0)
                            .getJSONArray("TITLE").getJSONObject(0).getString("VALUE");
                    musics.add(new Music(artist, title, coverUrl));
                }
                setResponse(musics);
                mMusicsList.add(musics);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Response.Listener<JSONObject> mAnalizeSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject result = response.getJSONArray("results").getJSONObject(0);
                int spn = result.getInt("spn");
                mAnalyzeData = new AnalyzeData(mWord, spn);
                mConnectionHelper.gnRadioConnection(spn, mGnKey, mGnRadioSuccessListener);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private RecognitionListener mRecognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Snackbar.make(mLayout, "認識準備ができました", Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {
            Snackbar.make(mLayout, "話しかけてください", Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            Log.v(TAG, "recieve : " + rmsdB + "dB");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.v(TAG,"onBufferReceived");
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> recData = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if(recData.size() > 0) {
                // 認識結果候補で一番有力なものを表示
                mWord = recData.get(0);
                Snackbar.make(mLayout, mWord, Snackbar.LENGTH_SHORT).show();
                mConnectionHelper.wordAnalyzeConnection(mWord, mAnalizeSuccessListener);
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {
        }
    };


}