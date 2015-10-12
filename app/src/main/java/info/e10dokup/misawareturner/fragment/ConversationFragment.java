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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;

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
import info.e10dokup.misawareturner.adapter.ConversationAdapter;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.core.MyApplication;
import info.e10dokup.misawareturner.data.AnalyzeData;
import info.e10dokup.misawareturner.data.Conversation;
import info.e10dokup.misawareturner.helper.ConnectionHelper;

/**
 * Created by e10dokup on 2015/10/11
 **/
public class ConversationFragment extends BaseFragment {
    private static final String TAG = ConversationFragment.class.getSimpleName();
    private final ConversationFragment self = this;

    @Inject
    ConnectionHelper mConnectionHelper;

    @Bind(R.id.list_conversation)
    ListView mListView;
    @Bind(R.id.btn_voice)
    ImageView mRecognitionButton;
    @Bind(R.id.layout_conversation)
    RelativeLayout mLayout;

    private List<Conversation> mConversationList = new ArrayList<>();
    private ConversationAdapter mAdapter;
    private AnalyzeData mAnalyzeData;
    private SpeechRecognizer mSpeechRecognizer;
    private String mWord;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnalyzeData = ((MainActivity)getBaseActivity()).getAnalyzeData();
    }

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

        mAdapter = new ConversationAdapter(getBaseActivity(), mConversationList);
        mListView.setAdapter(mAdapter);
        mRecognitionButton.setOnClickListener(mOnClickListener);

        mConnectionHelper.misawaConnection(mAnalyzeData.getSpn(), mMisawaSuccessListener);
        return view;
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
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

    Response.Listener<JSONObject> mMisawaSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                String selfMessage = mAnalyzeData.getWord();
                Random random = new Random();
                int randomInt =  random.nextInt(response.getJSONArray("records").length());
                JSONObject record = response.getJSONArray("records").getJSONObject(randomInt);
                mConversationList.add(new Conversation(selfMessage, record.getJSONObject("image").getString("value")));
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.Listener<JSONObject> mAnalizeSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject result = response.getJSONArray("results").getJSONObject(0);
                int spn = result.getInt("spn");
                mAnalyzeData = new AnalyzeData(mWord, spn);
                mConnectionHelper.misawaConnection(spn, mMisawaSuccessListener);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    RecognitionListener mRecognitionListener = new RecognitionListener() {
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