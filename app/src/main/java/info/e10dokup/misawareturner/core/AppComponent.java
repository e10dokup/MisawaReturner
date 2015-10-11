package info.e10dokup.misawareturner.core;

import com.android.volley.RequestQueue;

import dagger.Component;
import info.e10dokup.misawareturner.MainActivity;
import info.e10dokup.misawareturner.fragment.MainFragment;

@Component(
        modules = MyModule.class
)
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);
}
