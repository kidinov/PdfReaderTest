package org.kidinov.kura_test.common.di.component;

import org.kidinov.kura_test.common.App;
import org.kidinov.kura_test.common.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
}
