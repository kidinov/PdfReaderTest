package org.kidinov.kura_test.common.di.module;

import org.kidinov.kura_test.common.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApp() {
        return this.application;
    }
}
