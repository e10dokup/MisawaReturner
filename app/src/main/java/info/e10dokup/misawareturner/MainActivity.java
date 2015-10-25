package info.e10dokup.misawareturner;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.e10dokup.misawareturner.core.BaseActivity;
import info.e10dokup.misawareturner.core.BaseFragment;
import info.e10dokup.misawareturner.core.MyApplication;
import info.e10dokup.misawareturner.data.AnalyzeData;
import info.e10dokup.misawareturner.fragment.ContentsTabFragment;
import info.e10dokup.misawareturner.fragment.MainFragment;

/**
 * Created by e10dokup on 2015/10/09
 **/
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final MainActivity self = this;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private AnalyzeData sAnalyzeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        { // Inject values
            MyApplication app = (MyApplication) getApplication();
            app.getComponent().inject(this);
        }

        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new ContentsTabFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        BaseFragment fragment = getCurrentFragment();

        if (fragment != null && fragment.onBackPressed()) {
            // OK
        } else {
            popFragment();
        }
    }

    public BaseFragment getCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
        if (fragment instanceof BaseFragment) {
            return (BaseFragment) fragment;
        }
        return null;
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean recordBackstack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (recordBackstack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();
    }

    @Override
    public void popFragment() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onFragmentResumed(BaseFragment fragment) {
        super.onFragmentResumed(fragment);
    }

    public void setAnalizeData(AnalyzeData analyzeData) {
        sAnalyzeData = analyzeData;
    }

    public AnalyzeData getAnalyzeData() {
        return sAnalyzeData;
    }

    public void setToolbarTitle(int titleId) {
        mToolbar.setTitle(titleId);
    }
}