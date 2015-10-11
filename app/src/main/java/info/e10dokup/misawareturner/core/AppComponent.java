package info.e10dokup.misawareturner.core;

import dagger.Component;
import info.e10dokup.misawareturner.MainActivity;

@Component(
        modules = MyModule.class
)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
